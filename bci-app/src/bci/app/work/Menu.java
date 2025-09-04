package bci.app.work;

import bci.LibraryManager;

public class Menu extends pt.tecnico.uilib.menus.Menu {

    public Menu(LibraryManager receiver) {
        super(Label.TITLE, //
                new DoDisplayWork(receiver), //
                new DoDisplayWorks(receiver), //
                new DoPerformSearch(receiver), //
                new DoDisplayWorksByCreator(receiver), //
                new DoChangeWorkInventory(receiver) //
        );
    }

}
