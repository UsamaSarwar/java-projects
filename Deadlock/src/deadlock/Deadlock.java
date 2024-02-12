package deadlock;

/*
Let say Transaction 1 is Tread 1 and Transaction 2 is Thred 2
Transactions 1 and 2 execute concurrently.  
Transaction  1 transfers $25 from account A to account B, and 
Transaction 2 transfers $50 from account B to account A
 */
class user {

    int AccountNumber;
    double CurrentAmount = 1000;

    user(int AccountNumber) {
        this.AccountNumber = AccountNumber;
    }
}

/*
    Transaction  1 transfers $25 from account A to account B
 */
class transaction1 {

    user A = new user(1);
    user B = new user(2);

    void withdraw(double CurrentAmount) {
        A.CurrentAmount = A.CurrentAmount - CurrentAmount;
    }

    void deposite(double CurrentAmount) {
        B.CurrentAmount = B.CurrentAmount + CurrentAmount;

    }

    void transaction(double CurrentAmount) {
        withdraw(CurrentAmount);
        deposite(CurrentAmount);
    }
}

/*
    Transaction 2 transfers $50 from account B to account A
 */
class transaction2 {

    user A = new user(1);
    user B = new user(2);

    void withdraw(double CurrentAmount) {
        B.CurrentAmount = B.CurrentAmount - CurrentAmount;
    }

    void deposite(double CurrentAmount) {
        A.CurrentAmount = A.CurrentAmount + CurrentAmount;
    }

    void transaction(double CurrentAmount) {
        withdraw(CurrentAmount);
        deposite(CurrentAmount);
    }
}

class LockTransaction {

    transaction1 t1 = new transaction1();
    transaction2 t2 = new transaction2();

    /*
    Synchronized method is used to lock an object for any shared resource. 
    When a thread invokes a synchronized method, 
    it automatically acquires the lock for that object and releases it when the thread completes its task.
     */
    
    public void Transaction1() {
        while (true) {
            synchronized (t1) {
                synchronized (t2) {
                    t1.transaction(25);
                    System.out.println("Transaction 1 Processing...");
                }
            }
        }

    }

    public void Transaction2() {
        while (true) {
            synchronized (t2) {
                synchronized (t1) {
                    t2.transaction(50);
                    System.out.println("Transaction 2 Processing...");
                }
            }
        }

    }
}

class DoTransaction {

    void doTransaction() {
        LockTransaction lockTransaction = new LockTransaction();

        new Thread(() -> {
            lockTransaction.Transaction1();
        }).start();

        new Thread(() -> {
            lockTransaction.Transaction2();

        }).start();
    }
}

public class Deadlock {

    static Solution s = new Solution();

    public static void main(String[] args) {

        DoTransaction t = new DoTransaction();
        try {
            t.doTransaction();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

}
