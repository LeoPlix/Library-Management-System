package bci.app.user;

import bci.LibraryManager;
import pt.tecnico.uilib.menus.Command;
import java.util.List;


class DoShowDutifulUsers extends Command<LibraryManager> {

    DoShowDutifulUsers(LibraryManager receiver) {
        super(Label.SHOW_USERS, receiver);
    }

    @Override
    protected final void execute() {
        List<String> users = _receiver.getLibrary().showDutifulUsers();
        for (String user : users) {
            _display.addLine(user);
        }
    }

}
