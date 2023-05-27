package com.test.serti.babel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.test.serti.babel.models.entity.Book;
import com.test.serti.babel.models.entity.Location;
import com.test.serti.babel.service.IBookService;
import com.test.serti.babel.service.ILocationService;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("location")
@RequestMapping("/location")
public class LocationController {
  @Autowired
  private IBookService bookService;
  @Autowired
  private ILocationService locationService;

  @GetMapping("/form/{book_id}")
  public String addLocation(@PathVariable(value = "book_id") Long book_id, Model model) {

    Book book = bookService.findOne(book_id);
    if (book == null) {

      return "redirect:/list";
    }

    Location location = new Location();
    location.setBook(book);
    model.addAttribute("location", location);
    model.addAttribute("title", "Añadir ubicación");
    model.addAttribute("nameBtn", "Guardar");

    return "location/form";
  }

  @PostMapping("/form")

  public String saveLocation(@Valid Location location, BindingResult result, Model model, SessionStatus status) {

    if (result.hasErrors()) {
      model.addAttribute("title", "Añadir ubicación");
      model.addAttribute("nameBtn", "Guardar");

      return "location/form";

    }

    locationService.save(location);
    status.setComplete();

    return "redirect:/details/" + location.getBook().getId();
  }

  @GetMapping("/update/{id}")
  public String updateLocation(@PathVariable(value = "id") Long id, Model model) {
    Location location = locationService.findOne(id);
    model.addAttribute("location", location);
    model.addAttribute("title", "Actualizar Ubicacion");
    model.addAttribute("nameBtn", "Actualizar");

    return "location/form";
  }

  @GetMapping("/eliminar/{id}")
  public String deteleteLocation(@PathVariable(value = "id") Long id) {
    Location location = locationService.findOne(id);

    locationService.delete(id);

    return "redirect:/details/" + location.getBook().getId();
  }

}
