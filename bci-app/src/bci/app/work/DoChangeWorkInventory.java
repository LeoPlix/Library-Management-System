package bci.app.work;

import bci.LibraryManager;
import bci.work.*;
import bci.app.exceptions.NoSuchWorkException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


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
        if (work.getTotalCopies() - amount < 0) {
            _display.addLine(Message.notEnoughInventory(workId, Math.abs(amount)));
        }
    }

}
