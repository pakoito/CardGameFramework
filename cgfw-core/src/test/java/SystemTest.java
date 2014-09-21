import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.pacoworks.cardframework.framework.CardgameFramework;
import com.pacoworks.cardframework.systems.BasePhaseSystem;
import com.pacoworks.cardframework.systems.GameSystem;
import com.pacoworks.cardframework.systems.IGameSystemListener;

/**
 * Created by Paco on 20/09/2014.
 */
public class SystemTest {
    private static final BasePhaseSystem basePhaseSystem = new BasePhaseSystem(Aspect.getEmpty()) {
        @Override
        public BasePhaseSystem pushSystem() {
            return this;
        }

        @Override
        protected void process(Entity e) {
            //
        }
    };

    public static void main(String[] args) {
        final AtomicBoolean loop = new AtomicBoolean(true);
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        final CardgameFramework cardgameFramework = new CardgameFramework();
        cardgameFramework.start(new GameSystem(new IGameSystemListener() {
            @Override
            public boolean isVictoryCondition() {
                System.out.println("Bup");
                return Math.random() < 0.02d;
            }

            @Override
            public void triggerVictory() {
                System.out.println("VICTORY!");
                cardgameFramework.end();
                loop.lazySet(false);
            }
        }), basePhaseSystem);
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                cardgameFramework.process();
            }
        }, 0, 16, TimeUnit.MILLISECONDS);
        while (loop.get()) {
            // HERP
        }
    }
}
