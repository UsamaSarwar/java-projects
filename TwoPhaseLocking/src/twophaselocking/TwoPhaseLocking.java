/*
Implementation of rigorous two phase locking protocol for concurrency control with the wait-die method for dealing with deadlock

Design and Implementation of the program:

I have designed two HashMaps for tracking all the transactions and a PriorityQueue to store the waiting transactions. 
We store the list of all operations in Transaction table (TT) and Lock Table (LT).

TRANSACTION TABLE: The fields to be stored are, 
• Transaction_ID 
• Transaction_state (active, blocked/waiting, aborted/cancelled, committed) 
• Transaction_timestamp 
• List of items locked by the transaction

Transaction_ID Transaction_ Timestamp Trans_State Items_locked

LOCK TABLE: The fields to be stored are, 
• Item_name 
• Lock_state(read/share locked, write/exclusive lock) 
• Transaction_id

Item_name transid_RL transid_WL trans_waiting_write trans_waiting_read
 */
package twophaselocking;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

class LockTable {

    public int transid_WL;
    public int transid_RL;
    public int trans_waiting_read;
    public int trans_waiting_write;

    //Constructor
    public LockTable() {
        this.transid_WL = 0;
        this.transid_RL = 0;
        this.trans_waiting_read = 0;
        this.trans_waiting_write = 0;
    }

    //........................Get.............................................
    public int Get_transid_RL() {

        return this.transid_RL;
    }

    public int Get_transid_WL() {

        return this.transid_WL;
    }

    public int Get_trans_waiting_read() {

        return this.trans_waiting_read;
    }

    public int Get_trans_waiting_write() {

        return this.trans_waiting_write;
    }
    //..........................Set.............................................

    public void Set_transid_WL(int x) {

        this.transid_WL = x;
    }

    public void Set_trans_waiting_read(int x) {

        String y;
        y = Integer.toString(this.trans_waiting_read) + Integer.toString(x);
        this.trans_waiting_read = Integer.parseInt(y);
    }

    public void Set_transid_RL(int x) {

        String y;
        y = Integer.toString(this.transid_RL) + Integer.toString(x);
        this.transid_RL = Integer.parseInt(y);
    }

    public void Set_trans_waiting_write(int x) {

        String y;
        y = Integer.toString(this.trans_waiting_write) + Integer.toString(x);
        this.trans_waiting_write = Integer.parseInt(y);
    }
    //.....................Remove..............................................

    public void replace_transid_RL() {
        this.transid_RL = 0;
    }

    public void replace_transid_WL() {
        this.transid_WL = 0;
    }

    public void replace_transid_waiting_RL() {
        this.trans_waiting_read = 0;
    }

    public void replace_transid_waiting__WL() {
        this.trans_waiting_write = 0;
    }
}

public class TwoPhaseLocking {

    public static HashMap<Integer, Transaction> transMap = new HashMap<Integer, Transaction>();
    public static HashMap<String, LockTable> lockMap = new HashMap<String, LockTable>();
    public static Queue<Integer> q = new PriorityQueue<Integer>();
    public static String[] data = new String[20];

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        TwoPhaseLocking obj1 = new TwoPhaseLocking();
        data = obj1.ReadFile();
        System.out.println("Implementation of Rigorous 2PL");
        Transaction T1 = new Transaction();
        T1.ReadTransactions();
    }

    public String[] ReadFile() {

        // reading file line by line in Java using BufferedReader
        FileInputStream fis = null;
        BufferedReader reader = null;
        String[] myarray;
        myarray = new String[20];
        try {
            fis = new FileInputStream("C:/Users/Manu/workspace/DB2/input.txt");
            reader = new BufferedReader(new InputStreamReader(fis));
            int i = 0;
            String line = reader.readLine();

            while (line != null) {
                myarray[i] = line;
                line = reader.readLine();
                i++;
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
        return myarray;
    }
}

class Transaction {

    public static int TS = 0;
    // public int tid;
    public int trans_timestamp;
    public String trans_state;
    public String items_locked;

    public String[] filedata = new String[100];

    //Default constructor
    public Transaction() {

    }

    public Transaction(String state, String items_locked) {
        this.trans_timestamp = ++TS;
        // TS++;
        this.trans_state = state;
        this.items_locked = items_locked;
    }

    public void ReadTransactions() {
        for (int i = 0; i < TwoPhaseLocking.data.length; i++) {
            filedata[i] = TwoPhaseLocking.data[i];
        }
        int i = 0;
        while (filedata[i] != null) {

            switch (filedata[i].substring(0, 1)) {
                case "b":
                    Transaction T = new Transaction("Active", "none");
                    int tid = Integer.parseInt(filedata[i].substring(1,
                            filedata[i].indexOf(";")));
                    TwoPhaseLocking.transMap.put(tid, T);
                    System.out.println("Begin Transaction: T" + tid);

                    break;

                case "r":
                    tid = Integer.parseInt(filedata[i].substring(1,
                            filedata[i].indexOf("(")));
                    LockTable L = new LockTable();
                    L.Set_transid_RL(tid);
                    String itemname = filedata[i].substring(
                            filedata[i].indexOf("(") + 1, filedata[i].indexOf(")"));

                    // check if exists in locktable- yes:check read/write lock
                    // No:insert into locktable
                    if (!TwoPhaseLocking.lockMap.containsKey(itemname)) {
                        TwoPhaseLocking.lockMap.put(itemname, L);
                        System.out.println("T" + tid + " has a read lock on item "
                                + itemname + '\n');
                        TwoPhaseLocking.transMap.get(tid).setItems_locked(itemname);
                    } else {
                        for (String key : TwoPhaseLocking.lockMap.keySet()) {

                            if (key.equals(itemname)) {

                                if ((TwoPhaseLocking.lockMap.get(itemname).Get_transid_WL()) != 0) {
                                    System.out.println("Item " + key
                                            + " is Writelocked and not available!"
                                            + '\n');
                                    // System.out.println("Transaction "+tid+" is blocked");
                                    // T1.setTrans_state("Blocked");
                                    enqueue(tid);
                                    // TwoPhaseLocking.transMap.put(tid, T1);
                                    TwoPhaseLocking.transMap.get(tid).setTrans_state(
                                            "Blocked");
                                    // L.Set_trans_waiting_write(tid);
                                    // TwoPhaseLocking.lockMap.put(itemname,L);
                                    TwoPhaseLocking.lockMap.get(itemname)
                                            .Set_trans_waiting_write(tid);
                                }
                                if ((TwoPhaseLocking.lockMap.get(itemname).Get_transid_RL()) != 0) {
                                    // L.Set_transid_RL(tid);
                                    TwoPhaseLocking.lockMap.get(itemname).Set_transid_RL(tid);
                                    System.out.println("T" + tid
                                            + " has a read lock on item "
                                            + itemname + '\n');
                                    TwoPhaseLocking.transMap.get(tid).setItems_locked(
                                            itemname);
                                }
                            }

                        }
                    }

                    break;

                case "w":
                    String itemname1 = filedata[i].substring(
                            filedata[i].indexOf("(") + 1, filedata[i].indexOf(")"));
                    tid = Integer.parseInt(filedata[i].substring(1,
                            filedata[i].indexOf("(")));

                    LockTable L1 = new LockTable();
                    L1.Set_transid_WL(tid);

                    if (!TwoPhaseLocking.lockMap.containsKey(itemname1)) {
                        TwoPhaseLocking.lockMap.put(itemname1, L1);
                        System.out.println("T" + tid + " has a write lock on item "
                                + itemname1 + '\n');
                    } else {
                        for (String key : TwoPhaseLocking.lockMap.keySet()) {
                            if (key.equals(itemname1)) {
                                // for (LockTable value : TwoPhaseLocking.lockMap.values())
                                // {
                                // if(value.transid_WL != 0)
                                // //No write lock on item
                                // {
                                if ((TwoPhaseLocking.lockMap.get(itemname1).Get_transid_WL()) != 0) {
                                    int timestamp_requesting_trans = TwoPhaseLocking.transMap
                                            .get(tid).getTrans_timestamp();
                                    int timestamp_itemHolding_trans = TwoPhaseLocking.lockMap
                                            .get(itemname1).Get_transid_WL();
                                    // check wait-die and move transaction to
                                    // queue,change state to block in transaction
                                    // table
                                    // add entry in lock table for waiting
                                    // transaction
                                    if (timestamp_requesting_trans < timestamp_itemHolding_trans) {
                                        enqueue(tid);
                                        TwoPhaseLocking.transMap.get(tid).setTrans_state(
                                                "Blocked");
                                        TwoPhaseLocking.lockMap.get(itemname1)
                                                .Set_trans_waiting_write(tid);
                                    }
                                } else {
                                    if ((TwoPhaseLocking.lockMap.get(itemname1)
                                            .Get_transid_RL()) != 0) // checking if read lock already exists
                                    {
                                        int len_transid_RL = (int) (Math
                                                .log10(TwoPhaseLocking.lockMap.get(itemname1)
                                                        .Get_transid_RL()) + 1);
                                        if (len_transid_RL < 2
                                                && TwoPhaseLocking.lockMap.get(itemname1)
                                                        .Get_transid_RL() == tid) {
                                            System.out
                                                    .println("T"
                                                            + tid
                                                            + " is Upgrading readlock to writelock on item "
                                                            + itemname1 + '\n');
                                            TwoPhaseLocking.lockMap.get(itemname1)
                                                    .replace_transid_RL();
                                            TwoPhaseLocking.lockMap.get(itemname1)
                                                    .Set_transid_WL(tid);

                                        }
                                    }

                                }
                                // }
                            }
                        }
                    }
                    break;

                case "e":
                    tid = Integer.parseInt(filedata[i].substring(1, 2));
                    if (TwoPhaseLocking.transMap.get(tid).getTrans_state().equals("Blocked")) {
                        System.out.println("Transaction " + tid + " is Committed"
                                + '\n');
                    }
                    if (TwoPhaseLocking.transMap.get(tid).getTrans_state().equals("Aborted")) {
                        System.out.println("Ignore!Transaction is Aborted");
                    }
                    if (TwoPhaseLocking.transMap.get(tid).getTrans_state().equals("Active")) {

                        System.out.println("Transaction " + tid + " is Committed"
                                + '\n');
                        release(tid);
                        // Call release function to release all items held by
                        // transaction
                    }

                    break;
            }
            i++;
        }
    }

    public String getItems_locked() {

        return this.items_locked;
    }

    public String getTrans_state() {

        return this.trans_state;
    }

    public int getTrans_timestamp() {

        return this.trans_timestamp;
    }

    // .....................................................
    // public void setTrans_timestamp() {
    //
    // this.trans_timestamp = timestamp + 1;
    // }
    public void setItems_locked(String item) {
        if (this.items_locked.equals("none")) {
            this.items_locked = item;
        } else {
            this.items_locked = this.items_locked + item;
        }
    }

    public void setTrans_state(String state) {

        this.trans_state = state;
    }

    public void enqueue(int tid) {
        TwoPhaseLocking.q.add(tid);
    }

    public void dequeue(int tid) {
        TwoPhaseLocking.q.remove(tid);
    }

    public void release(int tid) {
        String items_locked = TwoPhaseLocking.transMap.get(tid).getItems_locked();

        if (items_locked.equals("none")) {
            System.out.println("All items released");
        } else {
            TwoPhaseLocking.transMap.get(tid).replaceItems_locked();
            TwoPhaseLocking.transMap.get(tid).setItems_locked("none");
            TwoPhaseLocking.transMap.get(tid).setTrans_state("Committed");
            System.out.println("All items released by the transaction T" + tid);
            TwoPhaseLocking.q.remove(tid);
        }
    }

    private void replaceItems_locked() {
        // TODO Auto-generated method stub
        this.items_locked = "";
    }

    // .............................Function used to debug the
    // code....................................................
    // public static void display()
    // {
    // for (Integer key : TwoPhaseLocking.transMap.keySet())
    // {
    // System.out.println("Transaction ID(Key) = " + key);
    //
    // System.out.println("Timestamp = " +
    // TwoPhaseLocking.transMap.get(key).getTrans_timestamp());
    // System.out.println("Trans_state = " +
    // TwoPhaseLocking.transMap.get(key).getTrans_state());
    // System.out.println("Items_locked = " +
    // TwoPhaseLocking.transMap.get(key).getItems_locked()+'\n'+'\n');
    // }
    //
    //
    // // for (Transaction value : TwoPhaseLocking.transMap.values()) {
    // // System.out.println("Timestamp = " + value.trans_timestamp);
    // // System.out.println("state = " + value.trans_state);
    // // System.out.println("Items_locked = " + value.items_locked+'\n');
    // // }
    //
    // for (String key : TwoPhaseLocking.lockMap.keySet())
    // {
    // System.out.println("Item Name(Key) = " + key);
    //
    // System.out.println("Write locked Trans = " +
    // TwoPhaseLocking.lockMap.get(key).Get_transid_WL());
    // System.out.println("Read locked Trans = " +
    // TwoPhaseLocking.lockMap.get(key).Get_transid_RL()+'\n'+'\n');
    // }
    //
    // // for (LockTable value : TwoPhaseLocking.lockMap.values()) {
    // // System.out.println("Read locked Trans = " + value.transid_RL);
    // // System.out.println("Write locked Trans = " + value.transid_WL);
    // // System.out.println("Waiting for read = " +
    // value.trans_waiting_read+'\n');
    // // System.out.println("Waiting for read = " +
    // value.trans_waiting_write+'\n');
    // // }
    // //System.out.println(TwoPhaseLocking.map.get(active_trans));
    // }
    // ................................Function used to debug the
    // code...................................................
}
