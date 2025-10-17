package bci.app.request;

import bci.LibraryManager;
import bci.app.exceptions.NoSuchUserException;
import bci.app.exceptions.NoSuchWorkException;
import bci.app.exceptions.WorkNotBorrowedByUserException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;

/**
 * 4.4.2. Return a work.
 */
class DoReturnWork extends Command<LibraryManager> {

    DoReturnWork(LibraryManager receiver) {
        super(Label.RETURN_WORK, receiver);
        addIntegerField("userId", bci.app.user.Prompt.userId());
        addIntegerField("workId", bci.app.work.Prompt.workId());
    }

    @Override
    protected final void execute() throws CommandException {
        int userId = integerField("userId");
        int workId = integerField("workId");
        
        try {
            // Process work return
            int fine = _receiver.getLibrary().returnWork(userId, workId);
            
            // If there's a fine, show fine message and ask for payment
            if (fine > 0) {
                _display.popup(Message.showFine(userId, fine));
                
                // Ask user if they want to pay the fine
                boolean wantsToPay = Form.confirm(Prompt.finePaymentChoice());
                
                if (wantsToPay) {
                    try {
                        _receiver.getLibrary().payFine(userId, fine);
                    } catch (bci.exceptions.UserIsActiveException e) {
                    }
                }
            }
            
        } catch (bci.exceptions.NoSuchUserException e) {
            throw new NoSuchUserException(userId);
            
        } catch (bci.exceptions.NoSuchWorkException e) {
            throw new NoSuchWorkException(workId);
            
        } catch (bci.exceptions.WorkNotBorrowedByUserException e) {
            throw new WorkNotBorrowedByUserException(workId, userId);
        }
    }

}
