package service;

import model.Video;

import java.util.List;

public interface VideoService {
    void addVideo(Video video);
    void removeVideo(Video video);
    List<Video> listVideos();
}