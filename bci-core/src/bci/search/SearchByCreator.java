package bci.search;

import java.util.List;
import java.util.stream.Collectors;
import bci.Search;
import bci.work.Work;
import bci.work.workType.Book;

/**
 * Implementation of Search interface that searches works by creator name.
 * For books, searches through all authors.
 * For DVDs, searches by director name.
 * Performs a case-insensitive substring search.
 */
public class SearchByCreator implements Search {
    
    @Override
    public List<Work> search(String term, List<Work> works) {
        if (term == null || term.trim().isEmpty()) {
            return works;
        }
        
        String searchTerm = term.toLowerCase().trim();
        
        return works.stream()
                .filter(work -> matchesCreator(work, searchTerm))
                .collect(Collectors.toList());
    }
    
    private boolean matchesCreator(Work work, String searchTerm) {
        // For books, check all authors
        if (work instanceof Book) {
            Book book = (Book) work;
            return book.getAuthor().stream()
                    .anyMatch(author -> author.getName().toLowerCase().contains(searchTerm));
        }
        
        // For other works (like DVD), check the primary creator
        if (work.getCreator() != null) {
            return work.getCreator().getName().toLowerCase().contains(searchTerm);
        }
        
        return false;
    }
}