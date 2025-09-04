package bci.app.main;

public interface Message {
    static String currentDate(int date) {
        return "Data actual: " + date;
    }
}
