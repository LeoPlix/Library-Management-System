package bci.app.user;

public interface Prompt {

    static String userId() {
        return "Introduza o número de utente: ";
    }

    static String userName() {
        return "Introduza o nome do utente: ";
    }

    static String userEMail() {
        return "Introduza o endereço de correio do utente: ";
    }

}
