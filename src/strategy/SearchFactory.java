package strategy;

public class SearchFactory {
    public static SearchStrategy getSearch (int opcaoDePesquisa) {
        if (opcaoDePesquisa == 1) {
            return new TitleSearchStrategy();
        } else if (opcaoDePesquisa == 2) {
            return new CategorySearchStrategy();
        } else {
            throw new IllegalArgumentException("Opção não esperada: " + opcaoDePesquisa);
        }
    }
}
