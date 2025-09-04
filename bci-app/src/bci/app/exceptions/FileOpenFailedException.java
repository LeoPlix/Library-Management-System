package bci.app.exceptions;

import pt.tecnico.uilib.menus.CommandException;

public class FileOpenFailedException extends CommandException {
    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;

    public FileOpenFailedException(Exception e) {
        super(Message.problemOpeningFile(e), e);
    }
}
