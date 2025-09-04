package bci.app.exceptions;

import pt.tecnico.uilib.menus.CommandException;

public class NoSuchUserException extends CommandException {
    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;

    public NoSuchUserException(int id) {
        super(Message.noSuchUser(id));
    }
}
