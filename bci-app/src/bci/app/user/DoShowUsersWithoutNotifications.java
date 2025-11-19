package bci.app.user;

import bci.LibraryManager;
import pt.tecnico.uilib.menus.Command;
import java.util.List;


class DoShowUsersWithoutNotifications extends Command<LibraryManager> {

    DoShowUsersWithoutNotifications(LibraryManager receiver) {
        super(Label.SHOW_NO_NOTIFICATIONS, receiver);
    }

    @Override
    protected final void execute() {
        List<String> users = _receiver.getLibrary().showUsersWithoutNotifications();
        for (String user : users) {
            _display.addLine(user);
        }
    }

}
