package bci;

import bci.exceptions.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.io.BufferedReader;
import java.io.FileReader;

/** Class that represents the library as a whole. */
public class Library implements Serializable {

    private boolean _changed = false;

    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;

    // Collections to store users and works
    private Map<Integer, User> _users;
    private Map<Integer, Work> _works;
    private Map<String, Creator> _creators;
    private int _nextUserId;
    private int _nextWorkId;
    private int _currentDate;

    /**
     * Default constructor.
     */
    public Library() {
        _users = new HashMap<>();
        _works = new HashMap<>();
        _creators = new HashMap<>();
        _nextUserId = 1;
        _nextWorkId = 1;
        _currentDate = 0;
        _changed = false;
    }

    /**
     * Registers a new user.
     * @param name user name
     * @param email user email
     * @return the new user's ID
     */
    public int registerUser(String name, String email) {
        int userId = _nextUserId++;
        User user = new User(userId, name, email);
        _users.put(userId, user);
        setChanged(true);
        return userId;
    }

    /**
     * Gets a user by ID.
     * @param userId user ID
     * @return the user, or null if not found
     */
    public User getUser(int userId) {
        return _users.get(userId);
    }

    /**
     * Gets all users sorted by ID.
     * @return list of all users
     */
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>(_users.values());
        userList.sort(Comparator.comparingInt(User::getId));
        return userList;
    }

    /**
     * Gets a work by ID.
     * @param workId work ID
     * @return the work, or null if not found
     */
    public Work getWork(int workId) {
        return _works.get(workId);
    }

    /**
     * Gets all works sorted by ID.
     * @return list of all works
     */
    public List<Work> getAllWorks() {
        List<Work> workList = new ArrayList<>(_works.values());
        workList.sort(Comparator.comparingInt(Work::getId));
        return workList;
    }

    /**
     * Gets all works by a specific creator.
     * @param creatorId creator identifier
     * @return list of works by creator, or empty list if creator not found
     */
    public List<Work> getWorksByCreator(String creatorId) {
        Creator creator = _creators.get(creatorId);
        if (creator == null) {
            return new ArrayList<>();
        }
        return creator.getWorks();
    }

    /**
     * Checks if a creator exists.
     * @param creatorId creator identifier
     * @return true if creator exists
     */
    public boolean hasCreator(String creatorId) {
        return _creators.containsKey(creatorId);
    }

    /**
     * Adds a work to the library.
     * @param work the work to add
     */
    private void addWork(Work work) {
        _works.put(work.getId(), work);
        
        // Add creator and work to creator index
        Creator creator = work.getCreator();
        _creators.putIfAbsent(creator.getName(), creator);
        creator.addWork(work);
        
        setChanged(true);
    }

    /**
     * Gets current date.
     * @return current date
     */
    public int getCurrentDate() {
        return _currentDate;
    }

    /**
     * Advances the current date.
     * @param days number of days to advance
     */
    public void advanceDate(int days) {
        _currentDate += days;
        setChanged(true);
    }

    /**
     * Checks if library has changed.
     * @return true if library has changed
     */
    public boolean hasChanged() {
        return _changed;
    }

    /**
     * Read the text input file at the beginning of the program and populates the
     * instances of the various possible types (books, DVDs, users).
     *
     * @param filename name of the file to load
     * @throws UnrecognizedEntryException
     * @throws IOException
     * FIXME maybe other exceptions
     */
    void importFile(String filename) throws UnrecognizedEntryException, IOException
	    /* FIXME maybe other exceptions */  {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue; // Skip empty lines and comments
                }
                
                String[] parts = line.split(":");
                if (parts.length < 2) {
                    throw new UnrecognizedEntryException(line);
                }
                
                String type = parts[0].trim();
                
                try {
                    switch (type) {
                        case "USER":
                            parseUser(parts);
                            break;
                        case "BOOK":
                            parseBook(parts);
                            break;
                        case "DVD":
                            parseDVD(parts);
                            break;
                        default:
                            throw new UnrecognizedEntryException(line);
                    }
                } catch (Exception e) {
                    throw new UnrecognizedEntryException(line, e);
                }
            }
        }
    }

    /**
     * Parses a user entry from the import file.
     * @param parts split line parts
     */
    private void parseUser(String[] parts) {
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid user format");
        }
        
        int id = Integer.parseInt(parts[1]);
        String name = parts[2];
        String email = parts[3];
        
        User user = new User(id, name, email);
        _users.put(id, user);
        
        if (id >= _nextUserId) {
            _nextUserId = id + 1;
        }
    }

    /**
     * Parses a book entry from the import file.
     * @param parts split line parts
     */
    private void parseBook(String[] parts) {
        if (parts.length != 7) {
            throw new IllegalArgumentException("Invalid book format");
        }
        
        int id = Integer.parseInt(parts[1]);
        String title = parts[2];
        String authorName = parts[3];
        // String isbn = parts[4]; // Not used in this implementation
        int price = Integer.parseInt(parts[5]);
        int copies = Integer.parseInt(parts[6]);
        
        Creator author = _creators.computeIfAbsent(authorName, Creator::new);
        Book book = new Book(id, title, price, parts[4], author, copies);
        addWork(book);
        
        if (id >= _nextWorkId) {
            _nextWorkId = id + 1;
        }
    }

    /**
     * Parses a DVD entry from the import file.
     * @param parts split line parts
     */
    private void parseDVD(String[] parts) {
        if (parts.length != 7) {
            throw new IllegalArgumentException("Invalid DVD format");
        }
        
        int id = Integer.parseInt(parts[1]);
        String title = parts[2];
        String directorName = parts[3];
        // String isbn = parts[4]; // Not used in this implementation
        int price = Integer.parseInt(parts[5]);
        int copies = Integer.parseInt(parts[6]);
        
        Creator director = _creators.computeIfAbsent(directorName, Creator::new);
        DVD dvd = new DVD(id, title, price, parts[4], director, copies);
        addWork(dvd);
        
        if (id >= _nextWorkId) {
            _nextWorkId = id + 1;
        }
    }

    public void setChanged(boolean changed) {
      _changed = changed;
    }

}


