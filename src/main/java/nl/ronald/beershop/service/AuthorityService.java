package nl.ronald.beershop.service;

import nl.ronald.beershop.model.Authority;

import java.util.List;

public interface AuthorityService {

    public List<Authority> getAllAuthorities();
    //public Authority getAuthority(long id);
    public void save(Authority Authority);
    public void deleteById(long id);

}