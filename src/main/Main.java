package main;

import service.VideoManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VideoManager videoManager = new VideoManager();

        while (true) {
            System.out.println("=== Sistema de Gerenciamento de Vídeos ===\n");
            System.out.println("1. Adicionar vídeo");
            System.out.println("2. Listar vídeos");
            System.out.println("3. Pesquisar vídeo por título");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            if (opcao == 1) {
                videoManager.adicionarVideo(scanner);
            } else if (opcao == 2) {
                videoManager.listarVideos();
            } else if (opcao == 3) {
                videoManager.pesquisarVideo(scanner);
            } else if (opcao == 4) {
                System.out.println("Saindo do sistema...");
                break;
            } else {
                System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }
}