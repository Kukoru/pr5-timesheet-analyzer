package ru.edu.pr02;

public class SelfCheck {
    public static void main(String[] args) {
        boolean ok = true;

        int[] typical = {8, 8, 8, 8, 8};
        ok &= TimesheetAnalyzer.total(typical) == 40;
        ok &= TimesheetAnalyzer.average(typical) == 8.0;
        ok &= TimesheetAnalyzer.overtimeDays(typical) == 0;
        ok &= TimesheetAnalyzer.weeklyOvertime(typical) == 0;

        int[] parsed = TimesheetAnalyzer.parseHours("9;8;7;10;8");
        ok &= parsed.length == 5;
        ok &= parsed[0] == 9 && parsed[1] == 8;

        try {
            TimesheetAnalyzer.parseHours("8;8;17;8;8");
            ok = false;
        } catch (IllegalArgumentException e) {}

        try {
            TimesheetAnalyzer.parseHours("8;abc;8;8;8");
            ok = false;
        } catch (IllegalArgumentException e) {}

        try {
            TimesheetAnalyzer.parseHours("8;8;8;8");
            ok = false;
        } catch (IllegalArgumentException e) {}

        try {
            TimesheetAnalyzer.parseHours("");
            ok = false;
        } catch (IllegalArgumentException e) {}

        System.out.println(ok ? "PASS" : "FAIL  complete TODO methods");
    }
}