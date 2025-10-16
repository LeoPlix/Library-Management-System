package bci.work.workType;

import bci.work.workCategory.Category;
import bci.work.*;
import bci.creator.*;

public class DVD extends Work {
    
    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;
    
    private final String _igac;
    private final Creator _director;

    public DVD(int id, String title, int price, Category category, String igac, Creator director) {
        super(id, title, price, category, director);
        _igac = igac;
        _director = director;
        
        if (director != null) {
            director.addWork(this);
        }
    }
    
    public String getIgac() {
        return _igac;
    }
    
    public Creator getDirector() {
        return _director;
    }
    
    @Override
    public Creator getCreator() {
        return _director;
    }
    
    @Override
    public String getWorkType() {
        return "DVD";
    }
    
    @Override
    public String getAdditionalInfo() {
        String directorName = _director != null ? _director.getName() : "Unknown";
        return " - " + directorName + " - " + _igac;
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
}