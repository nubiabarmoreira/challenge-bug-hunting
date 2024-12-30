package model;

public enum category {
    FILME ("1 : Filme"),
    SERIE ("2 : Série"),
    DOCUMENTARIO ("3 : Documentário"),
    CURTA ("4 : Curta"),
    OUTRO ("5 : Outro");

    private String descrition;

    category(String descrition) {
        this.descrition = descrition;
    }

    public String getDescrition() {
        return descrition;
    }
}
