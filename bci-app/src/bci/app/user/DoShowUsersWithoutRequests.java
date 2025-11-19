package bci.app.user;

import bci.LibraryManager;
import pt.tecnico.uilib.menus.Command;
import java.util.List;


class DoShowUsersWithoutRequests extends Command<LibraryManager> {

    DoShowUsersWithoutRequests(LibraryManager receiver) {
        super(Label.SHOW_USER_WITHOUT_REQUESTS, receiver);
    }

    @Override
    protected final void execute() {
        List<String> users = _receiver.getLibrary().showUsersWithoutRequests();
        for (String user : users) {
            _display.addLine(user);
        }
    }

}
