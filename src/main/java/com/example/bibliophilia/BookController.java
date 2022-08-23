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

    @Autowired // Springboot-Annotation um Service automatisch zur Verfügung zu stellen
    private BookService _bookService;

    /**
     * Funktion um alle Buecher inklusiver Filter nach Attribut nach Get-Request an die View zu uebergeben
     * @param model
     * @param authorFilter
     * @return anzuzeigende View
     */
    @GetMapping("/allBooks")
    public String showBooks(Model model, @RequestParam(required = false, defaultValue = "") String authorFilter) {
        model.addAttribute("bookDto", new BookDto());
        model.addAttribute("allBooks", _bookService.getAllBooks());

        model.addAttribute("allBooks", _bookService.filterByAuthor(authorFilter));
        model.addAttribute("filter", authorFilter);

        return "allBooks";
    }

    /**
     * Funktion um Formular zur Anlage eines neuen Buchs anzuzeigen
     * @param model
     * @return anzuzeigende View
     */
    @GetMapping("/newBook")
    public String newBook(Model model) {
        model.addAttribute("bookDto", new BookDto());
        return "newBook";
    }

    /**
     * Funktion zum Loeschen eines Buchs aus der Sammlung
     * @param id ID des Buchs, das geloescht werden soll
     * @param model
     * @return Weiterleitung zur anzuzeigenden View
     */
    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") long id, Model model) {
        _bookService.deleteBook(id);
        return "redirect:/allBooks";
    }

    /**
     * Funktion um nach Absenden des Formulars (Post-Request) alle Buecher an die View zu uebergeben
     * @param bookDto
     * @param bindingResult
     * @param model
     * @return Weiterleitung zur anzuzeigenden View
     */
    @PostMapping("/addBook")
    public String addNewBook(@ModelAttribute @Valid BookDto bookDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
           bindingResult.getFieldError();
            return "newBook";
        }
        model.addAttribute(bookDto);
        _bookService.add(bookDto);
        return "redirect:/allBooks";
    }

    @GetMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") long id, Model model) {
        model.addAttribute("bookDto", _bookService.findBook(id));
        return "editBook";
    }

    @PostMapping("/editBook/saveBook")
    public String saveBook(@ModelAttribute @Valid BookDto bookDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldError();
            return "editBook";
        }
        _bookService.saveBook(bookDto);
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
