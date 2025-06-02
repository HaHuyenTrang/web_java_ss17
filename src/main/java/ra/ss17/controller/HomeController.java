package ra.ss17.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.ss17.entity.Product;
import ra.ss17.repository.ProductRepository;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public String home(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "6") int size,
                       Model model) {
        List<Product> products = productRepository.getAll(page, size);
        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        return "home"; // home.html
    }

    // Trang chi tiết sản phẩm
    @GetMapping("/product/{id}")
    public String productDetail(@PathVariable("id") int id, Model model) {
        Product product = productRepository.findById(id);
        if (product == null) {
            return "redirect:/"; // hoặc trả về trang lỗi
        }
        model.addAttribute("product", product);
        return "product_detail"; // product_detail.html
    }
}
