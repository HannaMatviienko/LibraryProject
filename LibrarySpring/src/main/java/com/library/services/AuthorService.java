package com.library.services;

import com.library.dto.AuthorDTO;
import com.library.repositories.AuthorRepository;
import com.library.tools.AuthorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository repo;

    @Autowired
    public AuthorService(AuthorRepository repo) {
        this.repo = repo;
    }

    public List<AuthorDTO> listAll()
    {
        return (List<AuthorDTO>) repo.findAll();
    }

    public void save(AuthorDTO car) {
        repo.save(car);
    }

    public AuthorDTO get(Integer id) throws AuthorNotFoundException {
        Optional<AuthorDTO> result = repo.findById(id);
        if (result.isPresent())
            return result.get();
        else
            throw new AuthorNotFoundException("Could not find any author with ID " + id);
    }

    public void delete(Integer id) throws AuthorNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new AuthorNotFoundException("Could not find any car with ID " + id);
        }
        repo.deleteById(id);
    }
}
