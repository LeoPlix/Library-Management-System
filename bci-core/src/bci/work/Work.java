package bci.work;

import java.io.Serializable;
import bci.creator.*;

import bci.work.workCategory.Category;


public abstract class Work implements Serializable {
    
    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;
    
    private final int _idWork;
    private final String _title;
    private final int _price;
    private final Category _category;
    private int _totalCopies;
    private int _availableCopies;
    
    public Work(int id, String title, int price, Category category, Creator creator) {
        _idWork = id;
        _title = title;
        _price = price;
        _category = category;
        _totalCopies = 1; // Default to 1 copy
        _availableCopies = 1;
        
        if (creator != null) {
            creator.addWork(this);
        }
        
        if (category != null) {
            category.addWork(this);
        }
    }
    
    public void addCopy() {
        _availableCopies++;
    }
    
    public void removeCopy() {
        if (_availableCopies > 0) {
            _availableCopies--;
        }
    }
    
    public void changeInventory(int amount) {
            _availableCopies += amount;
            _totalCopies += amount;
    }
    
    public boolean isAvailable() {
        return _availableCopies > 0;
    }

    public boolean isUnavailable() {
        return _availableCopies == 0;
    }
    
    public boolean isRequested() {
        return false;
    }
    
    public void requestCopy() {
    }

    public abstract String getWorkType();
    
    public abstract String getAdditionalInfo();

    // ========== GETTERS ==========
    
    public int getIdWork() {
        return _idWork;
    }
    
    public String getTitle() {
        return _title;
    }
    
    public int getPrice() {
        return _price;
    }
    
    public Category getCategory() {
        return _category;
    }
    
    /**
     * Returns the primary creator of this work.
     * For books with multiple authors, returns the first author.
     * For DVDs, returns the director.
     * 
     * @return the primary creator of this work, or null if no creator is set
     */
    public abstract Creator getCreator();

    public abstract int getCreatorNumber();
    
    public int getTotalCopies() {
        return _totalCopies;
    }
    
    public int getAvailableCopies() {
        return _availableCopies;
    }

    public String getCategoryName() {
        return _category != null ? _category.getName() : "Unknown";
    }

    // ========== EQUALS & TOSTRING ==========
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Work work = (Work) obj;
        return _idWork == work._idWork;
    }
    
    @Override
    public String toString() {
        return _idWork + " - " + _availableCopies + " de " + _totalCopies + " - " + getWorkType() + " - " + _title + " - " + _price + " - " + getCategoryName() + getAdditionalInfo();
    }
}
