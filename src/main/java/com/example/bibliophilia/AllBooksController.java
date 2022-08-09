package com.example.bibliophilia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller zum Handling des Routings zwischen Server und Browser
 *
 * @author Aline Hoffmann
 */
@Controller
@RequestMapping("/allBooks")
public class AllBooksController {

    @Autowired // Springboot-Annotation um Service automatisch zur Verfügung zu stellen
    private BookService _bookService;

    @GetMapping
    public String showAllBooks(Model model, @RequestParam(required = false, defaultValue = "") String authorFilter) {
        model.addAttribute("allBooks", _bookService.filterByAuthor(authorFilter));
        model.addAttribute("filter", authorFilter);
        return "allBooks";
    }
    // "Reise um den Mond", "Jules Vernes", "978-3-596-52241-5"

    @PostMapping
    public String createNewBook(Model model, @ModelAttribute Book book) {
        _bookService.add(book);
        return "redirect:/allBooks";
    }

    /*
    Alternative zum GetMapping mit View als String-Rückgabe und Model als Parameter.
    Stattdessen ModelAndView-Servlet von Springboot mit testView (wird nicht verwendet)

    @GetMapping
    public ModelAndView test() {
        ModelAndView test = new ModelAndView("testView");
        test.addObject("allBooks", allBooks);
        return test;
    }
    */
}
