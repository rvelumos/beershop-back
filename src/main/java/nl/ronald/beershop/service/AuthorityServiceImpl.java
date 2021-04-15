package nl.ronald.beershop.service;

import nl.ronald.beershop.exception.RecordNotFoundException;
import nl.ronald.beershop.model.Authority;
import nl.ronald.beershop.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public List<Authority> getAllAuthorities() {
        return authorityRepository.findAll();
    }

//    @Override
//    public Authority getAuthority(long id) {
//        return null;
//    }

//    @Override
//    public Authority getAuthority(String id) {
//        if (authorityRepository.existsById(id)) {
//            return authorityRepository.findById(id).get();
//        }
//        else {
//            throw new RecordNotFoundException();
//        }
//    }

    @Override
    public void save(Authority Authority) {
        authorityRepository.save(Authority);
    }

    @Override
    public void deleteById(long id) {

    }

//    @Override
//    public void deleteById(String id) {
//
//    }


}

