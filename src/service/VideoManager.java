package service;

import model.Video;
import repository.FileVideoRepository;
import strategy.SearchFactory;
import strategy.SearchStrategy;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class VideoManager {
    private VideoService videoService;

    public VideoManager (){
        this.videoService = new VideoServiceImpl(new FileVideoRepository("videos.txt"));
    }

    public void adicionarVideo (Scanner scanner) {
        System.out.println("\nVamos dar início à adição de novo vídeo!\n");
        System.out.print("Digite o título do vídeo: ");
        String titulo = scanner.nextLine();
        System.out.print("Digite a descrição do vídeo: ");
        String descricao = scanner.nextLine();
        System.out.print("Digite a duração do vídeo (em minutos): ");
        int duracao = scanner.nextInt();
        System.out.print("Digite a categoria do vídeo: ");
        String categoria = scanner.nextLine();
        System.out.print("Digite a data de publicação (dd/MM/yyyy): ");
        String dataStr = scanner.nextLine();

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date dataPublicacao = simpleDateFormat.parse(dataStr);
            Video video = new Video(titulo, descricao, duracao, categoria, dataPublicacao);
            videoService.addVideo(video);
            System.out.println("Vídeo adicionado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar vídeo.");
        }
    }

    public void listarVideos () {
        System.out.println("\nSegue listagem dos vídeos existentes: \n");
        List<Video> videos = videoService.listVideos();
        for (Video video : videos) {
            System.out.println(video);
        }
    }

    public void pesquisarVideo (Scanner scanner) {
        System.out.println("1- Pesquisa por Título");
        System.out.println("2- Pesquisa por Categoria");
        System.out.println("\nDigite a opção para pesquisa: ");
        int opcaoDePesquisa = scanner.nextInt();

        SearchStrategy searchStrategy = SearchFactory.getSearch(opcaoDePesquisa);

        System.out.print(searchStrategy.showMessage());
        String query = scanner.nextLine();

        List<Video> resultados = searchStrategy.search(videoService.listVideos(), query);
        System.out.println("\nSeguem os vídeos encontrados de acordo com sua pesquisa: \n");
        for (Video video : resultados) {
            System.out.println(video);
        }
    }
}
