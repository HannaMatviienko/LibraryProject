package com.library.repositories;

import com.library.dto.UserDTO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<UserDTO, Integer> {
    Long countById(Integer id);
    Optional<UserDTO> findByEmail(String email);
    List<UserDTO> findByRole(String roles);
}