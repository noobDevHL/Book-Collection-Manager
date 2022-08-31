package com.example.bibliophilia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Controller zum Handling des Routings zwischen Server und Browser
 *
 * @author Aline Hoffmann
 */
@Controller
@RequestMapping("/")
public class BookController {

    @Autowired
    private BookService _bookService;

    @GetMapping("/allBooks")
    public String showBooks(Model model) {
        model.addAttribute("bookDto", new BookDto());
        model.addAttribute("allBooks", _bookService.getAllBooks());

        return "allBooks";
    }

    @GetMapping("/filtered")
    public String showFilteredBooks(Model model,
                                    @RequestParam(required = false, defaultValue = "") String search,
                                    @RequestParam(required = false, defaultValue = "") String filter) {
        model.addAttribute("bookDto", new BookDto());
        model.addAttribute("allBooks", _bookService.getAllBooks());
        model.addAttribute("allBooks", _bookService.filterBy(search, filter));
        model.addAttribute("search", search);
        model.addAttribute("filter", filter);

        return "allBooks";
    }

    @GetMapping("/sorted")
    public String showSortedBooks(Model model, @RequestParam(required = false, defaultValue = "") String sort) {
        model.addAttribute("bookDto", new BookDto());
        model.addAttribute("allBooks", _bookService.sortBy(sort));
        model.addAttribute("sort", sort);

        return "allBooks";
    }

    @GetMapping("/newBook")
    public String newBook(Model model) {
        model.addAttribute("bookDto", new BookDto());
        return "newBook";
    }

    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") long id) {
        _bookService.deleteBook(id);
        return "redirect:/allBooks";
    }

    @GetMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") long id, Model model) {
        model.addAttribute("bookDto", _bookService.findBook(id));
        return "editBook";
    }

    @PostMapping("/addBook")
    public String addNewBook(@ModelAttribute @Valid BookDto bookDto, BindingResult bindingResult) {
        validateIsbn(bookDto.getIsbn(), bindingResult);
        if (bindingResult.hasErrors()) {
            return "allBooks";
        }
        _bookService.add(bookDto);
        return "redirect:/allBooks";
    }

    @PostMapping("/editBook/saveBook")
    public String saveBook(@ModelAttribute @Valid BookDto bookDto, BindingResult bindingResult) {
        validateIsbn(bookDto.getIsbn(), bindingResult);
        if (bindingResult.hasErrors()) {
            return "editBook";
        }
        _bookService.saveBook(bookDto);
        return "redirect:/allBooks";
    }

    private void validateIsbn(String isbn, BindingResult bindingResult) {
        if(!dvIsbnNumber.isValid(isbn)) {
            bindingResult.rejectValue("isbn", "isbn.nichtValide");
        }
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
