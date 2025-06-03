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

    @GetMapping("/home")
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
    @GetMapping("/init")
    @ResponseBody
    public String initData() {
        Product p1 = new Product(0, "Áo sơ mi", "Áo sơ mi trắng form rộng", 200000, 100,
                "https://product.hstatic.net/1000096703/product/1_ed08837bea9b4ba1be7aceddfe9fee46_master.jpg");
        Product p2 = new Product(1, "Giày sneaker", "Giày thể thao nam", 500000, 50,
                "https://product.hstatic.net/1000312752/product/9ad06707545ec874dac433f8f0d6df2eb45c65c4119b75224706a0b38624078f777ed3_c6e623f8e2df430387fb8c8a07ca76b0.jpg");

        productRepository.save(p1);
        productRepository.save(p2);

        return "Thêm sản phẩm mẫu thành công!";
    }
}
