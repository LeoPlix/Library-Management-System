package bci.app.user;

interface Message {

    static String registrationSuccessful(int idUser) {
        return "Novo utente criado com o número " + idUser + ".";
    }

}
