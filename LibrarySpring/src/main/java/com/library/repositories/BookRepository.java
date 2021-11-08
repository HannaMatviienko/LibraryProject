package com.library.repositories;

import com.library.dto.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends CrudRepository<BookDTO, Integer> {
    Long countById(Integer id);
    List<BookDTO> findAll();
    @Query("SELECT b FROM BookDTO b " +
            "JOIN b.author a " +
            "JOIN b.publication p " +
            "WHERE CONCAT(b.yearPublication, '') LIKE :year " +
            "AND a.name LIKE :author " +
            "AND b.name LIKE :name " +
            "AND p.name LIKE :publication ")
    Page<BookDTO> findAll(@Param("name") String name,
                          @Param("author") String author,
                          @Param("publication") String publication,
                          @Param("year") String year,
                          Pageable pageable);

    @Query("SELECT b FROM BookDTO b " +
            "JOIN b.author a " +
            "JOIN b.publication p " +
            "WHERE CONCAT(b.yearPublication, '') LIKE :year " +
            "AND a.name LIKE :author " +
            "AND b.name LIKE :name " +
            "AND p.name LIKE :publication " +
            "AND b.available > 0 ")
    Page<BookDTO> findAllAvailable(@Param("name") String name,
                          @Param("author") String author,
                          @Param("publication") String publication,
                          @Param("year") String year,
                          Pageable pageable);

}
