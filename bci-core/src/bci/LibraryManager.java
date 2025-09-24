package bci;

import bci.exceptions.*;
import java.io.*;
//FIXME maybe import classes

/**
 * The façade class.
 */
public class LibraryManager {

    /** The object doing all the actual work. */
    private Library _library = new Library(/**_defaultRules*/);

    //FIXME maybe define constructors

    public void save() throws MissingFileAssociationException, IOException {
        //FIXME implement method
    }

    public void saveAs(String filename) throws MissingFileAssociationException, IOException {
        //FIXME implement method
    }

    public void load(String filename) throws UnavailableFileException {
        //FIXME implement method
    }

    /**
     * Read text input file and initializes the current library (which should be empty)
     * with the domain entities representeed in the import file.
     *
     * @param filename name of the text input file
     * @throws ImportFileException if some error happens during the processing of the
     * import file.
     */
    public void importFile(String filename) throws ImportFileException {
      try {
        if (filename != null && !filename.isEmpty())
          _library.importFile(filename);
      } catch (IOException | UnrecognizedEntryException /* FIXME maybe other exceptions */ e) {
        throw new ImportFileException(filename, e);
      }
    }

    //FIXME implement other methods

}
