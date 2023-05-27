package com.test.serti.babel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.test.serti.babel.models.entity.Book;
import com.test.serti.babel.service.IBookService;

import jakarta.validation.Valid;


@Controller
@SessionAttributes("book")
public class bookController {
    @Autowired
    private IBookService bookService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("title", "Libros");
        model.addAttribute("books", bookService.findAll());

        return "index";
    }

    @GetMapping("/details/{id}")
    public String details(Model model, @PathVariable(value = "id") Long id) {
        Book book = bookService.findOne(id);

        if (book == null) {
            return "redirect:/list";
        }
        model.addAttribute("titleBook", "Detalles del libro");
        model.addAttribute("titleLocation", "Ubicacion del libro");
        model.addAttribute("book", book);

        return "bookDetails";
    }

    @GetMapping("/form")
    public String addBook(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        model.addAttribute("title", "Nuevo libro");
        model.addAttribute("nameBtn", "Guardar");

        return "form";

    }

    @PostMapping("/form")
    public String saveBook(@Valid Book book, BindingResult result, Model model, SessionStatus status) {

        if (result.hasErrors()) {
            model.addAttribute("title", "Nuevo libro");
            model.addAttribute("nameBtn", "Guardar");

            return "form";
        }

        if (bookService.existsByVolumeNumber(book.getVolumeNumber()) & book.getId() == null) {
            result.rejectValue("volumeNumber", "error.book", "El volumen ingresado ya está en uso");
            model.addAttribute("title", "Nuevo libro");
            model.addAttribute("nameBtn", "Guardar");
            return "form";
        }

        bookService.save(book);
        status.setComplete();
        
        return "redirect:/list";
    }

    @GetMapping("/form/{id}")
    public String updateBook(@PathVariable(value = "id") Long id, Model model) {
        Book book = bookService.findOne(id);
        model.addAttribute("book", book);
        model.addAttribute("title", "Actualizar libro");
        model.addAttribute("nameBtn", "Actualizar");

        return "form";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteBook(@PathVariable(value = "id") Long id) {
        bookService.delete(id);
        return "redirect:/list";
    }

}
