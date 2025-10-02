package bci.app.work;

import bci.LibraryManager;
import bci.Work;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import bci.app.exceptions.NoSuchCreatorException;
import java.util.List;

/**
 * 4.3.3. Display all works by a specific creator.
 */
class DoDisplayWorksByCreator extends Command<LibraryManager> {

    DoDisplayWorksByCreator(LibraryManager receiver) {
        super(Label.SHOW_WORKS_BY_CREATOR, receiver);
        addStringField("creatorId", Prompt.creatorId());
    }

    @Override
    protected final void execute() throws CommandException {
        String creatorId = stringField("creatorId");
        
        if (!_receiver.getLibrary().hasCreator(creatorId)) {
            throw new NoSuchCreatorException(creatorId);
        }
        
        List<Work> works = _receiver.getLibrary().getWorksByCreator(creatorId);
        
        for (Work work : works) {
            _display.addLine(work.toString());
        }
    }

}
