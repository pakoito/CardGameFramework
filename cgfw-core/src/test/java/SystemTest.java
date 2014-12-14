import com.pacoworks.cardframework.api.factories.system.SystemFactory;
import com.pacoworks.cardframework.systems.BasePhaseSystem;

import java.io.IOException;

/**
 * Created by Paco on 20/09/2014.
 */
public class SystemTest {

    public static void main(String[] args) {
        oldMethod();
        try {
            BasePhaseSystem system = SystemFactory.create(SystemTest.class.getResource("playerchoice.json"));
            system.fakeProcess();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void oldMethod() {
//        final AtomicBoolean loop = new AtomicBoolean(true);
//        basePhaseSystem.add(new BasePhaseSystem(Aspect.getEmpty()) {
//            @Override
//            public BasePhaseSystem[] pushSystems() {
//                return new BasePhaseSystem[] {
//                    this
//                };
//            }
//
//            @Override
//            protected void process(Entity e) {
//                //
//            }
//        });
//        CardgameFramework.CFBuilder builder = CardgameFramework.builder();
//        IVictoryDecider victoryDecider = new IVictoryDecider() {
//            @Override
//            public boolean isVictoryCondition() {
//                System.out.println("Stuff");
//                return Math.random() < 0.5d;
//            }
//        };
//        final CardgameFramework cardgameFramework = builder
//                .victoryChecker(victoryDecider).phaseSystems(basePhaseSystem).build();
//        EventCommander eventCommander = cardgameFramework.getCommander();
//        eventCommander.subscribe(new Object() {
//            @Subscribe
//            public void triggerVictory(EventVictory victory) {
//                loop.set(false);
//                System.out.println("VICTORY!" + System.nanoTime());
//            }
//        });
//        // executorService.scheduleAtFixedRate(new Runnable() {
//        // @Override
//        // public void run() {
//        //
//        // }
//        // }, 0, 100, TimeUnit.MILLISECONDS);
//        while (loop.get()) {
//            System.out.println(System.nanoTime());
//            cardgameFramework.process();
//        }
//        cardgameFramework.end();
    }
}
