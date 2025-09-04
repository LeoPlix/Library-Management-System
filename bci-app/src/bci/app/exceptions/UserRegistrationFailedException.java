package bci.app.exceptions;

import pt.tecnico.uilib.menus.CommandException;

public class UserRegistrationFailedException extends CommandException {
    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;

    public UserRegistrationFailedException(String name, String email) {
        super(Message.userRegistrationFailed(name, email));
    }
}
