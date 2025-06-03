package ra.ss17.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ra.ss17.dto.LoginDTO;
import ra.ss17.entity.Customer;
import ra.ss17.repository.CustomerRepository;

import javax.validation.Valid;
import java.util.List;


@Controller
public class AuthController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("customer") Customer customer,
                           BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        customerRepository.save(customer);
        return "redirect:/login";
    }


    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("customer", new LoginDTO());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("customer") @Valid LoginDTO loginDTO,
                        BindingResult result,
                        Model model) {
        if (result.hasErrors()) {
            return "login";
        }

        List<Customer> matched = customerRepository.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());

        if (matched.isEmpty()) {
            model.addAttribute("errorMessage", "Tên đăng nhập hoặc mật khẩu không đúng");
            return "login";
        }

        Customer loggedIn = matched.get(0);
        if ("ADMIN".equalsIgnoreCase(loggedIn.getRole())) {
            return "redirect:/admin";
        } else {
            return "redirect:/home";
        }
    }
}
