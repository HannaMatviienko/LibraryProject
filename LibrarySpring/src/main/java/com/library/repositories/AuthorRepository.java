package com.library.repositories;

import com.library.dto.AuthorDTO;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<AuthorDTO, Integer> {
    Long countById(Integer id);
}
