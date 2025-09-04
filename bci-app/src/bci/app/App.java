package bci.app;

import bci.exceptions.ImportFileException;
import pt.tecnico.uilib.Dialog;

public class App {

    public static void main(String[] args) {
        try (var ui = Dialog.UI) {
            var receiver = new bci.LibraryManager();
            String datafile = System.getProperty("import");
            if (datafile != null) {
                try {
                    receiver.importFile(datafile);
                } catch (ImportFileException e) {
                    // no behavior described: just present the problem
                    e.printStackTrace();
                }
            }

            (new bci.app.main.Menu(receiver)).open();
        }
    }

}
