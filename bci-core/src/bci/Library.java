package bci;

import bci.exceptions.*;
import bci.user.*;
import bci.work.*;
import bci.work.workCategory.*;
import bci.work.workType.*;
import bci.creator.*;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

// Class that represents the library as a whole
public class Library implements Serializable {
    private boolean _changed = false;
    private int _currentDate = 1;
    private Map<Integer, User> _users = new HashMap<>();
    private Map<Integer, Work> _works = new HashMap<>();
    private Map<String, Creator> _creators = new HashMap<>();

    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;

    void importFile(String filename) throws UnrecognizedEntryException, IOException, 
                                           NoSuchUserException, NoSuchWorkException, NoSuchCreatorException,
                                           UserRegistrationFailedException, BorrowingRuleFailedException,
                                           WorkNotBorrowedByUserException, UserIsActiveException {
        var reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            var fields = line.split(":");
            process(fields);
        }
        _changed = true;
        reader.close();
    }

    /**
     * Processes a line from the import file.
     * @param fields the fields from the line split by ":"
     * @throws UnrecognizedEntryException if the entry type is not recognized
     * @throws NoSuchUserException if a referenced user doesn't exist
     * @throws NoSuchWorkException if a referenced work doesn't exist
     * @throws NoSuchCreatorException if a referenced creator doesn't exist
     * @throws UserRegistrationFailedException if user registration fails
     * @throws BorrowingRuleFailedException if borrowing rules are violated
     * @throws WorkNotBorrowedByUserException if work is not borrowed by user
     * @throws UserIsActiveException if user is already active
     */
    private void process(String[] fields) throws UnrecognizedEntryException, NoSuchUserException, 
                                               NoSuchWorkException, NoSuchCreatorException,
                                               UserRegistrationFailedException, BorrowingRuleFailedException,
                                               WorkNotBorrowedByUserException, UserIsActiveException {
        switch (fields[0]) {
            case "USER":
                processUser(fields);
                break;
            case "BOOK":
            case "DVD":
                processWork(fields);
                break;
            case "REQUEST":
                processRequest(fields);
                break;
            default:
                throw new UnrecognizedEntryException(fields[0]);
        }
    }



    /**
     * Processes a user entry from the import file.
     * Format: USER:name:email
     * ID is auto-generated incrementally
     */
    public User processUser(String... fields) throws UserRegistrationFailedException {
        // Always auto-generate ID incrementally
        int id = getCurrentUserID();
        String name = fields[1];
        String email = fields[2];
        
        if (_users.containsKey(id)) throw new UserRegistrationFailedException(name, email);
        
        var user = new User(id, name, email);
        _users.put(id, user);
        _changed = true;
        return user;
    }


    public Work processWork(String... fields) throws NoSuchCreatorException, UnrecognizedEntryException {
        String workType = fields[0];
        String title = fields[1];
        String creatorName = fields[2];
        int price = Integer.parseInt(fields[3]);
        String categoryName = fields[4];
        String additionalInfo = fields[5]; // ISBN for BOOK, IGAC for DVD
        int quantity = Integer.parseInt(fields[6]);
        
        int id = getCurrentWorkID();
        
        Creator creator;
        try {
            creator = creatorByKey(creatorName);
        } catch (NoSuchCreatorException e) {
            creator = new Creator(creatorName);
            _creators.put(creatorName, creator);
        }
        
        Category category = getCategoryByName(categoryName);
        
        Work work = createWork(workType, id, title, price, category, additionalInfo, creator, quantity);
        _works.put(id, work);
        _changed = true;
        return work;
    }

    
    private Work createWork(String workType, int id, String title, int price, Category category, 
                           String additionalInfo, Creator creator, int quantity) throws UnrecognizedEntryException {
        Work work;
        switch (workType.toUpperCase()) {
            case "BOOK":
                work = new Book(id, title, price, category, additionalInfo, Arrays.asList(creator));
                break;
            case "DVD":
                work = new DVD(id, title, price, category, additionalInfo, creator);
                break;
            default:
                throw new UnrecognizedEntryException(workType);
        }
        
        // Set initial quantity
        if (quantity > 1) {
            work.changeInventory(quantity - 1); // -1 because work starts with 1 copy
        }
        
        return work;
    }
    

    /**
     * Processes a request entry from the import file.
     * Format: REQUEST|userId|workId
     */
    public void processRequest(String... fields) throws NoSuchUserException, NoSuchWorkException, 
                                                       BorrowingRuleFailedException, UnrecognizedEntryException {
        int userId = Integer.parseInt(fields[1]);
        int workId = Integer.parseInt(fields[2]);
        
        userByKey(userId);
        workByKey(workId);
        
        _changed = true;
    }
    /**
     * Gets a category by name, creating it if it doesn't exist.
     * @param categoryName the category name
     * @return the category instance
     */
    private Category getCategoryByName(String categoryName) {
        switch (categoryName.toUpperCase()) {
            case "FICTION":
                return new Fiction();
            case "TECHNICAL":
            case "SCITECH":
                return new Technical();
            case "REFERENCE":
                return new Reference();
            default:
                return null;
        }
    }


    /**
     * Gets a user by ID.
     * @param id the user ID
     * @return the user
     * @throws NoSuchUserException if user doesn't exist
     */
    public User userByKey(int id) throws NoSuchUserException {
        var user = _users.get(id);
        if (user == null) throw new NoSuchUserException(id);
        return user;
    }

    /**
     * Gets a work by ID.
     * @param id the work ID
     * @return the work
     * @throws NoSuchWorkException if work doesn't exist
     */
    public Work workByKey(int id) throws NoSuchWorkException {
        var work = _works.get(id);
        if (work == null) throw new NoSuchWorkException(id);
        return work;
    }

    /**
     * Gets a creator by name.
     * @param name the creator name
     * @return the creator
     * @throws NoSuchCreatorException if creator doesn't exist
     */
    public Creator creatorByKey(String name) throws NoSuchCreatorException {
        var creator = _creators.get(name);
        if (creator == null) throw new NoSuchCreatorException(name);
        return creator;
    }

    /**
     * Registers a new user in the library.
     * @param user the user to register
     */
    public void registerUser(User user) {
        _users.put(user.getIdUser(), user);
        setChanged(true);
    }

    /**
     * Registers a new work in the library.
     * @param work the work to register
     */
    public void registerWork(Work work) {
        _works.put(work.getIdWork(), work);
        setChanged(true);
    }

    /**
     * Registers a new creator in the library.
     * @param creator the creator to register
     */
    public void registerCreator(Creator creator) {
        _creators.put(creator.getName(), creator);
        setChanged(true);
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
        return _works.size() + 1;
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

