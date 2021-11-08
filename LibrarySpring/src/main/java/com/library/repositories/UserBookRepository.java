package com.library.repositories;

import com.library.dto.UserBookDTO;
import com.library.dto.UserDTO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserBookRepository extends CrudRepository<UserBookDTO, Integer> {
    Long countById(Integer id);
    List<UserBookDTO> findAllByUserAndStatusNotOrderByStatusAscDateTakeAsc(UserDTO user, int status);
    List<UserBookDTO> findAllByUserAndStatusOrderByDateBackAsc(UserDTO user, int status);
    List<UserBookDTO> findAllByStatus(int status);
}
