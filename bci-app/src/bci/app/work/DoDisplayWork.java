package bci.app.work;

import bci.LibraryManager;
import bci.app.exceptions.NoSuchWorkException;
import bci.work.*;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

class DoDisplayWork extends Command<LibraryManager> {

    DoDisplayWork(LibraryManager receiver) {
        super(Label.SHOW_WORK, receiver);
        addIntegerField("workId", Prompt.workId());
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            int workId = integerField("workId");
            Work work = _receiver.getLibrary().workByKey(workId);
            _display.popup(work.toString());
        } catch (bci.exceptions.NoSuchWorkException e) {
            throw new NoSuchWorkException(integerField("workId"));
        }
    }
}
