package bci;

import java.io.Serializable;

/**
 * Abstract notification class
 */
public abstract class Notification implements Serializable {
    
    @java.io.Serial
    private static final long serialVersionUID = 202507171004L;
    
    private int _timestamp;
    
    public Notification(int timestamp) {
        _timestamp = timestamp;
    }
    
    public int getTimestamp() {
        return _timestamp;
    }
    
    /**
     * Gets the notification message to display
     * @return formatted notification string
     */
    public abstract String getNotificationMessage();
}