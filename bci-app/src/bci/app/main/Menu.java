package bci.app.main;

import bci.LibraryManager;

public class Menu extends pt.tecnico.uilib.menus.Menu {

    public Menu(LibraryManager receiver) {
        super(Label.TITLE, // 4.1
                new DoOpenFile(receiver), //
                new DoSaveFile(receiver), //
                new DoDisplayDate(receiver), //
                new DoAdvanceDate(receiver), //
                new DoOpenMenuUsers(receiver), //
                new DoOpenMenuWorks(receiver), //
                new DoOpenMenuRequests(receiver) //
        );
    }

}
