package bci.work.workCategory;

import bci.work.*;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;


public class Reference implements Category, Serializable {
    
    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;
    
    private List<Work> _works;
    private String _name;
    
    public Reference() {
        _name = "Referência";
        _works = new ArrayList<>();
    }
    
    @Override
    public List<Work> category(String name) {
        if ("REFERENCE".equals(name)) {
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

    // ========== GETTERS ==========
    
    public String getName() {
        return _name;
    }

    public List<Work> getWorks() {
        return new ArrayList<>(_works);
    }
}