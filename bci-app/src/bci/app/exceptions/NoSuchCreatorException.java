package bci.app.exceptions;

import pt.tecnico.uilib.menus.CommandException;

public class NoSuchCreatorException extends CommandException {
    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;

    public NoSuchCreatorException(String id) {
        super(Message.noSuchCreator(id));
    }
}
