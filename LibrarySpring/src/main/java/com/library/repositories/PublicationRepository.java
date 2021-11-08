package com.library.repositories;

import com.library.dto.PublicationDTO;
import org.springframework.data.repository.CrudRepository;


public interface PublicationRepository extends CrudRepository<PublicationDTO, Integer> {
    Long countById(Integer id);
}
