package bci.app.main;

//FIXME maybe import classes

import bci.LibraryManager;
import bci.app.exceptions.FileOpenFailedException;
import bci.exceptions.UnavailableFileException;
import bci.exceptions.ImportFileException;
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
            
            // Check if it's an import file (text) or serialized file (binary)
            if (filename.endsWith(".import")) {
                // Clear current library and import text file
                _receiver.setLibrary(new bci.Library());
                _receiver.importFile(filename);
            } else {
                // Load serialized library file
                _receiver.load(filename);
            }
        } catch (UnavailableFileException e) {
            throw new FileOpenFailedException(e);
        } catch (ImportFileException e) {
            throw new FileOpenFailedException(e);
        }
    }

}
