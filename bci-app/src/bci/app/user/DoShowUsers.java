package bci.app.user;

import bci.LibraryManager;
import bci.User;
import pt.tecnico.uilib.menus.Command;
import java.util.List;

/**
 * 4.2.4. Show all users.
 */
class DoShowUsers extends Command<LibraryManager> {

    DoShowUsers(LibraryManager receiver) {
        super(Label.SHOW_USERS, receiver);
    }

    @Override
    protected final void execute() {
        List<User> users = _receiver.getLibrary().getAllUsers();
        
        for (User user : users) {
            _display.addLine(user.toString());
        }
    }

}
