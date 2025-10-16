package bci.search;

import java.util.List;
import java.util.stream.Collectors;
import bci.Search;
import bci.work.Work;

/**
 * Implementation of Search interface that searches works by title.
 * Performs a case-insensitive substring search on the work title.
 */
public class SearchByTitle implements Search {
    
    @Override
    public List<Work> search(String term, List<Work> works) {
        if (term == null || term.trim().isEmpty()) {
            return works;
        }
        
        String searchTerm = term.toLowerCase().trim();
        
        return works.stream()
                .filter(work -> work.getTitle().toLowerCase().contains(searchTerm))
                .collect(Collectors.toList());
    }
}