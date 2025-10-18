package bci.user;

import bci.user.behaviorInterface.*;
import bci.Notification;
import bci.Request;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class User implements Serializable {
    
    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;
    
    private int _idUser;
    private String _name;
    private String _email;
    private String _status;
    private UserBehavior _behavior; 
    private int _consecutiveLate; 
    private int _fines;
    private int _currentRequests;
    private int _consecutiveOnTime;
    private List<Integer> _interestWork; 
    private List<Notification> _notifications; 
    

    public User(int id, String name, String email) {
        _idUser = id;
        _name = name;
        _email = email;
        _status = "ACTIVO";
        _behavior = new Normal();
        _consecutiveLate = 0;
        _fines = 0;
        _currentRequests = 0;
        _consecutiveOnTime = 0;
        _interestWork = new ArrayList<>();
        _notifications = new ArrayList<>();
    }

    public void suspend() {
        _status = "SUSPENSO";
    }
    
    public void activate() {
        _status = "ACTIVO";
    }

    public boolean isSuspended() {
        return "SUSPENSO".equals(_status);
    }
    
    public void addLateRequest(Request request) {
        _consecutiveLate += 1;
    }

    public void addConsecutiveOnTime(Request request) {
        _consecutiveOnTime += 1;
    }
    
    public void addFine(int value) {
        _fines += value;
    }

    /**
     * Registra uma devolução e atualiza os contadores de comportamento
     * @param wasOnTime true se a devolução foi feita no prazo
     */
    public void recordReturn(boolean wasOnTime) {
        if (wasOnTime) {
            _consecutiveOnTime++;
            _consecutiveLate = 0; // Reset late requests counter
        } else {
            _consecutiveLate++;
            _consecutiveOnTime = 0; // Reset consecutive on time counter
        }
        calculateAndUpdateBehavior();
    }
    
    public boolean canBorrow() {
        return "ACTIVO".equals(_status);
    }
    
    public boolean canBorrowExpensive() {
        return _behavior.canBorrowExpensive();
    }
    
    public void calculateAndUpdateBehavior() {
        // Cumpridor: últimas 5 requisições rigorosamente no prazo
        if (_consecutiveOnTime >= 5) {
            _behavior = new Dutiful();
        } 
        // Faltoso: últimas 3 requisições atrasadas  
        else if (_consecutiveLate >= 3) {
            _behavior = new Overdue();
        } 
        // Normal: utente faltoso que fez 3 devoluções consecutivas no prazo, ou casos gerais
        else if (_behavior instanceof Overdue && _consecutiveOnTime >= 3) {
            _behavior = new Normal();
        } 
        // Todos os outros casos: normal
        else {
            _behavior = new Normal();
        }
    }
    
    /**
     * Atualiza o status do utente baseado em multas e obras em atraso.
     * Utente é suspenso se:
     * - Tiver multas por pagar, OU
     * - Tiver obras requisitadas fora do prazo de entrega
     */
    public void updateStatus() {
        if (_fines > 0) {
            this.suspend();
        }
    }
    
    public void payFine(int amount) {
        if (amount >= _fines) {
            _fines = 0;
            this.activate();
        }
    }
    
    /**
     * Adds a notification to the user's notification list
     * @param notification the notification to add
     */
    public void addNotification(Notification notification) {
        _notifications.add(notification);
    }
    
    /**
     * Gets all user notifications and clears the list
     * @return list of notifications in the order they were received
     */
    public List<Notification> getAndClearNotifications() {
        List<Notification> notifications = new ArrayList<>(_notifications);
        _notifications.clear();
        return notifications;
    }
    
    /**
     * Adds a work to the user's interest list for availability notifications
     * @param workId the ID of the work the user is interested in
     */
    public void addInterestWork(int workId) {
        if (!_interestWork.contains(workId)) {
            _interestWork.add(workId);
        }
    }
    
    /**
     * Removes a work from the user's interest list
     * @param workId the ID of the work to remove from interests
     */
    public void removeInterestWork(int workId) {
        _interestWork.remove(Integer.valueOf(workId));
    }

    // ========== GETTERS ==========
    
    public int getIdUser() {
        return _idUser;
    }
    
    public String getName() {
        return _name;
    }
    
    public String getEmail() {
        return _email;
    }
    
    public String getStatus() {
        return _status;
    }

    public String getBehavior() {
        String className = _behavior.getClass().getSimpleName();
        switch (className) {
            case "Dutiful":
                return "CUMPRIDOR";
            case "Overdue":
                return "FALTOSO";
            case "Normal":
            default:
                return "NORMAL";
        }
    }
    
    public UserBehavior getBehaviorObject() {
        return _behavior;
    }
    
    public int getFines() {
        return _fines;
    }
    
    public int getCurrentRequests() {
        return _currentRequests;
    }
    
    public int getMaxAllowedWorks() {
        return _behavior.getMaxAllowedWorks();
    }
    
    public int getMaxAllowedRequestDuration() {
        return _behavior.getMaxAllowedRequestDuration();
    }
    
    public int getRequestDuration() {
        return _behavior.getMaxAllowedRequestDuration();
    }

    public int getCurrentOnTime() {
        return _consecutiveOnTime;
    }
    
    /**
     * Gets the list of works the user is interested in
     * @return list of work IDs
     */
    public List<Integer> getInterestWorks() {
        return new ArrayList<>(_interestWork);
    }

    // ========== SETTERS ==========
    
    public void setIdUser(int idUser) {
        this._idUser = idUser;
    }

    public void setName(String name) {
        this._name = name;
    }

    public void setEmail(String email) {
        this._email = email;
    }

    public void setStatus(String status) {
        this._status = status;
    }

    public void setBehavior(UserBehavior behavior) {
        this._behavior = behavior;
    }

    public void setFines(int fines) {
        this._fines = fines;
    }

    public void setCurrentRequests(int currentRequests) {
        this._currentRequests = currentRequests;
    }

    public void setConsecutiveOnTime(int consecutiveOnTime) {
        _consecutiveOnTime = consecutiveOnTime;
    } 

    // ========== TOSTRING ==========
    
    @Override
    public String toString() {
        String behaviorName = getBehavior();
        if ("SUSPENSO".equals(_status)) {
            return _idUser + " - " + _name + " - " + _email + " - " + behaviorName + " - " + _status + " - EUR " + _fines;
        } else {
            return _idUser + " - " + _name + " - " + _email + " - " + behaviorName + " - " + _status;
        }
    }
}
