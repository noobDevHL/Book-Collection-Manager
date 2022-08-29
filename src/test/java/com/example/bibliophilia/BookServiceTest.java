package com.example.bibliophilia;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookServiceTest {

    private BookService bookService;

    public BookServiceTest() {
        final List<Book> BOOKS = List.of(new Book("Reise um den Mond", "Jules Verne", dvIsbnNumber.valueOf("978-3-123-12345-0")), new Book("In 80 Tagen um die Welt", "Jules Verne", dvIsbnNumber.valueOf("978-3-456-56789-1")),new Book("Auris", "Sebastian Fitzek", dvIsbnNumber.valueOf("978-3-321-54321-9")));
        BookRepository bookRepositoryMock = mock(BookRepository.class);
        when(bookRepositoryMock.findAll()).thenReturn(BOOKS);
        bookService = new BookService(bookRepositoryMock);
    }

    @Test
    public void booksWrittenByJulesVerne() {
        List<Book> julesVerneBooks = bookService.filterBy("Jules Verne", "author");
        assertEquals(List.of("Jules Verne", "Jules Verne"), julesVerneBooks.stream().
                map(Book::getAuthor).
                collect(Collectors.toList()));
    }

    @Test
    public void booksSortedByTitle() {
        List<Book> sortedBooks = bookService.sortBy("title");
        assertEquals(List.of("Auris", "In 80 Tagen um die Welt", "Reise um den Mond"), sortedBooks.stream().
                map(Book::getTitle).
                collect(Collectors.toList()));
    }

    @Test
    public void booksSortedByAuthor() {
        List<Book> sortedBooks = bookService.sortBy("author");
        assertEquals(List.of("Jules Verne", "Jules Verne", "Sebastian Fitzek"), sortedBooks.stream().
                map(Book::getAuthor).
                collect(Collectors.toList()));
    }

    @Test
    public void testTwoIsbnEqual() {
        Book book1 = new Book("Reise um den Mond", "Jules Verne", dvIsbnNumber.valueOf("978-3-123-12345-0"));
        Book book2 = new Book("In 80 Tagen um die Welt", "Jules Verne", dvIsbnNumber.valueOf("978-3-123-12345-0"));
        assertEquals(book1.getIsbn(), book2.getIsbn());
    }
}
