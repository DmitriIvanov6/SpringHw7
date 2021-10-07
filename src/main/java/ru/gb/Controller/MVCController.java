package ru.gb.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.Domain.Product;
import ru.gb.Repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/app/products/mvc")
public class MVCController {

    private final ProductRepository repository;

    public MVCController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String findAll(Model model) {
        List<Product> products = new ArrayList<>();
        repository.findAll().forEach(products::add);
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/delete")
    public String deleteById(int id) {
        repository.deleteById(id);
        return "redirect:/app/products/mvc";
    }


}
