package bci;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a library user.
 */
public class User implements Serializable {
    
    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;
    
    private int _idUser;
    private String _name;
    private String _email;
    private String _status;
    private String _behavior; // UserBehavior
    private List<Object> _lateRequests; // List<Requests>
    private int _fines;
    private int _currentRequests;
    private List<Work> _interestWork; // List<Work>
    private List<Object> _notifications; // List<Notifications>
    
    /**
     * Constructor for User.
     * @param id user identifier
     * @param name user name
     * @param email user email
     */
    public User(int id, String name, String email) {
        _idUser = id;
        _name = name;
        _email = email;
        _status = "ACTIVO";
        _behavior = "NORMAL";
        _lateRequests = new ArrayList<>();
        _fines = 0;
        _currentRequests = 0;
        _interestWork = new ArrayList<>();
        _notifications = new ArrayList<>();
    }
    
    /**
     * Gets the user ID.
     * @return user ID
     */
    public int getId() {
        return _idUser;
    }
    
    /**
     * Gets the user name.
     * @return user name
     */
    public String getName() {
        return _name;
    }
    
    /**
     * Gets the user email.
     * @return user email
     */
    public String getEmail() {
        return _email;
    }
    
    /**
     * Gets the user status.
     * @return user status
     */
    public String getStatus() {
        return _status;
    }
    
    /**
     * Checks if user is active.
     * @return true if user is active
     */
    public boolean isActive() {
        return "ACTIVO".equals(_status);
    }
    
    /**
     * Sets user active status.
     * @param active the active status
     */
    public void setActive(boolean active) {
        _status = active ? "ACTIVO" : "SUSPENSO";
    }
    
    /**
     * Suspends the user.
     */
    public void suspend() {
        _status = "SUSPENSO";
    }
    
    /**
     * Activates the user.
     */
    public void activate() {
        _status = "ACTIVO";
    }
    
    /**
     * Gets the user's fine amount.
     * @return fine amount
     */
    public int getFines() {
        return _fines;
    }
    
    /**
     * Sets the user's fine amount.
     * @param fines the fine amount
     */
    public void setFines(int fines) {
        _fines = fines;
    }
    
    /**
     * Adds a fine to the user.
     * @param fine fine amount to add
     */
    public void addFine(int fine) {
        _fines += fine;
    }
    
    /**
     * Pays the user's fine.
     * @param amount amount to pay
     */
    public void payFine(int amount) {
        _fines = Math.max(0, _fines - amount);
    }
    
    /**
     * Gets current requests count.
     * @return current requests
     */
    public int getCurrentRequests() {
        return _currentRequests;
    }
    
    /**
     * Checks if user can borrow.
     * @return true if user can borrow
     */
    public boolean canBorrow() {
        return isActive();
    }
    
    /**
     * Gets request duration based on user behavior.
     * @param days base days
     * @return actual duration
     */
    public int getRequestDuration(int days) {
        // Implementation based on user behavior
        return days;
    }
    
    /**
     * Calculates and updates user behavior.
     */
    public void calculateAndUpdateBehavior() {
        // Implementation for behavior calculation
    }
    
    /**
     * Updates user status.
     */
    public void updateStatus() {
        // Implementation for status update
    }
    
    @Override
    public String toString() {
        if (_fines > 0) {
            return _idUser + " - " + _name + " - " + _email + " - " + _status + " - EUR " + _fines;
        } else {
            return _idUser + " - " + _name + " - " + _email + " - " + _status;
        }
    }
}