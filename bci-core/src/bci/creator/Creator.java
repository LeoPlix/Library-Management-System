package bci.creator;

import bci.work.*;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;


public class Creator implements Serializable {
    
    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;
    
    private String _name;
    private List<Work> _works;
    
    public Creator(String name) {
        _name = name;
        _works = new ArrayList<>();
    }
    
    public void addWork(Work work) { 
        if (work != null && !_works.contains(work)) {
            _works.add(work);
        }
    }
    
    public void removeWork(Work work) {
        _works.remove(work);
    }

    public boolean hasWork() {
        return !_works.isEmpty();
    }

    // ========== GETTERS ==========
    
    public String getName() {
        return _name;
    }
    
    public List<Work> getWorks() { 
        return _works;
    }

    // ========== EQUALS & TOSTRING ==========
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Creator creator = (Creator) obj;
        return _name != null ? _name.equals(creator._name) : creator._name == null;
    }

    @Override
    public String toString() {
        return _name;
    }
}