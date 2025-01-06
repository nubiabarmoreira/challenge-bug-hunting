package main;

import model.menuPrincipal;
import service.VideoManager;
import strategy.SearchStrategy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VideoManager videoManager = new VideoManager();
        boolean isMenuOpen = true;

        while (isMenuOpen) {
            System.out.println("\n=== Sistema de Gerenciamento de Vídeos ===\n");

            for (menuPrincipal exibaMenuPrincipal : menuPrincipal.values()){
                System.out.println(exibaMenuPrincipal.getDescritionMenu());
            }

            System.out.print("Escolha uma opção: ");

            int opcaoMenuPrincipal = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoMenuPrincipal) {
                case 1:
                    videoManager.adicionarVideo(scanner);
                    break;
                case 2:
                    videoManager.listarVideos();
                    break;
                case 3:
                    videoManager.pesquisarVideoPorTitulo(scanner);
                    break;
                case 4:
                    videoManager.editarVideo(scanner);
                    break;
                case 5:
                    videoManager.excluirVideo(scanner);
                    break;
                case 6:
                    videoManager.pesquisarVideoPorCategoria(scanner);
                    break;
                case 7:
                    videoManager.ordenarVideos();
                    break;
                case 8:
                    videoManager.relatorioEstatistica(scanner);
                    break;
                case 9:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }

        scanner.close();
    }
}