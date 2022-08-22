package com.example.bibliophilia;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
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
     * Funktion zum Filtern der Buecher nach Autor
     * @param authorFilter Name des Autors, nach dem gefiltert werden soll
     * @return Liste mit Buechern vom ausgewaehlten Autor
     */
    public List<Book> filterByAuthor(String authorFilter) {
        List<Book> books = getAllBooks();
        return books.stream().filter(book -> book.getAuthor().contains(authorFilter)).collect(Collectors.toList());
    }

    /**
     * Funktion um ein neues Buch ins BookRepository hinzuzufuegen
     * @param bookDto Buch-Objekt, dass hinzugefuegt werden soll
     */
    public void add(@Valid BookDto bookDto) {
        allBooks.save(convertDtoToBook(bookDto));
    }

    /**
     * Funktion um alle Buecher aus dem BookRepository zu lesen
     * @return Liste mit allen Buechern
     */
    public List<Book> getAllBooks() {
        //TODO: Sortierfunktion implementieren
        return allBooks.findAll();
    }

    /**
     * Funktion zum loeschen eines Buchs
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
