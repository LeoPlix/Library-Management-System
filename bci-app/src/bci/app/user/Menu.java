package bci.app.user;

import bci.LibraryManager;

public class Menu extends pt.tecnico.uilib.menus.Menu {

    public Menu(LibraryManager receiver) {
        super(Label.TITLE, //
                new DoRegisterUser(receiver), //
                new DoShowUser(receiver), //
                new DoShowUsers(receiver), //
                new DoShowUserNotifications(receiver), //
                new DoPayFine(receiver) //
        );
    }

}
