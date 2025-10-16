package bci.app.work;

import bci.LibraryManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.forms.Form;
import java.util.List;

/**
 * 4.3.5. Perform search according to miscellaneous criteria.
 */
class DoPerformSearch extends Command<LibraryManager> {

    DoPerformSearch(LibraryManager receiver) {
        super(Label.PERFORM_SEARCH, receiver);
    }

    @Override
    protected final void execute() {
        // Get search term from user
        String searchTerm = Form.requestString(Prompt.searchTerm());
        
        // Perform search 
        List<String> results = _receiver.searchWorks(searchTerm);
        
        // Display results
        for (String workDescription : results) {
            _display.popup(workDescription);
        }
    }

}
