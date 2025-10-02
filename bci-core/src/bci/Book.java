package bci;

import java.util.List;

/**
 * Represents a book in the library.
 */
public class Book extends Work {
    
    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;
    
    private String _isbn;
    private List<Creator> _author;
    
    /**
     * Constructor for Book.
     * @param id book identifier
     * @param title book title
     * @param price book price
     * @param isbn book ISBN
     * @param author book author
     * @param totalCopies total number of copies
     */
    public Book(int id, String title, int price, String isbn, Creator author, int totalCopies) {
        super(id, title, price, "BOOK", totalCopies);
        _isbn = isbn;
        _author = List.of(author);
    }
    
    /**
     * Gets the book ISBN.
     * @return ISBN
     */
    public String getIsbn() {
        return _isbn;
    }
    
    /**
     * Gets the book authors.
     * @return list of authors
     */
    public List<Creator> getAuthor() {
        return _author;
    }
    
    @Override
    public Creator getCreator() {
        return _author.get(0); // Return first author
    }
}