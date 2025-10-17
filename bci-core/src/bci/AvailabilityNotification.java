package bci;

import bci.work.Work;

/**
 * Notification sent when a work becomes available
 */
public class AvailabilityNotification extends Notification {
    
    @java.io.Serial
    private static final long serialVersionUID = 202507171006L;
    
    private Work _work;
    
    public AvailabilityNotification(int timestamp, Work work) {
        super(timestamp);
        _work = work;
    }
    
    public Work getWork() {
        return _work;
    }
    
    @Override
    public String getNotificationMessage() {
        return "DISPONIBILIDADE: " + _work.toString();
    }
}