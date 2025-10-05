package bci.work.workType;

import bci.work.workCategory.Category;
import bci.work.*;
import bci.creator.*;

/**
 * Represents a DVD in the library system.
 */
public class DVD extends Work {
    
    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;
    
    // Additional attributes specific to DVD
    private final String _igac;
    private final Creator _director;
    
    /**
     * Constructor for DVD.
     * @param id the work identifier
     * @param title the DVD title
     * @param price the DVD price
     * @param category the DVD category
     * @param igac the DVD IGAC rating
     * @param director the DVD director
     */
    public DVD(int id, String title, int price, Category category, String igac, Creator director) {
        super(id, title, price, category, director);
        _igac = igac;
        _director = director;
        
        // Add this DVD to the director's collection
        if (director != null) {
            director.addWork(this);
        }
    }
    
    /**
     * Gets the DVD IGAC rating.
     * @return the IGAC rating
     */
    public String getIgac() {
        return _igac;
    }
    
    /**
     * Gets the DVD director.
     * @return the director
     */
    public Creator getDirector() {
        return _director;
    }
    
    @Override
    protected String getWorkType() {
        return "DVD";
    }
    
    @Override
    protected String getAdditionalInfo() {
        String directorName = _director != null ? _director.getName() : "Unknown";
        return " - " + directorName + " - " + _igac;
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
}