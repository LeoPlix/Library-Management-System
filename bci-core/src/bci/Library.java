package bci;

import bci.exceptions.*;
import java.io.IOException;
import java.io.Serializable;
//FIXME maybe import classes

/** Class that represents the library as a whole. */
class Library implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;

    //FIXME maybe define attributes
    //FIXME maybe implement constructor
    //FIXME maybe implement methods

    /**
     * Read the text input file at the beginning of the program and populates the
     * instances of the various possible types (books, DVDs, users).
     *
     * @param filename name of the file to load
     * @throws UnrecognizedEntryException
     * @throws IOException
     * FIXME maybe other exceptions
     */
    void importFile(String filename) throws UnrecognizedEntryException, IOException
	    /* FIXME maybe other exceptions */  {
      //FIXME implement method
    }

}
