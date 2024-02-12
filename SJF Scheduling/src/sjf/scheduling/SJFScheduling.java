package sjf.scheduling;

import java.util.Scanner;
import static javafx.application.Platform.exit;

public class SJFScheduling {
// Java Program for Shortest Job First (SRTF) Scheduling (Preemptive)

    static Scanner sc = new Scanner(System.in);

    public void Preemptive() {

        System.out.print("Number of Process: ");
        int n = sc.nextInt();
        int pid[] = new int[n]; // it takes pid of process
        int at[] = new int[n]; // at means arrival time
        int bt[] = new int[n]; // bt means burst time
        int ct[] = new int[n]; // ct means complete time
        int ta[] = new int[n]; // ta means turn around time
        int wt[] = new int[n];  // wt means waiting time
        int f[] = new int[n];  // f means it is flag it checks process is completed or not
        int k[] = new int[n];   // it is also stores brust time
        int i, st = 0, tot = 0;
        float avgwt = 0, avgta = 0;

        for (i = 0; i < n; i++) {
            pid[i] = i + 1;
            System.out.print("Process " + (i + 1) + " Arrival Time:");
            at[i] = sc.nextInt();
            System.out.print("Process " + (i + 1) + " Burst Time:");
            bt[i] = sc.nextInt();
            k[i] = bt[i];
            f[i] = 0;
        }

        while (true) {
            int min = 99, c = n;
            if (tot == n) {
                break;
            }

            for (i = 0; i < n; i++) {
                if ((at[i] <= st) && (f[i] == 0) && (bt[i] < min)) {
                    min = bt[i];
                    c = i;
                }
            }

            if (c == n) {
                st++;
            } else {
                bt[c]--;
                st++;
                if (bt[c] == 0) {
                    ct[c] = st;
                    f[c] = 1;
                    tot++;
                }
            }
        }

        for (i = 0; i < n; i++) {
            ta[i] = ct[i] - at[i];
            wt[i] = ta[i] - k[i];
            avgwt += wt[i];
            avgta += ta[i];
        }

        System.out.println("PID\tArrival\tBurst\tComplete\tTurn\tWaiting");
        for (i = 0; i < n; i++) {
            System.out.println(pid[i] + "\t" + at[i] + "\t" + k[i] + "\t" + ct[i] + "\t\t" + ta[i] + "\t" + wt[i]);
        }

        System.out.println("\nAverage TAT is " + (float) (avgta / n));
        System.out.println("Average Waiting Time is " + (float) (avgwt / n));
        sc.close();
    }

    public void NonPreemptive() {

        System.out.print("Number of Processes: ");
        int n = sc.nextInt();
        int pid[] = new int[n];
        int at[] = new int[n]; // at means arrival time
        int bt[] = new int[n]; // bt means burst time
        int ct[] = new int[n]; // ct means complete time
        int ta[] = new int[n]; // ta means turn around time
        int wt[] = new int[n];  //wt means waiting time
        int f[] = new int[n];  // f means it is flag it checks process is completed or not
        int st = 0, tot = 0;
        float avgwt = 0, avgta = 0;

        for (int i = 0; i < n; i++) {
            System.out.print("Process " + (i + 1) + " Arrival Time:");
            at[i] = sc.nextInt();
            System.out.print("Process " + (i + 1) + " Brust Time:");
            bt[i] = sc.nextInt();
            pid[i] = i + 1;
            f[i] = 0;
        }

        boolean a = true;
        while (true) {
            int c = n, min = 999;
            if (tot == n) // total no of process = completed process loop will be terminated
            {
                break;
            }

            for (int i = 0; i < n; i++) {
                /*
				 * If i'th process arrival time <= system time and its flag=0 and burst<min 
				 * That process will be executed first 
                 */
                if ((at[i] <= st) && (f[i] == 0) && (bt[i] < min)) {
                    min = bt[i];
                    c = i;
                }
            }

            /* If c==n means c value can not updated because no process arrival time< system time so we increase the system time */
            if (c == n) {
                st++;
            } else {
                ct[c] = st + bt[c];
                st += bt[c];
                ta[c] = ct[c] - at[c];
                wt[c] = ta[c] - bt[c];
                f[c] = 1;
                tot++;
            }
        }

        System.out.println("\nPid\tArrival\tBrust\tComplete\tTurn\tWaiting");
        for (int i = 0; i < n; i++) {
            avgwt += wt[i];
            avgta += ta[i];
            System.out.println(pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" + ct[i] + "\t\t" + ta[i] + "\t" + wt[i]);
        }
        System.out.println("\nAverage TAT is " + (float) (avgta / n));
        System.out.println("Average Waiting Time is " + (float) (avgwt / n));
        sc.close();
    }

    public void choice() {

        int choice;

        System.out.println("\nChoose Algorithm\n1. Preemtive Algorithm\n2. Non-Preemtive Algorithm\n3. Exit");
        choice = sc.nextInt();

        switch (choice) {
            case 1:
                Preemptive();
                break;
            case 2:
                NonPreemptive();
                break;
            case 3:
                exit();
                System.out.println("\nGood Bye!");
                break;
            default:
                System.out.println("Wrong Input\ni.e 1");
                choice();
                break;
        }
    }

    public static void main(String[] args) {
        SJFScheduling SJF = new SJFScheduling();
        SJF.choice();

    }
}
/*
Kernel Mode
In Kernel mode, the executing code has complete and unrestricted access to the underlying hardware. 
It can execute any CPU instruction and reference any memory address. Kernel mode is generally reserved 
for the lowest-level, most trusted functions of the operating system. Crashes in kernel mode are catastrophic; 
they will halt the entire PC.

User Mode
In User mode, the executing code has no ability to directly access hardware or reference memory. Code running 
in user mode must delegate to system APIs to access hardware or memory. Due to the protection afforded by this 
sort of isolation, crashes in user mode are always recoverable. Most of the code running on your computer will 
execute in user mode.
*/
