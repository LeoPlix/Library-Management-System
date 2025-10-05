package bci.work.workCategory;

import bci.work.*;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 * Fiction category implementation.
 */
public class Fiction implements Category, Serializable {
    
    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;
    
    private List<Work> _works;
    private String _name;
    
    /**
     * Constructor for Fiction category.
     */
    public Fiction() {
        _name = "Ficção";
        _works = new ArrayList<>();
    }
    
    @Override
    public List<Work> category(String name) {
        if ("FICTION".equals(name)) {
            return new ArrayList<>(_works);
        }
        return new ArrayList<>();
    }
    
    @Override
    public List<Work> addWork(Work work) {
        if (work != null && !_works.contains(work)) {
            _works.add(work);
        }
        return new ArrayList<>(_works);
    }
    
    @Override
    public List<Work> removeWork(Work work) {
        _works.remove(work);
        return new ArrayList<>(_works);
    }
    
    /**
     * Gets the category name.
     * @return the category name
     */
    public String getName() {
        return _name;
    }
    
    /**
     * Gets all works in this category.
     * @return list of all works
     */
    public List<Work> getWorks() {
        return new ArrayList<>(_works);
    }
}