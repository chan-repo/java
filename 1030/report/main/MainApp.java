package week6.report.main;

import week6.report.utill.Calc;

public class MainApp {

    public static void main(String[] args) {
        Calc c = new Calc(10, 20);
        System.out.println(c.sum());
    }
}
