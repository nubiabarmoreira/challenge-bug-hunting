import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Video> videos = new ArrayList<>();
        File file = new File("videos.txt");

        // Carregar vídeos do arquivo
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                Video video = Video.fromString(line);
                if (video != null) {
                    videos.add(video);
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Erro ao carregar vídeos: " + e.getMessage());
        }

        while (true) {
            System.out.println("\n=== Sistema de Gerenciamento de Vídeos ===");
            System.out.println("1. Adicionar vídeo");
            System.out.println("2. Listar vídeos");
            System.out.println("3. Pesquisar vídeo por título");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            if (opcao == 1) {
                System.out.print("Digite o título do vídeo: ");
                String titulo = scanner.nextLine();
                System.out.print("Digite a descrição do vídeo: ");
                String descricao = scanner.nextLine();
                System.out.print("Digite a duração do vídeo (em minutos): ");
                int duracao = scanner.nextInt();
                scanner.nextLine(); // Consumir a quebra de linha
                System.out.print("Digite a categoria do vídeo: ");
                String categoria = scanner.nextLine();
                System.out.print("Digite a data de publicação (dd/MM/yyyy): ");
                String dataStr = scanner.nextLine();

                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date dataPublicacao = sdf.parse(dataStr);
                    Video video = new Video(titulo, descricao, duracao, categoria, dataPublicacao);
                    videos.add(video);

                    // Salvar no arquivo
                    BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
                    bw.write(video.toString());
                    bw.newLine();
                    bw.close();

                    System.out.println("Vídeo adicionado com sucesso!");
                } catch (Exception e) {
                    System.out.println("Erro ao adicionar vídeo: " + e.getMessage());
                }
            } else if (opcao == 2) {
                System.out.println("\n=== Lista de Vídeos ===");
                for (Video video : videos) {
                    System.out.println("Título: " + video.titulo);
                    System.out.println("Descrição: " + video.descricao);
                    System.out.println("Duração: " + video.duracao + " minutos");
                    System.out.println("Categoria: " + video.categoria);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    System.out.println("Data de Publicação: " + sdf.format(video.dataPublicacao));
                    System.out.println("-----------------------");
                }
            } else if (opcao == 3) {
                System.out.print("Digite o título do vídeo que deseja buscar: ");
                String busca = scanner.nextLine();
                boolean encontrado = false;
                for (Video video : videos) {
                    if (video.titulo.contains(busca)) {
                        System.out.println("\n=== Vídeo Encontrado ===");
                        System.out.println("Título: " + video.titulo);
                        System.out.println("Descrição: " + video.descricao);
                        System.out.println("Duração: " + video.duracao + " minutos");
                        System.out.println("Categoria: " + video.categoria);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        System.out.println("Data de Publicação: " + sdf.format(video.dataPublicacao));
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) {
                    System.out.println("Vídeo não encontrado.");
                }
            } else if (opcao == 4) {
                System.out.println("Saindo do sistema...");
                break;
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }
}