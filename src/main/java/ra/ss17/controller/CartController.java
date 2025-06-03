package ra.ss17.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.ss17.entity.Product;
import ra.ss17.entity.ProductCart;
import ra.ss17.repository.ProductCartRepository;
import ra.ss17.repository.ProductRepositoryImpl;
import ra.ss17.service.ProductCartService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ProductCartRepository cartRepository;
    @Autowired
    private ProductRepositoryImpl productRepositoryImpl;
    @Autowired
    private ProductCartService productCartService;

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
        productCartService.addToCart(customerId,productId,quantity);
        return "redirect:/product/" + productId; // Quay lại trang sản phẩm

    }
    // Hiển thị trang giỏ hàng
    @GetMapping("/view")
    public String viewCart(HttpSession session, Model model) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/login";
        }

        List<ProductCart> cartItems = cartRepository.findByCustomerId(customerId);

        // Tính tổng tiền từng sản phẩm và tổng giá trị giỏ
        int totalPrice = 0;
        for (ProductCart item : cartItems) {
            Product product = productRepositoryImpl.findById(item.getProductId());
            item.setProductId(product); // set đối tượng Product để dùng trong Thymeleaf
            int itemTotal = (int) (product.getPrice() * item.getQuantity());
            item.setTotalPrice(itemTotal); // thuộc tính transient trong entity ProductCart
            totalPrice += itemTotal;
        }

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice);

        return "cart_view"; // file cart_view.html chính là template bạn gửi
    }

}