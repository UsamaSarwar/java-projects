package deadlock;

public class Solution {

    Thread1 t1 = new Thread1();
    Thread2 t2 = new Thread2();

    public void run() {
        new Thread(() -> {
            t1.run();
        });
        new Thread(() -> {
            t2.run();
        });
    }

}

class Thread1 {

    String Thread1 = "Thred 1 is executing";
    void run(){
        System.out.println(Thread1);
    }

}

class Thread2 {

    String Thread2 = "Thread 2 is executing";
     void run(){
        System.out.println(Thread2);
    }
}
