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
        this.allBooks.save(new Book("Reise um den Mond",
                "Jules Verne",
                dvIsbnNumber.valueOf("978-3-453-31583-9")));
        this.allBooks.save(new Book("In 80 Tagen um die Welt",
                "Jules Verne",
                dvIsbnNumber.valueOf("978-3-257-20246-5")));
        this.allBooks.save(new Book("Auris",
                "Sebastian Fitzek",
                dvIsbnNumber.valueOf("978-3-426-30840-0")));
        this.allBooks.save(new Book("Der Marsianer",
                "Andy Weir",
                dvIsbnNumber.valueOf("978-3-596-52241-5")));
    }

    public void add(@Valid BookDto bookDto) {
        allBooks.save(convertDtoToBook(bookDto));
    }

    public List<Book> getAllBooks() {
            return allBooks.findAll();
        }

    /**
     * @param search Suchbegriff
     * @param filter kann Title, Autor oder leer sein; leer entspricht Autor
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
     * @param sort kann Titel, Autor oder leer sein; leer entspricht Titel
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

    public void deleteBook(Long id) {
        allBooks.deleteById(id);
    }

    public Book findBook(Long id) {
        Book book = allBooks.findById(id).get();
        return book;
    }

    public void saveBook(@Valid BookDto bookDto) {
        Book book = allBooks.findById(bookDto.getId()).get();
        book = convertDtoToBook(bookDto);
        allBooks.save(book);
    }

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
