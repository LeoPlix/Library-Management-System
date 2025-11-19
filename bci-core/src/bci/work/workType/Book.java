package bci.work.workType;

import java.util.List;

import bci.work.*;
import bci.creator.*;
import bci.work.workCategory.Category;

import java.util.ArrayList;


public class Book extends Work {
    
    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;
    
    private final String _isbn;
    private final List<Creator> _author;
    
    public Book(int id, String title, int price, Category category, String isbn, List<Creator> authors) {
        super(id, title, price, category, authors != null && !authors.isEmpty() ? authors.get(0) : null);
        _isbn = isbn;
        _author = new ArrayList<>(authors != null ? authors : new ArrayList<>());
        
        if (authors != null) {
            for (Creator author : authors) {
                if (author != null) {
                    author.addWork(this);
                }
            }
        }
    }
    
    public void addAuthor(Creator author) {
        if (author != null && !_author.contains(author)) {
            _author.add(author);
            author.addWork(this);
        }
    }
   
    public void removeAuthor(Creator author) {
        if (_author.remove(author) && author != null) {
            author.removeWork(this);
        }
    }
    
    @Override
    public String getWorkType() {
        return "Livro";
    }
    
    @Override
    public String getAdditionalInfo() {
        StringBuilder authors = new StringBuilder();
        for (int i = 0; i < _author.size(); i++) {
            if (i > 0) authors.append("; ");
            authors.append(_author.get(i).getName());
        }
        return " - " + authors.toString() + " - " + _isbn;
    }

    // ========== GETTERS ==========
    
    public String getIsbn() {
        return _isbn;
    }
    
    public List<Creator> getAuthor() {
        return new ArrayList<>(_author); // Return a copy to maintain encapsulation
    }
    
    @Override
    public Creator getCreator() {
        return _author.isEmpty() ? null : _author.get(0);
    }

    @Override
    public int getCreatorNumber() {
        return _author.size();
    }

    // ========== TOSTRING ==========
    
    @Override
    public String toString() {
        return super.toString();
    }
}