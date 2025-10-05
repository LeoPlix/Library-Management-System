package bci.work;

import java.io.Serializable;
import bci.creator.*;

import bci.work.workCategory.Category;

/**
 * Abstract class representing a work in the library system.
 */
public abstract class Work implements Serializable {
    
    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;
    
    // Attributes from UML
    private final int _idWork;
    private final String _title;
    private final int _price;
    private final Category _category;
    private int _totalCopies;
    private int _availableCopies;
    
    /**
     * Constructor for Work.
     * @param id the work identifier
     * @param title the work title
     * @param price the work price
     * @param category the work category
     * @param creator the work creator
     */
    public Work(int id, String title, int price, Category category, Creator creator) {
        _idWork = id;
        _title = title;
        _price = price;
        _category = category;
        _totalCopies = 1; // Default to 1 copy
        _availableCopies = 1;
        
        // Add this work to the creator's collection
        if (creator != null) {
            creator.addWork(this);
        }
        
        // Add this work to the category
        if (category != null) {
            category.addWork(this);
        }
    }
    
    /**
     * Gets the work ID.
     * @return the work ID
     */
    public int getIdWork() {
        return _idWork;
    }
    
    /**
     * Gets the work title.
     * @return the work title
     */
    public String getTitle() {
        return _title;
    }
    
    /**
     * Gets the work price.
     * @return the work price
     */
    public int getPrice() {
        return _price;
    }
    
    /**
     * Gets the work category.
     * @return the work category
     */
    public Category getCategory() {
        return _category;
    }
    
    /**
     * Gets the total number of copies.
     * @return the total copies
     */
    public int getTotalCopies() {
        return _totalCopies;
    }
    
    /**
     * Gets the number of available copies.
     * @return the available copies
     */
    public int getAvailableCopies() {
        return _availableCopies;
    }
    
    /**
     * Adds a copy to the work.
     */
    public void addCopy() {
        _availableCopies++;
    }
    
    /**
     * Removes a copy from the work.
     */
    public void removeCopy() {
        if (_availableCopies > 0) {
            _availableCopies--;
        }
    }
    
    /**
     * Changes the inventory by the specified amount.
     * @param amount the amount to change (positive to add, negative to remove)
     * @return true if successful, false if not enough inventory to remove
     */
    public void changeInventory(int amount) {
        if (amount > 0) {
            // Adding copies - update both available and total
            _availableCopies += amount;
            _totalCopies += amount;
        } else {
            // Removing copies - only update available (total stays the same)
            _availableCopies += amount;
        }
    }
    
    /**
     * Checks if the work is available for borrowing.
     * @return true if available, false otherwise
     */
    public boolean isAvailable() {
        return _availableCopies > 0;
    }
    
    /**
     * Checks if the work is currently requested.
     * @return true if requested, false otherwise
     */
    public boolean isRequested() {
        // This would need to be implemented based on the request system
        return false; // Placeholder implementation
    }
    
    /**
     * Requests a copy of the work.
     */
    public void requestCopy() {
        // This would need to be implemented based on the request system
        // Placeholder implementation
    }
    
    @Override
    public String toString() {
        return _idWork + " - " + _availableCopies + " de " + _totalCopies + " - " + getWorkType() + " - " + _title + " - " + _price + " - " + getCategoryName() + getAdditionalInfo();
    }
    
    /**
     * Gets the category name.
     * @return the category name
     */
    protected String getCategoryName() {
        return _category != null ? _category.getName() : "Unknown";
    }
    
    /**
     * Gets the work type (e.g., "Livro", "DVD").
     * @return the work type
     */
    protected abstract String getWorkType();
    
    /**
     * Gets additional information specific to the work type.
     * @return additional information string
     */
    protected abstract String getAdditionalInfo();
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Work work = (Work) obj;
        return _idWork == work._idWork;
    }
    
}