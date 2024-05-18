import java.util.*;

public class Control implements Runnable {
    private final Queue<Call> calls;
    public Control(Queue<Call> calls) {
        this.calls = calls;

    }

    @Override
    public void run() {
        Elevator el1 = new Elevator(1);
        Elevator el2 = new Elevator(2);
        List<Elevator> elevators = List.of(el1, el2);
        try {
            while (true) {
                while (calls.isEmpty()) {
                    Thread.sleep(100);
                }
                Call call = calls.poll();
                Queue<Integer> floorsQueue = new LinkedList<>();

                floorsQueue.add(call.getCurrFloor());
                floorsQueue.add(call.getFinalFloor());

                Elevator el = chooseElevator(elevators, call);
                while (!floorsQueue.isEmpty()) {
                    int targetFloor = floorsQueue.poll();
                    System.out.println("Лифт " + el.getNumber() + " сейчас поедет на этаж " + targetFloor);
                    el.setDirection(targetFloor);
                    while (el.getCurrFloor() != targetFloor) {
                        if (floorsQueue.contains(el.getCurrFloor())){
                            System.out.println("Лифт " + el.getNumber() + " остановился на" + el.getCurrFloor());
                            floorsQueue.remove(el.getCurrFloor());
                        }
                        if ((!calls.isEmpty()) && (chooseElevator(elevators, calls.peek()) == el)) {
                            if (el.getDirection() == Direction.UP) {
                                if ((el.getCurrFloor() == calls.peek().getCurrFloor()) && (calls.peek().getCurrFloor() < targetFloor)) {
                                    floorsQueue.add(calls.poll().getFinalFloor());
                                    Thread.sleep(2000);
                                    System.out.println("Лифт " + el.getNumber() + " пока ехал остановился на " + el.getCurrFloor());
                                }

                            }
                            else if (el.getDirection() == Direction.DOWN) {
                                if ((el.getCurrFloor() == calls.peek().getCurrFloor()) && (calls.peek().getCurrFloor() > targetFloor)) {
                                    floorsQueue.add(calls.poll().getFinalFloor());
                                    Thread.sleep(2000);
                                    System.out.println("Лифт" + el.getNumber() + "пока ехал остановился на" + el.getCurrFloor());
                                }
                            }
                        }
                        el.moveElevator();

                        }
                        System.out.println("Лифт " + el.getNumber() + " доехал до этажа " + el.getCurrFloor());
                    }
                }


            } catch(InterruptedException e){
                throw new RuntimeException(e);
            }

        }

    public Elevator chooseElevator(List<Elevator> elevators, Call call){
        Elevator best = null;
        int minDist = 20;
        for (Elevator elevator : elevators){
            int distance = elevator.getCurrFloor() - call.getFinalFloor();
            if (elevator.getDirection() == Direction.STOP) {
                if (minDist > distance){
                    minDist = distance;
                    best = elevator;
                }
            }
            else if (call.getDirection() == elevator.getDirection() && call.getDirection() == Direction.UP) {
                if (minDist > distance){
                    minDist = distance;
                    best = elevator;
                }
            }
            else if(call.getDirection() == elevator.getDirection() && call.getDirection() == Direction.DOWN){
                if (minDist > distance){
                    minDist = distance;
                    best = elevator;
                }
            }
        }
        if (best == null){
            best = elevators.getFirst();
        }
        return best;

    }
}
