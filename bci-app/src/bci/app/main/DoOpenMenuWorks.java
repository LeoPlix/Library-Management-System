package bci.app.main;

import bci.LibraryManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * §4.1.4 Management menus.
 */
class DoOpenMenuWorks extends Command<LibraryManager> {

    DoOpenMenuWorks(LibraryManager receiver) {
        super(Label.OPEN_MENU_WORKS, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        (new bci.app.work.Menu(_receiver)).open();
    }

}
