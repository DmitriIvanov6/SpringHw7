package ru.gb.Controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gb.Domain.Product;
import ru.gb.Repositories.ProductRepository;
import ru.gb.Servicies.Pagination;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/app/products/mvc")
public class MVCController {

    private final ProductRepository repository;

    public MVCController(ProductRepository repository) {
        this.repository = repository;
    }

//    @GetMapping
//    public String findAll(Model model) {
//        List<Product> products = new ArrayList<>();
//        repository.findAll().forEach(products::add);
//        model.addAttribute("products", products);
//        return "products";
//    }

    @GetMapping("/delete")
    public String deleteById(int id) {
        repository.deleteById(id);
        return "redirect:/app/products/mvc";
    }

    @GetMapping
    public String findAll(Model model, @RequestParam("page") Optional<Integer> page){
        int currentPage = page.orElse(1);
        Pageable thisPage = PageRequest.of(currentPage,10);
        List<Product> products = repository.findAll(thisPage).toList();
        model.addAttribute("products", products);
        Pagination pagination = new Pagination();
        List<Integer> pages = pagination.countPages(repository);
        model.addAttribute("pages", pages);
        return "products";
    }
}
