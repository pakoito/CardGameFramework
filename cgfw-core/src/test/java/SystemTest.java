import com.artemis.Aspect;
import com.artemis.Entity;
import com.pacoworks.cardframework.eventbus.EventCommander;
import com.pacoworks.cardframework.eventbus.events.EventVictory;
import com.pacoworks.cardframework.framework.CardgameFramework;
import com.pacoworks.cardframework.systems.BasePhaseSystem;
import com.pacoworks.cardframework.systems.IVictoryDecider;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Paco on 20/09/2014.
 */
public class SystemTest {
    private static final List<BasePhaseSystem> basePhaseSystem = new ArrayList<BasePhaseSystem>();

    public static void main(String[] args) {
        final AtomicBoolean loop = new AtomicBoolean(true);
        basePhaseSystem.add(new BasePhaseSystem(Aspect.getEmpty()) {
            @Override
            public BasePhaseSystem[] pushSystems() {
                return new BasePhaseSystem[] {
                    this
                };
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
        final CardgameFramework cardgameFramework = builder
                .victoryChecker(victoryDecider).phaseSystems(basePhaseSystem).build();
        EventCommander eventCommander = cardgameFramework.getCommander();
        eventCommander.subscribe(new Object() {
            @Subscribe
            public void triggerVictory(EventVictory victory) {
                loop.set(false);
                System.out.println("VICTORY!" + System.nanoTime());
            }
        });
        // executorService.scheduleAtFixedRate(new Runnable() {
        // @Override
        // public void run() {
        //
        // }
        // }, 0, 100, TimeUnit.MILLISECONDS);
        while (loop.get()) {
            System.out.println(System.nanoTime());
            cardgameFramework.process();
        }
        cardgameFramework.end();
    }
}
