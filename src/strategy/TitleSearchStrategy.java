package strategy;

import model.Video;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TitleSearchStrategy implements SearchStrategy {
    Scanner input = new Scanner(System.in);

    @Override
    public List<Video> search(List<Video> videos, String query) {
        return videos.stream()
                .filter(video -> video.getTitulo().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}