package bci.exceptions;

/** Class encoding input file parsing issues. */
public class UnrecognizedEntryException extends Exception {

    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;

    private final String _entrySpecification;

    public UnrecognizedEntryException(String entrySpecification) {
        _entrySpecification = entrySpecification;
    }

    public UnrecognizedEntryException(String entrySpecification, Exception cause) {
        super(cause);
        _entrySpecification = entrySpecification;
    }

    public String getEntrySpecification() {
        return _entrySpecification;
    }

}
