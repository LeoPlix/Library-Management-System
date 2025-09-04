package bci.app.work;

interface Message {

    static String noSuchWork(int idWork) {
        return "A obra " + idWork + " não existe.";
    }

    static String invalidWorkId(int idWork) {
        return "Número de obra inválido: " + idWork + ".";
    }

    static String workRequestedForDays(int idWork, int days) {
        return "A obra " + idWork + " foi requisitada por " + days + " dias.";
    }

    static String notEnoughInventory(int idWork, int amount) {
        return "A quantidade de exemplares da obra " + idWork + " não pode ser decrementada em " + amount + " (excederia o inventário).";
    }

    static String semExemplares(int idWork) {
        return "A obra " + idWork + " não pode ser requisitada: não há exemplares disponíveis.";
    }

}
