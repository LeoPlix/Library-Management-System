package bci.app.main;

interface Prompt {

    static String openFile() {
        return "Ficheiro a abrir: ";
    }

    static String saveBeforeExit() {
        return "Guardar antes de fechar? ";
    }

    static String saveAs() {
        return "Guardar ficheiro como: ";
    }

    static String newSaveAs() {
        return "Ficheiro sem nome. " + saveAs();
    }

    static String daysToAdvance() {
        return "Introduza número de dias a avançar: ";
    }

}
