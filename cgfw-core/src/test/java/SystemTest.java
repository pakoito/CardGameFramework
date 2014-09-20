import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.utils.ImmutableBag;
import com.pacoworks.cardframework.framework.CardgameFramework;
import com.pacoworks.cardframework.systems.BasePhaseSystem;
import com.pacoworks.cardframework.systems.GameSystem;

/**
 * Created by Paco on 20/09/2014.
 */
public class SystemTest {
    private static final BasePhaseSystem basePhaseSystem = new BasePhaseSystem(Aspect.getEmpty()) {
        @Override
        protected void processEntities(ImmutableBag<Entity> entities) {
            // NUFFIN HAPPENING
            System.out.println("BOP");
        }

        @Override
        public BasePhaseSystem pushSystem() {
            return this;
        }
    };

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        final CardgameFramework cardgameFramework = new CardgameFramework();
        cardgameFramework.start(new GameSystem() {
            @Override
            protected boolean isVictoryCondition() {
                System.out.println("Bup");
                return cardgameFramework.getWorld().delta > 500;
            }

            @Override
            protected void triggerVictory() {
                System.out.println("VICTORY!");
                cardgameFramework.end();
            }
        }, basePhaseSystem);
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                cardgameFramework.process();
            }
        }, 0, 16, TimeUnit.MILLISECONDS);
        while (true) {
            // HERP
        }
    }
}
