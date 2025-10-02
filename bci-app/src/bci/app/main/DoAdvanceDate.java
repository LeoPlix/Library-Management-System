package bci.app.main;

import bci.LibraryManager;
import pt.tecnico.uilib.menus.Command;

/**
 * 4.1.3. Advance the current date.
 */
class DoAdvanceDate extends Command<LibraryManager> {

    DoAdvanceDate(LibraryManager receiver) {
        super(Label.ADVANCE_DATE, receiver);
        addIntegerField("days", Prompt.daysToAdvance());
    }

    @Override
    protected final void execute() {
        int days = integerField("days");
        _receiver.getLibrary().advanceDate(days);
    }

}
