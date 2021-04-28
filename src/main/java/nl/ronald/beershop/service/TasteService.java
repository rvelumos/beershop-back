package nl.ronald.beershop.service;

import nl.ronald.beershop.model.Taste;

import java.util.List;

public interface TasteService {

    public List<Taste> getAllTastes();
    public Taste getCategory(long id);
    public void save(Taste taste);
    public void deleteById(long id);
}
