package bci.app.work;

import bci.LibraryManager;
import pt.tecnico.uilib.menus.Command;
import java.util.List;


class DoDisplayWorksByCategory extends Command<LibraryManager> {

    DoDisplayWorksByCategory(LibraryManager receiver) {
        super(Label.SHOW_WORKS_BY_CATEGORY, receiver);
    }

    @Override
    protected final void execute() {
        List<String> works = _receiver.getLibrary().showScitechWorks();
        for (String work : works) {
            _display.addLine(work);
        }
    }
}
