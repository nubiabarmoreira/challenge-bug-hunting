package model;

public enum ListaDeCategoria {
    FILME ("1 : Filme"),
    SERIE ("2 : Série"),
    DOCUMENTARIO ("3 : Documentário"),
    CURTA ("4 : Curta"),
    OUTRO ("5 : Outro");

    private String descrition;

    ListaDeCategoria(String descrition) {
        this.descrition = descrition;
    }

    public String getDescritionCategoria() {
        return descrition;
    }
}
