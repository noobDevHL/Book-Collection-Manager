package com.example.bibliophilia;

import net.bytebuddy.TypeCache;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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
     * Funktion um ein neues Buch ins BookRepository hinzuzufuegen
     * @param bookDto Buch-Objekt, dass hinzugefuegt werden soll
     */
    public void add(@Valid BookDto bookDto) {
        allBooks.save(convertDtoToBook(bookDto));
    }

    /**
     * Funktion um alle Buecher aus dem BookRepository zu lesen
     * per default alphabetisch nach Titel sortiert
     * @return Liste mit allen Buechern
     */
    public List<Book> getAllBooks() {
            return allBooks.findAll();
        }

    /**
     * Funktion zum Filtern der Buecher nach Autor
     * @param search Name des Autors, nach dem gefiltert werden soll
     * @return Liste mit Buechern vom ausgewaehlten Autor
     */
    public List<Book> filterBy(String search, String filter) {
        List<Book> filteredBooks = getAllBooks();
        if(filter.equals("title")) {
            filteredBooks = filteredBooks.stream().filter(book -> book.getTitle().
                    contains(search)).collect(Collectors.toList());
        } else {
            filteredBooks = filteredBooks.stream().filter(book -> book.getAuthor().
                    contains(search)).collect(Collectors.toList());
        }
        return filteredBooks;
    }

    /**
     * Funktion zum alphabetischen Sortieren der Buecher nach Autorenvorname
     * @param sort
     * @return sortierte Liste
     */
    public List<Book> sortBy(String sort) {
        List<Book> sortedBooks = getAllBooks();
        if(sort.equals("author")) {
            sortedBooks = sortedBooks.stream()
                    .sorted(Comparator.comparing(Book::getAuthor))
                    .collect(Collectors.toList());
        } else {
            sortedBooks = sortedBooks.stream()
                    .sorted(Comparator.comparing(Book::getTitle))
                    .collect(Collectors.toList());
        }
        return sortedBooks;
    }

    /**
     * Funktion zum Loeschen eines Buchs
     * @param id ID des Buchs, das geloescht werden soll
     */
    public void deleteBook(Long id) {
        allBooks.deleteById(id);
    }

    /**
     * Funktion um ein Buch anhand der ID zu suchen
     * @param id
     * @return das gesuchte Buch
     */
    public Book findBook(Long id) {
        Book book = allBooks.findById(id).get();
        return book;
    }

    /**
     * Funktion um eine Aenderung an einem existierenden Buch zu speichern
     * @param bookDto
     */
    public void saveBook(@Valid BookDto bookDto) {
        Book book = allBooks.findById(bookDto.getId()).get();
        book = convertDtoToBook(bookDto);
        allBooks.save(book);
    }

    /**
     * Funktion zum Wandeln des Materials BookDto zu einer Entity Book
     * @param bookDto
     * @return
     */
    private Book convertDtoToBook(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setIsbn(bookDto.getIsbn());
        return book;
    }


    private BookDto convertBookToDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setIsbn(book.getIsbn());
        return  bookDto;
    }
}
