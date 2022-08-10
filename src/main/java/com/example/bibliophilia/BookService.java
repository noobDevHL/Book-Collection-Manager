package com.example.bibliophilia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

  /*  public List<Book> filterByAuthor(String authorFilter) {
        return allBooks.stream().filter(book -> book.getAuthor().contains(authorFilter)).collect(Collectors.toList());
    }*/

    public void add(Book book) {
        allBooks.save(book);
    }

    public Iterable<Book> getAllBooks() {
        return allBooks.findAll();
    }
}
