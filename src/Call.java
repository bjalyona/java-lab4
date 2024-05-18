public class Call {
    private int currFloor;
    private int finalFloor;
    private Direction direction;
    Call(int currFloor, int finalFloor, Direction direction){
        this.currFloor = currFloor;
        this.finalFloor = finalFloor;
        this.direction = direction;

    }
    public int getCurrFloor(){
        return currFloor;
    }
    public int getFinalFloor(){
        return finalFloor;
    }
    public Direction getDirection(){
        return direction;
    }
}
