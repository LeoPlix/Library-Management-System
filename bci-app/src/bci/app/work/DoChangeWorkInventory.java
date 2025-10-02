package bci.app.work;

import bci.LibraryManager;
import bci.Work;
import bci.app.exceptions.NoSuchWorkException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * 4.3.4. Change the number of exemplars of a work.
 */
class DoChangeWorkInventory extends Command<LibraryManager> {

    DoChangeWorkInventory(LibraryManager receiver) {
        super(Label.CHANGE_WORK_INVENTORY, receiver);
        addIntegerField("workId", Prompt.workId());
        addIntegerField("amount", Prompt.amountToUpdate());
    }

    @Override
    protected final void execute() throws CommandException {
        int workId = integerField("workId");
        int amount = integerField("amount");
        
        Work work = _receiver.getLibrary().getWork(workId);
        if (work == null) {
            throw new NoSuchWorkException(workId);
        }
        
        _receiver.getLibrary().changeWorkInventory(workId, amount);
        if (amount < 0) {
            // Failed because not enough inventory to remove
            _display.addLine(Message.notEnoughInventory(workId, Math.abs(amount)));
        }
    }

}
