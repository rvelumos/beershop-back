package nl.ronald.beershop.service;

import nl.ronald.beershop.model.Media;

import java.util.List;

public interface MediaService {

    public List<Media> getAllMedia();
    public Media getMedia(long id);
    public void save(Media Media);
    public void deleteById(long id);

}