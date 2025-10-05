package bci.work.workCategory;

import bci.work.*;
import java.util.List;

/**
 * Interface representing a category in the library system.
 */
public interface Category {
    
    /**
     * Gets works in this category by name.
     * @param name the category name
     * @return list of works in this category
     */
    List<Work> category(String name);
    
    /**
     * Adds a work to this category.
     * @param work the work to add
     * @return the updated list of works in this category
     */
    List<Work> addWork(Work work);
    
    /**
     * Removes a work from this category.
     * @param work the work to remove
     * @return the updated list of works in this category
     */
    List<Work> removeWork(Work work);
    
    /**
     * Gets the category name.
     * @return the category name
     */
    String getName();
}