package bci.app.main;

import bci.LibraryManager;
import bci.app.exceptions.FileOpenFailedException;
import bci.exceptions.UnavailableFileException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


class DoOpenFile extends Command<LibraryManager> {

    DoOpenFile(LibraryManager receiver) {
        super(Label.OPEN_FILE, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            if (_receiver.hasChanged() && Form.confirm(Prompt.saveBeforeExit())) {
                DoSaveFile cmd = new DoSaveFile(_receiver);
                cmd.execute();
            }
            
            String filename = Form.requestString(Prompt.openFile());
            
            _receiver.load(filename);
            
        } catch (UnavailableFileException e) {
            throw new FileOpenFailedException(e);
        }
    }

}