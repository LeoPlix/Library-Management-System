package bci.app.work;

public interface Prompt {

    static String workId() {
        return "Introduza o número da obra: ";
    }

    static String amountToUpdate() {
        return "Introduza a quantidade a actualizar: ";
    }

    static String searchTerm() {
        return "Introduza o termo de pesquisa: ";
    }

    static String creatorId() {
        return "Introduza o identificador do criador: ";
    }
}
