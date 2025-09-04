package bci.exceptions;

public class ImportFileException extends Exception {

    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;

    private static final String ERROR_MESSAGE = "Erro a processar ficheiro de import: ";

    public ImportFileException(String filename) {
        super(ERROR_MESSAGE + filename);
    }

    public ImportFileException(String filename, Exception cause) {
        super(ERROR_MESSAGE + filename, cause);
    }

}
