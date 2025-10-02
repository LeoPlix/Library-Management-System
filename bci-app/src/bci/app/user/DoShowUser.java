package bci.app.user;

import bci.LibraryManager;
import bci.User;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import bci.app.exceptions.NoSuchUserException;

/**
 * 4.2.2. Show specific user.
 */
class DoShowUser extends Command<LibraryManager> {

    DoShowUser(LibraryManager receiver) {
        super(Label.SHOW_USER, receiver);
        addIntegerField("userId", Prompt.userId());
    }

    @Override
    protected final void execute() throws CommandException {
        int userId = integerField("userId");
        
        User user = _receiver.getLibrary().getUser(userId);
        if (user == null) {
            throw new NoSuchUserException(userId);
        }
        
        _display.addLine(user.toString());
    }

}
