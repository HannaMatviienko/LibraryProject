package com.library.services;

import com.library.dto.PublicationDTO;
import com.library.repositories.PublicationRepository;
import com.library.tools.PublicationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicationService {

    private final PublicationRepository repo;

    @Autowired
    public PublicationService(PublicationRepository repo) {
        this.repo = repo;
    }

    public List<PublicationDTO> listAll()
    {
        return (List<PublicationDTO>) repo.findAll();
    }

    public void save(PublicationDTO car) {
        repo.save(car);
    }

    public PublicationDTO get(Integer id) throws PublicationNotFoundException {
        Optional<PublicationDTO> result = repo.findById(id);
        if (result.isPresent())
            return result.get();
        else
            throw new PublicationNotFoundException("Could not find any publication with ID " + id);
    }

    public void delete(Integer id) throws PublicationNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new PublicationNotFoundException("Could not find any publication with ID " + id);
        }
        repo.deleteById(id);
    }
}
