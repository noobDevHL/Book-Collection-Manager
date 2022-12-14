package com.example.bibliophilia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service für den BookController, der Logik als Dienst anbietet
 *
 * @author Aline Hoffmann
 */
@Service
public class BookService {
   @Autowired
    private BookRepository allBooks;

    public BookService(BookRepository allBooks) {
        this.allBooks = allBooks;
    }

    public void add(BookDto bookDto) {
        // TODO: check, ob ISBN schon im Repository
        allBooks.save(convertDtoToBook(bookDto));
    }

    public List<Book> getAllBooks() {
            return allBooks.findAll();
        }

    public void deleteBook(Long id) {
        allBooks.deleteById(id);
    }

    public Book findBook(Long id) {
        Book book = allBooks.findById(id).get();
        return book;
    }

    public void saveBook(BookDto bookDto) {
        // TODO: check, ob ISBN schon im Repository
        Book book = convertDtoToBook(bookDto);
        allBooks.save(book);
    }

    /**
     * @param search Suchbegriff
     * @param filter kann Title, Autor oder leer sein; leer entspricht Filtern nach Autor
     * @return Liste mit Buechern, die mit Suchbegriff im Titel oder Autor uebereinstimmen
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
     * @param sort kann Titel, Autor oder leer sein; leer entspricht Sortierung nach Titel
     * @return alphabetisch aufsteigend sortierte Liste
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

    //TODO: Sortierung einer gefilterten Liste

   // ---------------- Mapper-Methoden ---------------
    private Book convertDtoToBook(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setIsbn(dvIsbnNumber.valueOf(bookDto.getIsbn()));
        return book;
    }

    private BookDto convertBookToDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setIsbn(book.getIsbn().toString());
        return  bookDto;
    }
}
