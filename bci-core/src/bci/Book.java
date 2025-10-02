package bci;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a book in the library system.
 */
public class Book extends Work {
    
    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;
    
    // Additional attributes specific to Book
    private final String _isbn;
    private final List<Creator> _author;
    
    /**
     * Constructor for Book.
     * @param id the work identifier
     * @param title the book title
     * @param price the book price
     * @param category the book category
     * @param isbn the book ISBN
     * @param authors the list of authors
     */
    public Book(int id, String title, int price, Category category, String isbn, List<Creator> authors) {
        super(id, title, price, category, authors != null && !authors.isEmpty() ? authors.get(0) : null);
        _isbn = isbn;
        _author = new ArrayList<>(authors != null ? authors : new ArrayList<>());
        
        // Add this book to all authors' collections
        if (authors != null) {
            for (Creator author : authors) {
                if (author != null) {
                    author.addWork(this);
                }
            }
        }
    }
    
    /**
     * Gets the book ISBN.
     * @return the ISBN
     */
    public String getIsbn() {
        return _isbn;
    }
    
    /**
     * Gets the list of authors.
     * @return the list of authors
     */
    public List<Creator> getAuthor() {
        return new ArrayList<>(_author); // Return a copy to maintain encapsulation
    }
    
    /**
     * Adds an author to the book.
     * @param author the author to add
     */
    public void addAuthor(Creator author) {
        if (author != null && !_author.contains(author)) {
            _author.add(author);
            author.addWork(this);
        }
    }
    
    /**
     * Removes an author from the book.
     * @param author the author to remove
     */
    public void removeAuthor(Creator author) {
        if (_author.remove(author) && author != null) {
            author.removeWork(this);
        }
    }
    
    @Override
    public String toString() {
        StringBuilder authors = new StringBuilder();
        for (int i = 0; i < _author.size(); i++) {
            if (i > 0) authors.append(", ");
            authors.append(_author.get(i).getName());
        }
        return super.toString() + " - ISBN: " + _isbn + " - Authors: " + authors.toString();
    }
}