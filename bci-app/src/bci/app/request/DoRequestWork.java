package bci.app.request;

import bci.LibraryManager;
import bci.app.exceptions.NoSuchUserException;
import bci.app.exceptions.NoSuchWorkException;
import bci.app.exceptions.BorrowingRuleFailedException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;

/**
 * 4.4.1. Request work.
 */
class DoRequestWork extends Command<LibraryManager> {

    DoRequestWork(LibraryManager receiver) {
        super(Label.REQUEST_WORK, receiver);
        addIntegerField("userId", bci.app.user.Prompt.userId());
        addIntegerField("workId", bci.app.work.Prompt.workId());
    }

    @Override
    protected final void execute() throws CommandException {
        int userId = integerField("userId");
        int workId = integerField("workId");
        
        try {
            // Use library's requestWork method for interactive requests
            int requestLimit = _receiver.getLibrary().requestWork(userId, workId);
            
            // Show return day message for successful requests
            _display.popup(Message.workReturnDay(workId, requestLimit));
            
        } catch (bci.exceptions.NoSuchUserException e) {
            throw new NoSuchUserException(userId);

        } catch (bci.exceptions.NoSuchWorkException e) {
            throw new NoSuchWorkException(workId);

        } catch (bci.exceptions.BorrowingRuleFailedException e) {
            if (e.getRuleId() == 3) { // Rule 3
                Form.confirm(Prompt.returnNotificationPreference());
            }
            else {
                throw new BorrowingRuleFailedException(userId, workId, e.getRuleId());
            }
        }
    }

}
