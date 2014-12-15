
package com.pacoworks.cardframework.framework;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.pacoworks.cardframework.api.util.NamingUtils;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Accessors;
import lombok.experimental.Builder;
import lombok.extern.slf4j.Slf4j;

import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.World;
import com.pacoworks.cardframework.api.CFWConstants;
import com.pacoworks.cardframework.api.factories.system.SystemFactory;
import com.pacoworks.cardframework.api.model.CFWSystem;
import com.pacoworks.cardframework.api.model.components.CFWComponent;
import com.pacoworks.cardframework.api.util.WorldFetcher;
import com.pacoworks.cardframework.dependencies.GameSystemModule;
import com.pacoworks.cardframework.dependencies.LuaJModule;
import com.pacoworks.cardframework.dependencies.WorldModule;
import com.pacoworks.cardframework.eventbus.IEventCommander;
import com.pacoworks.cardframework.eventbus.events.EventGameEnded;
import com.pacoworks.cardframework.eventbus.events.EventGameProcessed;
import com.pacoworks.cardframework.eventbus.events.EventGameStarted;
import com.pacoworks.cardframework.luaj.LuaJEngine;
import com.pacoworks.cardframework.systems.BasePhaseSystem;
import com.pacoworks.cardframework.systems.GameSystem;
import com.pacoworks.cardframework.systems.IVictoryDecider;
import dagger.Component;

/**
 * Created by Paco on 20/09/2014. See LICENSE.md
 */
@Slf4j
public class CardgameFramework {
    @Singleton
    @Component(modules = {
            GameSystemModule.class, LuaJModule.class, WorldModule.class
    })
    public interface CardgameFrameworkComponent {
        void inject(CardgameFramework injectee);

        void inject(WorldFetcher injectee);
    }

    private Entity mGame;

    private AtomicBoolean isStarted = new AtomicBoolean(false);

    @Inject
    protected GameSystem mGameSystem;

    @Getter
    @Accessors(prefix = "m")
    @Inject
    protected World mWorld;

    @Inject
    protected IEventCommander mCommander;

    @Getter
    @Accessors(prefix = "m")
    @Inject
    protected LuaJEngine mLuaEngine;

    @Getter
    @Accessors(prefix = "s")
    private static CardgameFrameworkComponent sComponentInjector;

    // TODO starting phase, passive system list, active system list
    // TODO player/team configuration
    // @Builder(builderClassName = "CFBuilder")
    private CardgameFramework(@NonNull IVictoryDecider victoryChecker,
            @NonNull IEventCommander eventCommander,
            @NonNull Iterable<BasePhaseSystem> phaseSystems,
            @NonNull BasePhaseSystem startingSystem, Iterable<EntitySystem> otherSystems,
            String scriptsPath, boolean debuggableScripts) {
        sComponentInjector = Dagger_CardgameFramework$CardgameFrameworkComponent
                .builder()
                .gameSystemModule(new GameSystemModule(victoryChecker, eventCommander))
                .luaJModule(
                        new LuaJModule((scriptsPath == null) ? "" : scriptsPath, debuggableScripts))
                .worldModule(new WorldModule(phaseSystems, otherSystems)).build();
        sComponentInjector.inject(this);
        mWorld.initialize();
        EntityFactory.createGame(mWorld, startingSystem);
        isStarted.set(true);
        mCommander.postAnyEvent(EventGameStarted.create());
        log.info("CardFramework started");
    }

    // TODO starting phase, passive system list, active system list
    // TODO player/team configuration
    @Builder(builderClassName = "CFWScriptsBuilder")
    private CardgameFramework(@NonNull IVictoryDecider victoryChecker,
            @NonNull IEventCommander eventCommander, @NonNull Iterable<CFWSystem> systemsFiles,
            @NonNull String startingSystemName, @NonNull Iterable<Class <? extends CFWComponent>> customComponents,
            String scriptsPath, boolean debuggableScripts) {
        for (Class <? extends CFWComponent> component: customComponents){
            CFWConstants.CustomComponents.registerComponent(NamingUtils.getNameForComponentClass(component), component);
        }
        List<BasePhaseSystem> phaseSystems = new ArrayList<BasePhaseSystem>();
        for (CFWSystem systemPath : systemsFiles) {
            BasePhaseSystem basePhaseSystem = SystemFactory.create(systemPath);
            phaseSystems.add(basePhaseSystem);
            CFWConstants.CustomSystems.registerSystem(systemPath.getName(), basePhaseSystem);
        }
        sComponentInjector = Dagger_CardgameFramework$CardgameFrameworkComponent
                .builder()
                .gameSystemModule(new GameSystemModule(victoryChecker, eventCommander))
                .luaJModule(
                        new LuaJModule((scriptsPath == null) ? "" : scriptsPath, debuggableScripts))
                .worldModule(new WorldModule(phaseSystems, null)).build();
        sComponentInjector.inject(this);
        mWorld.initialize();
        BasePhaseSystem startingSystem = CFWConstants.CustomSystems.getSystem(startingSystemName);
        EntityFactory.createGame(mWorld, startingSystem);
        isStarted.set(true);
        mCommander.postAnyEvent(EventGameStarted.create());
        log.info("CardFramework started");
    }

    public void process() throws IllegalStateException {
        assertStarted();
        mCommander.postAnyEvent(EventGameProcessed.create());
        mWorld.process();
    }

    private void assertStarted() {
        if (!isStarted.get()) {
            throw new IllegalStateException("CardFramework not started.");
        }
    }

    public void end() {
        isStarted.set(false);
        mCommander.postAnyEvent(EventGameEnded.create());
        mWorld.dispose();
        mWorld = null;
        mCommander = null;
        log.info("CardFramework ended");
    }
}
