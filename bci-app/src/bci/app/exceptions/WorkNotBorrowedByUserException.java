package bci.app.exceptions;

import pt.tecnico.uilib.menus.CommandException;

public class WorkNotBorrowedByUserException extends CommandException {
    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;

    public WorkNotBorrowedByUserException(int idWork, int idUser) {
        super(Message.workNotBorrowedByUser(idWork, idUser));
    }
}
