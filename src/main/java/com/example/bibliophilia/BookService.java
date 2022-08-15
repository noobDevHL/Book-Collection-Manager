package com.example.bibliophilia;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service für den BookController, der Logik als Dienst anbietet
 *
 * @author Aline Hoffmann
 */
@Service
public class BookService {
    @Autowired // dient der Erzeugung des BookRepository, weil man ein Interface nicht instanziieren kann
    private BookRepository allBooks;

    public List<Book> filterByAuthor(String authorFilter) {
        List<Book> books = (List<Book>) getAllBooks();
        return books.stream().filter(book -> book.getAuthor().contains(authorFilter)).collect(Collectors.toList());
    }

    public void add(@Valid BookDTO bookDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Book book = modelMapper.map(bookDTO, Book.class);
        allBooks.save(book);
    }

    public Iterable<Book> getAllBooks() {
        return allBooks.findAll();
    }
}
