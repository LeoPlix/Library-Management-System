package bci;

/**
 * Represents a DVD in the library.
 */
public class DVD extends Work {
    
    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;
    
    private String _igac;
    private Creator _director;
    
    /**
     * Constructor for DVD.
     * @param id DVD identifier
     * @param title DVD title
     * @param price DVD price
     * @param igac DVD IGAC
     * @param director DVD director
     * @param totalCopies total number of copies
     */
    public DVD(int id, String title, int price, String igac, Creator director, int totalCopies) {
        super(id, title, price, "DVD", totalCopies);
        _igac = igac;
        _director = director;
    }
    
    /**
     * Gets the DVD IGAC.
     * @return IGAC
     */
    public String getIgac() {
        return _igac;
    }
    
    /**
     * Gets the DVD director.
     * @return director
     */
    public Creator getDirector() {
        return _director;
    }
    
    @Override
    public Creator getCreator() {
        return _director;
    }
}