package bci.app.request;

interface Message {

    static String showFine(int idUser, int amount) {
        return "O utente " + idUser + " deve pagar uma multa de EUR " + amount + ".";
    }

    static String workReturnDay(int idWork, int day) {
        return "A obra " + idWork + " deve ser devolvida até ao dia " + day + ".";
    }

}
