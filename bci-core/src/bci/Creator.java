package bci;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a creator (author/director) in the library.
 */
public class Creator implements Serializable {
    
    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;
    
    private String _name;
    private List<Work> _works;
    
    /**
     * Constructor for Creator.
     * @param name creator name
     */
    public Creator(String name) {
        _name = name;
        _works = new ArrayList<>();
    }
    
    /**
     * Gets the creator name.
     * @return creator name
     */
    public String getName() {
        return _name;
    }
    
    /**
     * Gets all works by this creator.
     * @return list of works
     */
    public List<Work> getWorks() {
        return new ArrayList<>(_works);
    }
    
    /**
     * Adds a work to this creator.
     * @param work the work to add
     */
    public void addWork(Work work) {
        if (!_works.contains(work)) {
            _works.add(work);
        }
    }
    
    /**
     * Removes a work from this creator.
     * @param work the work to remove
     */
    public void removeWork(Work work) {
        _works.remove(work);
    }
    
    /**
     * Checks if this creator has any works.
     * @return true if creator has works
     */
    public boolean hasWorks() {
        return !_works.isEmpty();
    }
    
    @Override
    public String toString() {
        return _name;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Creator creator = (Creator) obj;
        return _name.equals(creator._name);
    }
    
    @Override
    public int hashCode() {
        return _name.hashCode();
    }
}