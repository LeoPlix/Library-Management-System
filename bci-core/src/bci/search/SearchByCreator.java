package bci.search;

import java.util.List;
import java.util.ArrayList;
import bci.Search;
import bci.work.Work;
import bci.work.workType.Book;
import bci.creator.Creator;

public class SearchByCreator implements Search {
    
    @Override
    public List<Work> search(String term, List<Work> works) {
        List<Work> results = new ArrayList<>();
        
        for (Work work : works) {
            if (work instanceof Book) {
                Book book = (Book) work;
                for (Creator author : book.getAuthor()) {
                    if (author.getName().toLowerCase().contains(term.toLowerCase())) {
                        results.add(work);
                        break;
                    }
                }
            } else {
                if (work.getCreator().getName().toLowerCase().contains(term.toLowerCase())) {
                    results.add(work);
                }
            }
        }
        
        return results;
    }
}