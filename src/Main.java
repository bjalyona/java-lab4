import java.util.LinkedList;
import java.util.Queue;


public class Main {
    public static void main(String[] args) {
        Queue<Call> calls = new LinkedList<>();
        CallsGenerator callsGenerator= new CallsGenerator(calls, 0, 20);
        Thread thread1 = new Thread(callsGenerator);
        thread1.start();

        Control control = new Control(calls);
        Thread thread2 = new Thread(control);
        thread2.start();


    }
}