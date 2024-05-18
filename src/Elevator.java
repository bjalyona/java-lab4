import java.util.LinkedList;
import java.util.Queue;


public class Elevator{
    private int number;
    private int currFloor;
    private Direction direction;
    private int goalFloor;
    private Queue<Call> calls;
    private Queue<Integer> floorsQueue;

    public Elevator(int number) {
        this.number = number;
        this.currFloor = 0;
        this.direction = Direction.STOP;
        this.calls = new LinkedList<>();
        this.floorsQueue = new LinkedList<>();
    }
    public int getCurrFloor(){
        return currFloor;
    }
    public int getNumber(){
        return number;
    }
    public Direction getDirection(){
        return direction;
    }
    public Queue<Call> getCalls(){
        return calls;
    }

    public void moveElevator() throws InterruptedException {
        if (this.direction == Direction.UP){
            Thread.sleep(500);
            currFloor++;
        }
        else if (this.direction == Direction.DOWN){
            Thread.sleep(500);
            currFloor--;
        }
    }
    public void setDirection(int floor){
        if (currFloor < floor){
            direction = Direction.UP;
        }
        else{
            direction = Direction.DOWN;
        }
    }
}
