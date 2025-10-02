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

/** Class that represents the library as a whole. */
public class Library implements Serializable {
    private boolean _changed = false;
    private int _currentDate = 0;
    private Map<Integer, User> _users = new HashMap<>();

    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;

    /**
     * Read the text input file at the beginning of the program and populates the
     * instances of the various possible types (books, DVDs, users).
     *
     * @param filename name of the file to load
     * @throws UnrecognizedEntryException
     * @throws IOException
     * FIXME maybe other exceptions
     */
    void importFile(String filename) throws UnrecognizedEntryException, IOException {
      //try (BufferedReader reader = new BufferedReader(new FileReader(filename)))
    }

    /** 
     * Set Library to changed. 
     */
    public void changed() {
        setChanged(true);
    }
    
    /** 
     * @return boolean changed status
     */
    public boolean getChanged() {
        return _changed;
    }

    /** 
     * @param changed
     */
    public void setChanged(boolean changed) {
        _changed = changed;
    }

    /**
     * Gets the current date.
     * @return current date
     */
    public int getCurrentDate() {
        return _currentDate;
    }

    /**
     * Advances the current date by the specified number of days.
     * @param days number of days to advance
     */
    public void advanceDate(int days) {
        if (days > 0) {
            _currentDate += days;
            setChanged(true);
        }
    }

    public int getCurrentUserID() {
        return _users.size() + 1;
    }

    /**
     * Registers a new user in the library.
     * @param id user identifier
     * @param name user name
     * @param email user email
     */
    public void registerUser(String name, String email) {
        int id = getCurrentUserID();
        if (_users.containsKey(id)) {
            // User already exists
            return;
        }
        User user = new User(id, name, email);
        _users.put(id, user);
        setChanged(true);
    }

    /**
     * Gets a user by ID.
     * @param userId user identifier
     * @return the user or null if not found
     */
    public User getUser(int userId) {
        return _users.get(userId);
    }

    /**
     * Gets all users.
     * @return list of all users
     */
    public List<User> getAllUsers() {
        return new ArrayList<>(_users.values());
    }

}
