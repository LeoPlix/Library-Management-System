package bci.app.request;

import bci.LibraryManager;

public class Menu extends pt.tecnico.uilib.menus.Menu {

    public Menu(LibraryManager receiver) {
        super(Label.TITLE, //
                new DoRequestWork(receiver), //
                new DoReturnWork(receiver) //
        );
    }

}
