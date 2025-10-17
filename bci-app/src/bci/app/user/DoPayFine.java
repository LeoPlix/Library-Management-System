package bci.app.user;

import bci.LibraryManager;
import bci.app.exceptions.NoSuchUserException;
import bci.app.exceptions.UserIsActiveException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * 4.2.5. Settle a fine.
 */
class DoPayFine extends Command<LibraryManager> {

    DoPayFine(LibraryManager receiver) {
        super(Label.PAY_FINE, receiver);
        addIntegerField("userId", Prompt.userId());
    }

    @Override
    protected final void execute() throws CommandException {
        int userId = integerField("userId");
        
        try {
            // Get the user's current fine amount to pay it all
            bci.user.User user = _receiver.getLibrary().getUser(userId);
            
            if (user == null) {
                throw new bci.exceptions.NoSuchUserException(userId);
            }
            
            int fineAmount = user.getFines();
            
            if (fineAmount > 0) {
                _receiver.getLibrary().payFine(userId, fineAmount);
            }
            
        } catch (bci.exceptions.NoSuchUserException e) {
            throw new NoSuchUserException(userId);
            
        } catch (bci.exceptions.UserIsActiveException e) {
            throw new UserIsActiveException(userId);
        }
    }

}
