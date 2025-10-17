package bci;

import bci.work.Work;

/**
 * Notification sent when a work is borrowed
 */
public class BorrowingNotification extends Notification {
    
    @java.io.Serial
    private static final long serialVersionUID = 202507171005L;
    
    private Work _work;
    
    public BorrowingNotification(int timestamp, Work work) {
        super(timestamp);
        _work = work;
    }
    
    public Work getWork() {
        return _work;
    }
    
    @Override
    public String getNotificationMessage() {
        return "REQUISIÇÃO: " + _work.toString();
    }
}