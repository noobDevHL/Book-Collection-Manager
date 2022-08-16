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

    /**
     * Funktion zum Filtern der Buecher nach Autor
     * @param authorFilter Name des Autors, nach dem gefiltert werden soll
     * @return Liste mit Buechern vom ausgewaehlten Autor
     */
    public List<Book> filterByAuthor(String authorFilter) {
        List<Book> books = (List<Book>) getAllBooks();
        return books.stream().filter(book -> book.getAuthor().contains(authorFilter)).collect(Collectors.toList());
    }

    /**
     * Funktion um ein neues Buch ins BookRepository hinzuzufuegen
     * @param bookDto Buch-Objekt, dass hinzugefuegt werden soll
     */
    public void add(@Valid BookDto bookDto) {
        ModelMapper modelMapper = new ModelMapper();
        Book book = modelMapper.map(bookDto, Book.class);
        allBooks.save(book);
    }

    /**
     * Funktion um alle Buecher aus dem BookRepository zu lesen
     * @return Liste mit allen Buechern
     */
    public List<Book> getAllBooks() {
        //TODO: Sortierfunktion implementieren
        return allBooks.findAll();
    }
}
