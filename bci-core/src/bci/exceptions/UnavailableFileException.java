package bci.exceptions;

/** Class encoding file access failures. */
public class UnavailableFileException extends Exception {

    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;

    private final String _filename;

    public UnavailableFileException(String filename) {
        super("Erro a processar ficheiro " + filename);
        _filename = filename;
    }

    public String getFilename() {
        return _filename;
    }

}
