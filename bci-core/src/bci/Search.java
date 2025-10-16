package bci;

import java.util.List;
import bci.work.Work;

/**
 * Interface for searching works in the library system.
 * Allows different search strategies to be implemented with minimal impact
 * on the existing codebase.
 */
public interface Search {
    
    /**
     * Performs a search based on the given term and list of works.
     * 
     * @param term the search term to look for
     * @param works the list of works to search through
     * @return a list of works that match the search criteria
     */
    List<Work> search(String term, List<Work> works);
}