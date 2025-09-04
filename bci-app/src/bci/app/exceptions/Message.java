package bci.app.exceptions;

interface Message {
    static String problemOpeningFile(Exception cause) {
        return "Problema ao abrir ficheiro: " + cause.getMessage();
    }

    static String noSuchCreator(String id) {
        return "O criador '" + id + "' não existe.";
    }

    static String noSuchWork(int id) {
        return "A obra " + id + " não existe.";
    }

    static String noSuchUser(int id) {
        return "O utente " + id + " não existe.";
    }

    static String userNotSuspended(int id) {
        return "O utente " + id + " não se encontra suspenso.";
    }

    static String borrowingRuleFailed(int idUser, int idWork, int idRule) {
        return "O utente " + idUser + " não pode requisitar a obra " + idWork + ". Violação da regra " + idRule + ".";
    }

    static String workNotBorrowedByUser(int idWork, int idUser) {
        return "A obra " + idWork + " não foi requisitada pelo utente " + idUser + ".";
    }

    static String userRegistrationFailed(String name, String email) {
        return "User registration failed: name '" + name + "', email '" + email + "'.";
    }
}
