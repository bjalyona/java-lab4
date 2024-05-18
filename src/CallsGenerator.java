import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import static java.lang.Thread.sleep;

public class CallsGenerator implements Runnable{
    private final Queue<Call> calls;
    private final int maxFloor;
    private final int minFloor;

    public CallsGenerator(Queue<Call> calls, int minFloor, int maxFloor) {
        this.calls = calls;

        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
    }
    @Override
    public void run(){
        try {
            Thread.sleep(50);

            while(true) {
                Random random = new Random();
                int currFloor = random.nextInt(maxFloor - minFloor + 1) + minFloor;

                int finalFloor;
                do {
                    finalFloor = random.nextInt(maxFloor - minFloor + 1) + minFloor;
                } while (currFloor == finalFloor);
                Direction direction;
                if (finalFloor > currFloor) {
                    direction = Direction.UP;
                } else {
                    direction = Direction.DOWN;
                }

                System.out.println("Создана заявка с " + currFloor + " по " + finalFloor + " этаж");
                calls.add(new Call(currFloor, finalFloor, direction));
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }





    }
}
