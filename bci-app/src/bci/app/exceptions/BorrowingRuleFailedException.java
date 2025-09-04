package bci.app.exceptions;

import pt.tecnico.uilib.menus.CommandException;

public class BorrowingRuleFailedException extends CommandException {
    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;

    public BorrowingRuleFailedException(int idUser, int idWork, int idRule) {
        super(Message.borrowingRuleFailed(idUser, idWork, idRule));
    }
}
