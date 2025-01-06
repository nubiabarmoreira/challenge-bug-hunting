package repository;

import model.Video;

import java.util.List;

public interface VideoRepository {
    void save(Video video);
    void saveAll(List<Video> videos);
    List<Video> findAll();
    void delete(Video video);
}