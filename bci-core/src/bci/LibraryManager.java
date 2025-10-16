package bci;

import bci.exceptions.*;
import bci.work.Work;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The façade class.
 */
public class LibraryManager {

  private Library _library = new Library(/**_defaultRules*/);

  private String _filename = "";

  /**
   * Saves the current state of the library to the associated file.
   * 
   * If there are no changes to save, the method returns immediately.
   * If the filename is not set or is blank, a {@code MissingFileAssociationException} is thrown.
   * The method serializes the library object and writes it to the specified file.
   * After saving, the library's changed flag is reset.
   *
   * @throws MissingFileAssociationException if the filename is not set or is blank
   * @throws IOException if an I/O error occurs during saving
   */
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

  /**
   * Saves the current state of the library to a new file specified by the given filename.
   * Updates the internal filename reference and delegates the actual saving process to the {@code save()} method.
   *
   * @param filename the name of the file to save the library state to
   * @throws MissingFileAssociationException if there is no file associated with the library
   * @throws IOException if an I/O error occurs during the save operation
   */
  public void saveAs(String filename) throws MissingFileAssociationException, IOException {
    _filename = filename;
    save();
  }

  /**
   * Loads a Library object from the specified file.
   * 
   * This method attempts to deserialize a Library instance from the given filename.
   * If successful, it updates the internal state with the loaded library and marks it as unchanged.
   * If the file cannot be read or the class cannot be found during deserialization,
   * an UnavailableFileException is thrown.
   *
   * @param filename the path to the file from which the Library should be loaded
   * @throws UnavailableFileException if the file cannot be loaded or deserialized
   */
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
      _library.importFile(filename);
    } catch (UnrecognizedEntryException | IOException | NoSuchUserException | NoSuchWorkException |
             NoSuchCreatorException | UserRegistrationFailedException | BorrowingRuleFailedException |
             WorkNotBorrowedByUserException | UserIsActiveException e) {
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

    /**
     * Performs a search by term in the library.
     * 
     * @param term the search term
     * @return a list of works matching the search term
     */
    public List<String> searchWorks(String term) {
        return _library.searchWorks(term);
    }

    /**
     * Performs a search using the given search strategy.
     * 
     * @param searchStrategy the search strategy to use
     * @param term the search term
     * @return a list of works that match the search criteria, formatted as strings
     */
    public List<String> performSearch(Search searchStrategy, String term) {
        List<Work> allWorks = _library.getAllWorks();
        List<Work> matchedWorks = searchStrategy.search(term, allWorks);
        
        return matchedWorks.stream()
                .map(Work::toString)
                .collect(Collectors.toList());
    }
}
