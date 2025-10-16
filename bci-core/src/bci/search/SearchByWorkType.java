package bci.search;

import java.util.List;
import java.util.ArrayList;
import bci.Search;
import bci.work.Work;

public class SearchByWorkType implements Search {
    
    @Override
    public List<Work> search(String term, List<Work> works) {
        List<Work> results = new ArrayList<>();
        
        for (Work work : works) {
            if (work.getWorkType().toLowerCase().contains(term.toLowerCase())) {
                results.add(work);
            }
        }
        
        return results;
    }
}