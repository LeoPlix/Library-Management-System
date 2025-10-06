package bci.app.main;

//FIXME maybe import classes

import bci.LibraryManager;
import bci.app.exceptions.FileOpenFailedException;
import bci.exceptions.UnavailableFileException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * §4.1.1 Open and load files.
 */
class DoOpenFile extends Command<LibraryManager> {

    DoOpenFile(LibraryManager receiver) {
        super(Label.OPEN_FILE, receiver);
	//FIXME maybe define fields
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            if (_receiver.hasChanged() && Form.confirm(Prompt.saveBeforeExit())) {
                DoSaveFile cmd = new DoSaveFile(_receiver);
                cmd.execute();
            }
            
            String filename = Form.requestString(Prompt.openFile());
            
            // Only load serialized library files (binary)
            // Text files (.import) can only be used at application startup with -Dimport
            _receiver.load(filename);
            
        } catch (UnavailableFileException e) {
            throw new FileOpenFailedException(e);
        }
    }

}
