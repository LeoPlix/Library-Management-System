package bci.app.work;

import bci.LibraryManager;
import pt.tecnico.uilib.menus.Command;
import java.util.List;


class DoDisplayEvenWorks extends Command<LibraryManager> {

    DoDisplayEvenWorks(LibraryManager receiver) {
        super(Label.SHOW_EVEN_WORKS, receiver);
    }

    @Override
    protected final void execute() {
        List<String> works = _receiver.getLibrary().showEvenWorks();
        for (String work : works) {
            _display.addLine(work);
        }
    }
}
