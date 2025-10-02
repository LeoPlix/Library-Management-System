package bci.app.user;

import bci.LibraryManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import bci.app.exceptions.UserRegistrationFailedException;

/**
 * 4.2.1. Register new user.
 */
class DoRegisterUser extends Command<LibraryManager> {

    DoRegisterUser(LibraryManager receiver) {
        super(Label.REGISTER_USER, receiver);
        addStringField("name", Prompt.userName());
        addStringField("email", Prompt.userEMail());
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            String name = stringField("name");
            String email = stringField("email");
            
            _receiver.getLibrary().registerUser(name, email);
            int id = _receiver.getLibrary().getCurrentUserID();
            _display.addLine(Message.registrationSuccessful(id));
        } catch (Exception e) {
            // Handle any registration failure
        throw new UserRegistrationFailedException(stringField("name"), stringField("email"));
    }
}

}
