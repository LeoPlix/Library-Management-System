package bci.search;

import java.util.List;
import java.util.stream.Collectors;
import bci.Search;
import bci.work.Work;

/**
 * Implementation of Search interface that searches works by work type.
 * Searches by the type of work (e.g., "Livro", "DVD").
 * Performs a case-insensitive substring search.
 */
public class SearchByWorkType implements Search {
    
    @Override
    public List<Work> search(String term, List<Work> works) {
        if (term == null || term.trim().isEmpty()) {
            return works;
        }
        
        String searchTerm = term.toLowerCase().trim();
        
        return works.stream()
                .filter(work -> work.getWorkType().toLowerCase().contains(searchTerm))
                .collect(Collectors.toList());
    }
}