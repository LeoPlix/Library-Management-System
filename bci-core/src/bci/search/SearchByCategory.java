package bci.search;

import java.util.List;
import java.util.stream.Collectors;
import bci.Search;
import bci.work.Work;

/**
 * Implementation of Search interface that searches works by category name.
 * Performs a case-insensitive substring search on category names.
 */
public class SearchByCategory implements Search {
    
    @Override
    public List<Work> search(String term, List<Work> works) {
        if (term == null || term.trim().isEmpty()) {
            return works;
        }
        
        String searchTerm = term.toLowerCase().trim();
        
        return works.stream()
                .filter(work -> work.getCategory() != null && 
                               work.getCategory().getName().toLowerCase().contains(searchTerm))
                .collect(Collectors.toList());
    }
}