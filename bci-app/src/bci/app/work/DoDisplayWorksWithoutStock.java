package bci.app.work;

import bci.LibraryManager;
import pt.tecnico.uilib.menus.Command;
import java.util.List;


class DoDisplayWorksWithoutStock extends Command<LibraryManager> {

    DoDisplayWorksWithoutStock(LibraryManager receiver) {
        super(Label.SHOW_WORKS_WITHOUT_STOCK, receiver);
    }

    @Override
    protected final void execute() {
        List<String> works = _receiver.getLibrary().showWorksWithoutStock();
        for (String work : works) {
            _display.addLine(work);
        }
    }
}
