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

  private String _filename = "";

  public void save() throws MissingFileAssociationException, IOException {
    if (!hasChanged()) return;
    if (_filename == null || _filename.isBlank()) 
      throw new MissingFileAssociationException();
    try (ObjectOutputStream oos = new ObjectOutputStream(
                                  new BufferedOutputStream(
                                  new FileOutputStream(_filename)))) {
      oos.writeObject(_library);
      _library.setChanged(false);                    
    }
  }

  public void saveAs(String filename) throws MissingFileAssociationException, IOException {
    _filename = filename;
    save();
  }

  public void load(String filename) throws UnavailableFileException {
    try {
      try (ObjectInputStream ois = new ObjectInputStream(
                                   new BufferedInputStream(  
                                   new FileInputStream(filename)))) {
        _filename = filename;
        _library = (Library)ois.readObject();
        _library.setChanged(false);
      }
    }
    catch (IOException | ClassNotFoundException e) {
      throw new UnavailableFileException(filename);
    }
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
    } catch (IOException | UnrecognizedEntryException e) {
    throw new ImportFileException(filename, e);
    }
  }

  /**
  * @return true if the library has changed since the last save.
  */
  public boolean hasChanged() { 
    return _library.getChanged();
  }

/**
   * Gets the current library.
   * @return the current library
   */
  public Library getLibrary() {
    return _library;
  }

  /**
   * Sets the current library.
   * @param library the library to set
   */
  public void setLibrary(Library library) {
    _library = library;
  }

    /**
     * Advances the date in the library
     */
    public void advanceDate(int days){
        _library.advanceDate(days);
    }

    
}
