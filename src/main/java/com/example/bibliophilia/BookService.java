package com.example.bibliophilia;

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

    private List<Book> allBooks;

    public BookService() {
        allBooks = new ArrayList<>();
        allBooks.add(new Book("QualityLand", "Marc-Uwe Kling", "978-3-548-29187-1"));
        allBooks.add(new Book("QualityLand 2.0", "Marc-Uwe Kling", "978-3-550-20102-8"));
        allBooks.add((new Book("Der Astronaut", "Andy Weir", "978-3-453-32134-2")));
        allBooks.add((new Book("Der Marsianer", "Andy Weir", "978-3-453-31583-9")));
    }

    public List<Book> filterByAuthor(String authorFilter) {
        return allBooks.stream().filter(book -> book.getAuthor().contains(authorFilter)).collect(Collectors.toList());
    }

    public void add(Book book) {
        allBooks.add(book);
    }
}
