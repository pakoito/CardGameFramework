import com.artemis.utils.EntityBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pacoworks.cardframework.api.CFWConstants;
import com.pacoworks.cardframework.api.model.CFWSystem;
import com.pacoworks.cardframework.api.model.components.CFWComponent;
import com.pacoworks.cardframework.components.Player;
import com.pacoworks.cardframework.components.UnpackedComponent;
import com.pacoworks.cardframework.eventbus.IEventCommander;
import com.pacoworks.cardframework.eventbus.events.BaseEvent;
import com.pacoworks.cardframework.framework.CardgameFramework;
import com.pacoworks.cardframework.systems.IVictoryDecider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Paco on 20/09/2014.
 */
public class SystemTest {
    public static void main(String[] args) {
        oldMethod();
        try {
            List<CFWSystem> systems = new ArrayList<CFWSystem>();
            ObjectMapper mapper = new ObjectMapper();
            CFWSystem cfwSystem = mapper.readValue(
                    SystemTest.class.getResource("selectnextplayer.json"), CFWSystem.class);
            systems.add(cfwSystem);
            List<Class<? extends CFWComponent>> customComponents = Arrays.asList(PlayerHandCount.class, PlayerPosition.class);
            CardgameFramework framework = CardgameFramework.builder().victoryChecker(new IVictoryDecider() {
                @Override
                public boolean isVictoryCondition() {
                    return false;
                }
            }).systemsFiles(systems).startingSystemName("selectnextplayer").customComponents(customComponents)
                    .eventCommander(new IEventCommander() {
                        @Override
                        public void postAnyEvent(BaseEvent event) {
                        }
                    }).build();
            new EntityBuilder(framework.getWorld()).tag("player1").group("1")
                    .with(new PlayerPosition(0), new PlayerHandCount(), new Player(), new UnpackedComponent()).build();
            new EntityBuilder(framework.getWorld()).tag("player2").tag(CFWConstants.PlayerGroups.SELECTED_PLAYER).group("2")
                    .with(new PlayerPosition(1), new PlayerHandCount(), new Player(), new UnpackedComponent()).build();
            CFWConstants.GlobalValues.setValue("player_count", 2f);
            framework.process();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void oldMethod() {
        // final AtomicBoolean loop = new AtomicBoolean(true);
        // basePhaseSystem.add(new BasePhaseSystem(Aspect.getEmpty()) {
        // @Override
        // public BasePhaseSystem[] pushSystems() {
        // return new BasePhaseSystem[] {
        // this
        // };
        // }
        //
        // @Override
        // protected void process(Entity e) {
        // //
        // }
        // });
        // CardgameFramework.CFBuilder builder = CardgameFramework.builder();
        // IVictoryDecider victoryDecider = new IVictoryDecider() {
        // @Override
        // public boolean isVictoryCondition() {
        // System.out.println("Stuff");
        // return Math.random() < 0.5d;
        // }
        // };
        // final CardgameFramework cardgameFramework = builder
        // .victoryChecker(victoryDecider).phaseSystems(basePhaseSystem).build();
        // EventCommander eventCommander = cardgameFramework.getCommander();
        // eventCommander.subscribe(new Object() {
        // @Subscribe
        // public void triggerVictory(EventVictory victory) {
        // loop.set(false);
        // System.out.println("VICTORY!" + System.nanoTime());
        // }
        // });
        // // executorService.scheduleAtFixedRate(new Runnable() {
        // // @Override
        // // public void run() {
        // //
        // // }
        // // }, 0, 100, TimeUnit.MILLISECONDS);
        // while (loop.get()) {
        // System.out.println(System.nanoTime());
        // cardgameFramework.process();
        // }
        // cardgameFramework.end();
    }
}
