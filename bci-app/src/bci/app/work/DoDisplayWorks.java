package bci.app.work;

import bci.LibraryManager;
import bci.Work;
import pt.tecnico.uilib.menus.Command;
import java.util.List;

/**
 * 4.3.2. Display all works.
 */
class DoDisplayWorks extends Command<LibraryManager> {

    DoDisplayWorks(LibraryManager receiver) {
        super(Label.SHOW_WORKS, receiver);
    }

    @Override
    protected final void execute() {
        List<Work> works = _receiver.getLibrary().getAllWorks();
        
        for (Work work : works) {
            _display.addLine(work.toString());
        }
    }
}
