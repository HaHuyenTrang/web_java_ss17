package ra.ss17.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ra.ss17.entity.ProductCart;
import ra.ss17.repository.ProductCartRepository;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ProductCartRepository cartRepository;

    @PostMapping("/add")
    public String addToCart(@RequestParam int productId,
                            @RequestParam(defaultValue = "1") int quantity,
                            HttpSession session) {


        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/login";
        }

        // Kiểm tra đã có trong giỏ chưa
        ProductCart existing = cartRepository.findByCustomerAndProduct(customerId, productId);

        if (existing == null) {
            ProductCart newCart = new ProductCart();
            newCart.setCustomerId(customerId);
            newCart.setProductId(productId);
            newCart.setQuantity(quantity);
            cartRepository.save(newCart);
        } else {
            existing.setQuantity(existing.getQuantity() + quantity);
            cartRepository.update(existing);
        }

        return "redirect:/product/" + productId; // Quay lại trang sản phẩm

    }
}