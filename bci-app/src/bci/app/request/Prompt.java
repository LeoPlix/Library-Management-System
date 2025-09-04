package bci.app.request;

interface Prompt {

    static String finePaymentChoice() {
        return "O utente deseja pagar multa (s/n)? ";
    }

    static String returnNotificationPreference() {
        return "Deseja ser avisado quando algum exemplar for devolvido (s/n)? ";
    }

}
