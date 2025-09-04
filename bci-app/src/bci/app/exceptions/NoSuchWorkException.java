package bci.app.exceptions;

import pt.tecnico.uilib.menus.CommandException;

public class NoSuchWorkException extends CommandException {
    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;

    public NoSuchWorkException(int id) {
        super(Message.noSuchWork(id));
    }
}
