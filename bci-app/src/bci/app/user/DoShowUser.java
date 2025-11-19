package bci.app.user;

import bci.LibraryManager;
import bci.user.*;
import bci.app.exceptions.NoSuchUserException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


class DoShowUser extends Command<LibraryManager> {

    DoShowUser(LibraryManager receiver) {
        super(Label.SHOW_USER, receiver);
        addIntegerField("userId", Prompt.userId());
    }

    @Override
    protected final void execute() throws CommandException {
        int userId = integerField("userId");
        String user = _receiver.getLibrary().getUser(userId);

        if (user == null) {
            throw new NoSuchUserException(userId);
        }

        _display.popup(user);
    }
}
