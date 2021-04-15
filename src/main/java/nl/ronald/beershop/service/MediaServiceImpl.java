package nl.ronald.beershop.service;

import nl.ronald.beershop.exception.RecordNotFoundException;
import nl.ronald.beershop.model.Media;
import nl.ronald.beershop.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    @Override
    public List<Media> getAllMedia() {
        return mediaRepository.findAll();
    }

    @Override
    public Media getMedia(long id) {
        if (mediaRepository.existsById(id)) {
            return mediaRepository.findById(id).get();
        }
        else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void save(Media media) {
        mediaRepository.save(media);
    }

    @Override
    public void deleteById(long id) {
        if (mediaRepository.existsById(id)) {
            mediaRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException();
        }
    }


}

