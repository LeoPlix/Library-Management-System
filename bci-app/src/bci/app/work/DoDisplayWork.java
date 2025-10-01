package bci.app.work;

import bci.LibraryManager;
import bci.Work;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import bci.app.exceptions.NoSuchWorkException;

/**
 * 4.3.1. Display work.
 */
class DoDisplayWork extends Command<LibraryManager> {

    DoDisplayWork(LibraryManager receiver) {
        super(Label.SHOW_WORK, receiver);
        addIntegerField("workId", Prompt.workId());
    }

    @Override
    protected final void execute() throws CommandException {
        int workId = integerField("workId");
        
        Work work = _receiver.getLibrary().getWork(workId);
        if (work == null) {
            throw new NoSuchWorkException(workId);
        }
        
        _display.addLine(work.toString());
    }

}
