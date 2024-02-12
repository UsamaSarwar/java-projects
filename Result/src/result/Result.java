package result;

import javax.swing.*;

public class Result {

    int rollNo;
    String name;
    int[] marks = new int[3];

    void input() {
        rollNo = Integer.parseInt(JOptionPane.showInputDialog("Enter Roll No. ", rollNo));
        name = JOptionPane.showInputDialog("Enter Name ", name);
        for (int i = 0; i < 3; i++) {
            marks[i] = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Subject " + (i + 1) + " Marks", marks[i]));
        }
    }

    void show() {
        JOptionPane.showMessageDialog(null, "Roll No: " + rollNo + "\nName: " + name + "\nMarks\nSubject 1: " + marks[0] + "\nSubject 2: " + marks[1] + "\nSubject 3: " + marks[2]);

    }

    double total() {
        return marks[0] + marks[1] + marks[2];
    }

    double avg() {
        return (marks[0] + marks[1] + marks[2]) / 3;
    }

    public static void main(String[] args) {
        Result r = new Result();
        r.input();
        r.show();
        JOptionPane.showMessageDialog(null, "Total Marks: " + r.total() + "\nAverage Marks: " + r.avg());
    }

}
