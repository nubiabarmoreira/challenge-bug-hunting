package main;

import model.menuPrincipal;
import service.VideoManager;

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

            if (opcaoMenuPrincipal == 1) {
                videoManager.adicionarVideo(scanner);
            } else if (opcaoMenuPrincipal == 2) {
                videoManager.listarVideos();
            } else if (opcaoMenuPrincipal == 3) {
                videoManager.pesquisarVideo(scanner);
            } else if (opcaoMenuPrincipal == 4) {
                videoManager.editarVideo();
            } else if (opcaoMenuPrincipal == 5) {
                videoManager.excluirVideo();
            } else if (opcaoMenuPrincipal == 6) {
                videoManager.pesquisarVideo(scanner);
            } else if (opcaoMenuPrincipal == 7) {
                videoManager.ordenarVideos();
            } else if (opcaoMenuPrincipal == 8) {
                videoManager.relatorioEstatistica();
            } else if (opcaoMenuPrincipal == 9) {
                System.out.println("Saindo do sistema...");
                break;
            } else {
                System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }
}