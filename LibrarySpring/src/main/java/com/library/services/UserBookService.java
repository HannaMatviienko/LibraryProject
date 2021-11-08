package com.library.services;

import com.library.dto.UserBookDTO;
import com.library.dto.UserDTO;
import com.library.repositories.UserBookRepository;
import com.library.tools.UserBookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserBookService {

    private final UserBookRepository repo;

    @Autowired
    public UserBookService(UserBookRepository repo) {
        this.repo = repo;
    }

    public UserBookDTO findById(int id) {
        return repo.findById(id).orElse(null);
    }

    public List<UserBookDTO> listCard(UserDTO user)
    {
        return repo.findAllByUserAndStatusNotOrderByStatusAscDateTakeAsc(user, 3);
    }

    public List<UserBookDTO> listFine(UserDTO user)
    {
        return repo.findAllByUserAndStatusOrderByDateBackAsc(user, 3);
    }

    public List<UserBookDTO> listLibrarian()
    {
        return repo.findAllByStatus(0);
    }

    public void save(UserBookDTO car) {
        repo.save(car);
    }

    public UserBookDTO get(Integer id) throws UserBookNotFoundException {
        Optional<UserBookDTO> result = repo.findById(id);
        if (result.isPresent())
            return result.get();
        else
            throw new UserBookNotFoundException("Could not find any book with ID " + id);
    }

    public void delete(Integer id) throws UserBookNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new UserBookNotFoundException("Could not find any book with ID " + id);
        }
        repo.deleteById(id);
    }
}
