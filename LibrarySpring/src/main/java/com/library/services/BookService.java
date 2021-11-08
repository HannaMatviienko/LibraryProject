package com.library.services;

import com.library.dto.BookDTO;
import com.library.dto.BookPage;
import com.library.repositories.BookRepository;
import com.library.tools.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository repo;

    @Autowired
    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    public List<BookDTO> listAll() {
        return (List<BookDTO>) repo.findAll();
    }

    public List<BookDTO> listAll(BookPage page) {
        Sort sort = Sort.by(page.getSortCol());
        if (Objects.equals(page.getSortDir(), "desc"))
            sort = sort.descending();
        else
            sort = sort.ascending();
        PageRequest request = PageRequest.of(page.getPage() - 1, page.getPageSize(), sort);
        Page<BookDTO> result = repo.findAll(
                page.getNormName(),
                page.getNormAuthor(),
                page.getNormPublication(),
                page.getNormYear(), request);

        page.setPageCount(result.getTotalPages());
        if (page.getPage() > page.getPageCount()) {
            page.setPage(1);
            result = repo.findAll(
                    page.getNormName(),
                    page.getNormAuthor(),
                    page.getNormPublication(),
                    page.getNormYear(), request);
        }
        return result.get().collect(Collectors.toList());
    }

    public List<BookDTO> listAllAvailable(BookPage page) {
        Sort sort = Sort.by(page.getSortCol());
        if (Objects.equals(page.getSortDir(), "desc"))
            sort = sort.descending();
        else
            sort = sort.ascending();
        PageRequest request = PageRequest.of(page.getPage() - 1, page.getPageSize(), sort);
        Page<BookDTO> result = repo.findAllAvailable(
                page.getNormName(),
                page.getNormAuthor(),
                page.getNormPublication(),
                page.getNormYear(), request);

        page.setPageCount(result.getTotalPages());
        if (page.getPage() > page.getPageCount()) {
            page.setPage(1);
            result = repo.findAllAvailable(
                    page.getNormName(),
                    page.getNormAuthor(),
                    page.getNormPublication(),
                    page.getNormYear(), request);
        }
        return result.get().collect(Collectors.toList());
    }


    public void save(BookDTO car) {
        repo.save(car);
    }

    public BookDTO get(Integer id) throws BookNotFoundException {
        Optional<BookDTO> result = repo.findById(id);
        if (result.isPresent())
            return result.get();
        else
            throw new BookNotFoundException("Could not find any book with ID " + id);
    }

    public void delete(Integer id) throws BookNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new BookNotFoundException("Could not find any book with ID " + id);
        }
        repo.deleteById(id);
    }
}
