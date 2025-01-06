package repository;

import model.Video;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileVideoRepository implements VideoRepository {
    private final File file;

    public FileVideoRepository(String filePath) {
        this.file = new File(filePath);
    }

    @Override
    public void save(Video video) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(video.toString());
            bufferedWriter.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }

    @Override
    public List<Video> findAll() {
        List<Video> videos = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Video video = Video.fromString(line);
                if (video != null) {
                    videos.add(video);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao procurar o arquivo: " + e.getMessage());
        }
        return videos;
    }

    @Override
    public void delete(Video video) {
        List<Video> videos = findAll();

        boolean videoParaDeletar = videos.removeIf(v -> v.getTitulo() == video.getTitulo());

        if (!videoParaDeletar) {
            System.out.println("Vídeo não encontrado no repositório.");
            return;
        }

        save(video);
    }
}