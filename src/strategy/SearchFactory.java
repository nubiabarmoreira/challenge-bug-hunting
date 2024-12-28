package strategy;

public class SearchFactory {
    public static SearchStrategy getSearch (int option) {
        if (option == 1){
            return new TitleSearchStrategy();
        } else if (option == 2) {
            return new CategorySearchStrategy();
        } else{
            throw new IllegalArgumentException("Opção não esperada: " + option);
        }
    }
}
