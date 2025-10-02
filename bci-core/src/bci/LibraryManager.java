package bci;

import bci.exceptions.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The façade class.
 */
public class LibraryManager {

  /** The object doing all the actual work. */
  private Library _library = new Library(/*_defaultRules*/);

  /** The current filename associated with the library. */
  private String _currentFilename = null;

  /**
   * Default constructor.
   */
  public LibraryManager() {
    // Constructor vazio - inicialização básica
  }

  /**
   * Constructor with initial library.
   * @param library the library to manage
   */
  public LibraryManager(Library library) {
    this._library = library;
  }

  public void save() throws MissingFileAssociationException, IOException {
    if (_currentFilename == null) {
      throw new MissingFileAssociationException();
    }
    saveAs(_currentFilename);
  }

  public void saveAs(String filename) throws MissingFileAssociationException, IOException {
    if (filename == null || filename.trim().isEmpty()) {
      throw new MissingFileAssociationException();
    }

    try (ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream(filename)))) {
      out.writeObject(_library);
      _currentFilename = filename;
    } catch (IOException e) {
      throw new IOException("Error saving library to file: " + filename, e);
    }
  }

  public void load(String filename) throws UnavailableFileException {
    if (filename == null || filename.trim().isEmpty()) {
      throw new UnavailableFileException("Invalid filename");
    }

    Path filePath = Paths.get(filename);
    if (!Files.exists(filePath) || !Files.isReadable(filePath)) {
      throw new UnavailableFileException("File not found or not readable: " + filename);
    }

    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream(filename)))) {
      _library = (Library) in.readObject();
      _currentFilename = filename;
    } catch (IOException | ClassNotFoundException e) {
      throw new UnavailableFileException("Error loading file: " + filename);
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
    this._library = library;
  }

  /**
   * Gets the current filename.
   * @return the current filename, or null if no file is associated
   */
  public String getCurrentFilename() {
    return _currentFilename;
  }
}

