package deadlockassignment;

class account {

    public int number;
    public int money;

    account(int number, int money) {
        this.number = number;
        this.money = money;
    }
}

class transaction_1 {

    public account accountA = new account(1, 500);
    public account accountB = new account(2, 500);

    void withdraw(int money) {
        accountA.money = accountA.money - money;
    }

    void deposite(int money) {
        accountB.money = accountB.money + money;

    }

    void transaction(int money) {
        withdraw(money);
        deposite(money);
    }
}

class transaction_2 extends transaction_1 {

    void withdraw(int money) {
        accountB.money = accountB.money - money;
    }

    void deposite(int money) {
        accountA.money = accountA.money + money;

    }

    void transaction(int money) {
        withdraw(money);
        deposite(money);
    }
}

class Run {

    transaction_1 t1 = new transaction_1();
    transaction_2 t2 = new transaction_2();

    public void Transaction1() {
        synchronized (t1) {
            synchronized (t2) {
                t1.transaction(25);

                System.out.println("Transaction 1 Successful!");
                System.out.println("Money in Account A: " + t1.accountA.money);
                System.out.println("Money in Account B: " + t1.accountB.money);
            }
        }
    }

    public void Transaction2() {
        synchronized (t2) {
            synchronized (t1) {
                t2.transaction(50);
                System.out.println("Transaction 2 Successful!");
                System.out.println("Money in Account A: " + t2.accountA.money);
                System.out.println("Money in Account B: " + t2.accountB.money);

            }
        }
    }

    void run() {

        new Thread(() -> {
            Transaction1();
        }).start();

        new Thread(() -> {
            Transaction2();
        }).start();

    }
}

public class DeadlockAssignment {

    public static void main(String[] args) {
        Run r = new Run();
        System.out.println("Money in Account A: $500");
        System.out.println("Money in Account B: $500");

        r.run();
    }

}
