package bci;

import bci.exceptions.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

/** Class that represents the library as a whole. */
public class Library implements Serializable {
    private boolean _changed = false;
    private int _currentDate = 0;
    private Map<Integer, User> _users = new HashMap<>();
    private Map<Integer, Work> _works = new HashMap<>();

    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;

    void importFile(String filename) throws UnrecognizedEntryException, IOException {
      //try (BufferedReader reader = new BufferedReader(new FileReader(filename)))
    }

    public void changed() {
        setChanged(true);
    }
    
    public boolean getChanged() {
        return _changed;
    }

    public void setChanged(boolean changed) {
        _changed = changed;
    }

    public int getCurrentDate() {
        return _currentDate;
    }

    public void advanceDate(int days) {
        if (days > 0) {
            _currentDate += days;
            setChanged(true);
        }
    }

    public int getCurrentUserID() {
        return _users.size() + 1;
    }

    public void registerUser(String name, String email) {
        int id = getCurrentUserID();
        if (_users.containsKey(id)) {
            return;
        }
        User user = new User(id, name, email);
        _users.put(id, user);
        setChanged(true);
    }

    public User getUser(int userId) {
        return _users.get(userId);
    }

    public List<String> showUsers() {
        return _users.values().stream()
            .sorted(Comparator.comparing(User::getName).thenComparing(User::getIdUser))
            .map(User::toString)
            .collect(Collectors.toList());
    }


    public int getCurrentWorkID() {
        return _users.size() + 1;
    }

    public Work getWork(int WorkId) {
        return _works.get(WorkId);
    }
    
    /**
     * Changes the inventory of a work by the specified amount.
     * @param workId the work identifier
     * @param amount the amount to change (positive to add, negative to remove)
     * @return true if successful, false if work doesn't exist or not enough inventory
     */
    public void changeWorkInventory(int workId, int amount) {
        Work work = _works.get(workId);
        if (work != null) {
            work.changeInventory(amount);
        if (amount>0) {
            setChanged(true);
        }
        }
    }

}