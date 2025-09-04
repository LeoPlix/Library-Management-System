package bci.app.main;

import bci.LibraryManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * §4.1.4 Management menus.
 */
class DoOpenMenuUsers extends Command<LibraryManager> {

    DoOpenMenuUsers(LibraryManager receiver) {
        super(Label.OPEN_MENU_USERS, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        (new bci.app.user.Menu(_receiver)).open();
    }

}
