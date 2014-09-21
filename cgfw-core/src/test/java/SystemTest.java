import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.pacoworks.cardframework.eventbus.EventCommander;
import com.pacoworks.cardframework.eventbus.events.EventVictory;
import com.pacoworks.cardframework.framework.CardgameFramework;
import com.pacoworks.cardframework.systems.BasePhaseSystem;
import com.pacoworks.cardframework.systems.IVictoryDecider;
import com.squareup.otto.Subscribe;

/**
 * Created by Paco on 20/09/2014.
 */
public class SystemTest {
    private static final List<BasePhaseSystem> basePhaseSystem = new ArrayList<BasePhaseSystem>();

    public static void main(String[] args) {
        final AtomicBoolean loop = new AtomicBoolean(true);
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        basePhaseSystem.add(new BasePhaseSystem(Aspect.getEmpty()) {
            @Override
            public BasePhaseSystem pushSystem() {
                return this;
            }

            @Override
            protected void process(Entity e) {
                //
            }
        });
        CardgameFramework.CFBuilder builder = CardgameFramework.builder();
        IVictoryDecider victoryDecider = new IVictoryDecider() {
            @Override
            public boolean isVictoryCondition() {
                System.out.println("Stuff");
                return Math.random() < 0.5d;
            }
        };
        EventCommander eventCommander = new EventCommander();
        final CardgameFramework cardgameFramework = builder.eventCommander(eventCommander)
                .victoryChecker(victoryDecider).phaseSystems(basePhaseSystem).build();
        eventCommander.subscribe(new Object() {
            @Subscribe
            public void triggerVictory(EventVictory victory) {
                System.out.println("VICTORY!" + System.nanoTime());
                cardgameFramework.end();
                loop.lazySet(false);
            }
        });
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                cardgameFramework.process();
            }
        }, 0, 100, TimeUnit.MILLISECONDS);
        while (loop.get()) {
            // HERP
        }
    }
}
