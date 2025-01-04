package model;

public enum menuPrincipal {
    ADICIONAR ("1. Adicionar vídeo"),
    LISTAR ("2. Listar vídeos"),
    PESQUISAR ("3. Pesquisar vídeo por título"),
    SAIR ("4. Sair");

    private String descrition;

    menuPrincipal (String descrition){
        this.descrition = descrition;
    }

    public String getDescritionMenu() {
        return descrition;
    }
}
