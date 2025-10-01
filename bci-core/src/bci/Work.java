package bci;

import java.io.Serializable;

/**
 * Abstract class representing a work (book, DVD, etc.) in the library.
 */
public abstract class Work implements Serializable {
    
    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;
    
    private int _idWork;
    private String _title;
    private int _price;
    private String _category;
    private int _totalCopies;
    private int _availableCopies;
    
    /**
     * Constructor for Work.
     * @param id work identifier
     * @param title work title
     * @param price work price
     * @param category work category
     * @param totalCopies total number of copies
     */
    public Work(int id, String title, int price, String category, int totalCopies) {
        _idWork = id;
        _title = title;
        _price = price;
        _category = category;
        _totalCopies = totalCopies;
        _availableCopies = totalCopies;
    }
    
    /**
     * Gets the work ID.
     * @return work ID
     */
    public int getId() {
        return _idWork;
    }
    
    /**
     * Gets the work title.
     * @return work title
     */
    public String getTitle() {
        return _title;
    }
    
    /**
     * Gets the work price.
     * @return work price
     */
    public int getPrice() {
        return _price;
    }
    
    /**
     * Gets the work category.
     * @return work category
     */
    public String getCategory() {
        return _category;
    }
    
    /**
     * Gets the total number of copies.
     * @return total copies
     */
    public int getTotalCopies() {
        return _totalCopies;
    }
    
    /**
     * Gets the number of available copies.
     * @return available copies
     */
    public int getAvailableCopies() {
        return _availableCopies;
    }
    
    /**
     * Adds copies to this work.
     */
    public void addCopy() {
        _totalCopies++;
        _availableCopies++;
    }
    
    /**
     * Removes a copy from this work.
     */
    public void removeCopy() {
        if (_totalCopies > 0) {
            _totalCopies--;
            if (_availableCopies > _totalCopies) {
                _availableCopies = _totalCopies;
            }
        }
    }
    
    /**
     * Checks if the work is available for borrowing.
     * @return true if available
     */
    public boolean isAvailable() {
        return _availableCopies > 0;
    }
    
    /**
     * Checks if the work is requested.
     * @return true if requested
     */
    public boolean isRequested() {
        return _availableCopies < _totalCopies;
    }
    
    /**
     * Requests a copy of this work.
     * @return true if successfully requested
     */
    public boolean requestCopy() {
        if (_availableCopies > 0) {
            _availableCopies--;
            return true;
        }
        return false;
    }
    
    /**
     * Returns a copy of this work.
     */
    public void returnCopy() {
        if (_availableCopies < _totalCopies) {
            _availableCopies++;
        }
    }
    
    /**
     * Gets the creator of this work.
     * @return creator
     */
    public abstract Creator getCreator();
    
    @Override
    public String toString() {
        return _idWork + " - " + _title + " - " + _price + " - " + _category + " - " + getCreator().getName() + " - " + _totalCopies;
    }
}