package service;

import model.Video;
import model.ListaDeCategoria;
import repository.FileVideoRepository;
import repository.VideoRepository;
import strategy.CategorySearchStrategy;
import strategy.SearchStrategy;
import strategy.TitleSearchStrategy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class VideoManager {
    private final VideoService videoService;
    private final VideoRepository videoRepository;

    public VideoManager (){
        this.videoService = new VideoServiceImpl(new FileVideoRepository("videos.txt"));
        this.videoRepository = new FileVideoRepository("videos.txt");
    }

    public void adicionarVideo (Scanner scanner) {
        System.out.println("\nVamos dar início à adição de novo vídeo!\n");

        System.out.print("Digite o título do vídeo: ");
        String titulo = scanner.nextLine();

        System.out.print("Digite a descrição do vídeo: ");
        String descricao = scanner.nextLine();

        System.out.print("Digite a duração do vídeo (em minutos): ");
        int duracao = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite a categoria do vídeo: \n");
        for (ListaDeCategoria listaDeCategoria : ListaDeCategoria.values()){
            System.out.println(listaDeCategoria.getDescritionCategoria());
        }
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
            System.out.println("Erro ao adicionar vídeo: " + e.getMessage());
        }
    }

    public void listarVideos () {
        System.out.println("\nSegue listagem dos vídeos existentes: \n");
        List<Video> videos = videoService.listVideos();
        for (Video video : videos) {
            System.out.println(video);
        }
    }

    public void pesquisarVideoPorTitulo (Scanner scanner) {
        System.out.println("Digite o título do vídeo que deseja pesquisar: ");
        String tituloParaBusca = scanner.nextLine();

        if (tituloParaBusca == null || tituloParaBusca.isBlank()) {
            System.out.println("O título não pode estar vazio. Tente novamente.");
            return;
        }

        SearchStrategy searchStrategy = new TitleSearchStrategy();
        List<Video> resultados = searchStrategy.search(videoService.listVideos(), tituloParaBusca);

        if (resultados.isEmpty()) {
            System.out.println("\nNenhum vídeo encontrado com o título fornecido.");
        } else {
            System.out.println("\nSeguem os vídeos encontrados de acordo com sua pesquisa: \n");
            for (Video video : resultados) {
                System.out.println(video);
            }
        }
    }

    public void pesquisarVideoPorCategoria (Scanner scanner) {
        System.out.println("Digite a categoria do vídeo que deseja pesquisar: ");
        String categoriaParaBusca = scanner.nextLine();

        if (categoriaParaBusca == null || categoriaParaBusca.isBlank()) {
            System.out.println("A categoria não pode estar vazia. Tente novamente.");
            return;
        }

        SearchStrategy searchStrategy = new CategorySearchStrategy();
        List<Video> resultados = searchStrategy.search(videoService.listVideos(), categoriaParaBusca);

        if (resultados.isEmpty()) {
            System.out.println("\nNenhum vídeo encontrado com a categoria fornecida.");
        } else {
            System.out.println("\nSeguem os vídeos encontrados de acordo com sua pesquisa: \n");
            for (Video video : resultados) {
                System.out.println(video);
            }
        }
    }

    public void editarVideo (Scanner scanner) {
        List<Video> videos = videoRepository.findAll();

        System.out.println("Digite o título do vídeo que deseja editar: ");
        String videoParaEditar = scanner.nextLine();

        if (videoParaEditar == null || videoParaEditar.isBlank()) {
            System.out.println("Digite um título para busca.");
            return;
        }

        Video encontrarVideoParaEditar = null;
        for (Video video : videos) {
            if (video.getTitulo().equalsIgnoreCase(videoParaEditar)) {
                encontrarVideoParaEditar = video;
                break;
            }
        }

        if (encontrarVideoParaEditar == null) {
            System.out.println("Nenhum vídeo encontrado com o título fornecido.");
            return;
        }

        System.out.println("Digite o novo título do vídeo (ou pressione Enter para manter o atual):");
        String novoTitulo = scanner.nextLine();
        if (!novoTitulo.isBlank()) {
            encontrarVideoParaEditar.setTitulo(novoTitulo);
        }

        System.out.println("Digite a nova descrição do vídeo (ou pressione Enter para manter a atual):");
        String novaDescricao = scanner.nextLine();
        if (!novaDescricao.isBlank()) {
            encontrarVideoParaEditar.setDescricao(novaDescricao);
        }

        System.out.println("Digite a nova categoria do vídeo:");
        String novaCategoria = scanner.nextLine();
        if (!novaCategoria.isBlank()) {
            encontrarVideoParaEditar.setCategoria(novaCategoria);
        } else {
            System.out.println("A categoria não pode estar vazia.");
            return;
        }

        System.out.println("Digite a nova duração do vídeo:");
        int novaDuracao = scanner.nextInt();
        scanner.nextLine();
        if (novaDuracao <= 0) {
            System.out.println("A duração deve ser igual ou maior do que zero.");
            return;
        } else {
            encontrarVideoParaEditar.setDuracao(novaDuracao);
        }

        System.out.println("Digite a nova data de publicação do vídeo:");
        String novaDataPublicacao = scanner.next();
        Date inputData;
        try {
            inputData = new SimpleDateFormat("dd/MM/yyyy").parse(novaDataPublicacao);
        } catch (ParseException e) {
            System.out.println("Digite uma data válida.");
            return;
        }

        videoRepository.save(encontrarVideoParaEditar);
        System.out.println("Vídeo editado com sucesso!");
    }

    public void excluirVideo (Scanner scanner) {
        List<Video> videos = videoRepository.findAll();

        System.out.println("Digite o título do vídeo que deseja excluir: ");
        String videoParaExcluir = scanner.nextLine();

        if (videoParaExcluir == null || videoParaExcluir.isBlank()) {
            System.out.println("Digite um título para busca.");
            return;
        }

        Video encontrarVideoParaExcluir = null;
        for (Video video : videos) {
            if (video.getTitulo().equalsIgnoreCase(videoParaExcluir)) {
                encontrarVideoParaExcluir = video;
                break;
            }
        }

        if (encontrarVideoParaExcluir == null) {
            System.out.println("Nenhum vídeo encontrado com o título fornecido.");
            return;
        }

        videoService.removeVideo(encontrarVideoParaExcluir);
        System.out.println("Vídeo excluído com sucesso!");
    }

    public void ordenarVideos () {
        List<Video> videos = videoRepository.findAll();
        videos.sort(Comparator.comparing(Video :: getDataPublicacao));
        videoRepository.saveAll(videos);

        System.out.println("Vídeos ordenados por data de publicação com sucesso!");
    }

    public void relatorioEstatistica (Scanner scanner) {
        System.out.println("\nEscolha o tipo de relatório que deseja: ");
        System.out.println("\n1- Número total de vídeos. \n2- Duração total de todos os vídeos. \n3- Quantidade de vídeos por categoria.\n");
        int tipoDeRelatorio = scanner.nextInt();

        switch (tipoDeRelatorio){
            case 1:
                System.out.println("fazer");
                break;
            case 2:
                System.out.println("fazer");
                break;
            case 3:
                System.out.println("fazer");
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }
}
