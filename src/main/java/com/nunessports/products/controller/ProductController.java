package com.nunessports.products.controller;

import com.nunessports.products.model.Product;
import com.nunessports.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String listAllProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "list";
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product id: " + id));

        model.addAttribute("product", product);
        return "view";
    }

    @GetMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "form";
    }

    @PostMapping("/new")
    public String createProduct(@ModelAttribute Product product) {
        if (product.getPrice() < 0) {
            throw new IllegalArgumentException("Negative price");
        }

        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String editProduct(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product id: " + id));

        model.addAttribute("product", product);
        return "form";
    }

    @PostMapping("/{id}/edit")
    public String updateProduct(@PathVariable String id, @ModelAttribute Product product) {
        if (product.getPrice() < 0) {
            throw new IllegalArgumentException("Negative price");
        }

        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product id: " + id));

        productRepository.delete(product);
        return "redirect:/products";
    }

}
