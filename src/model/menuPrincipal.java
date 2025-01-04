package model;

public enum menuPrincipal {
    ADICIONAR ("1. Adicionar vídeo"),
    LISTAR ("2. Listar vídeos"),
    PESQUISARTITULO ("3. Pesquisar vídeo por título"),
    EDITAR ("4. Editar vídeo"),
    EXCLUIR ("5. Excluir vídeo"),
    PESQUISARCATEGORIA ("6. Pesquisar vídeos por categoria"),
    ORDENAR ("7. Ordenar vídeos por data de publicação"),
    ESTATISTICA ("8. Exibir relatório de estatísticas"),
    SAIR ("9. Sair");

    private String descrition;

    menuPrincipal (String descrition){
        this.descrition = descrition;
    }

    public String getDescritionMenu() {
        return descrition;
    }
}
