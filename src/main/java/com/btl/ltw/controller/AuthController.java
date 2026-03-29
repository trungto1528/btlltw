package com.btl.ltw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.btl.ltw.model.User;
import com.btl.ltw.services.HashPassword;
import com.btl.ltw.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService ase;
    @Autowired
    private HashPassword hashPassword;

    @GetMapping(path = { "/", "/login" })
    public String login(Model model) {

        return "login";
    }

    @GetMapping("/register")
    public String register() {

        return "register";
    }

    @PostMapping("/register")
    public String auth(@RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("email") String email,
            RedirectAttributes redirectAttribute,
            Model model) {
        System.out.println(email + " " + password + " " + username + " " + ase.existsByEmail(email));
        if (ase.existsByEmail(email)) {
            model.addAttribute("error", "Kiem tra lai Email");
            model.addAttribute("username", username);
            model.addAttribute("password", password);
            return "register";
        }
        if (ase.existsByUsername(username)) {
            model.addAttribute("error", "Kiem tra lai Username");
            model.addAttribute("username", username);
            model.addAttribute("password", password);
            return "register";
        }

        ase.saveUser(new User(username, hashPassword.hashPassword(password), email));
        redirectAttribute.addFlashAttribute("success", "Tao tai khoan thanh cong");
        return "redirect:/auth/login";
    }

    @PostMapping("/login")
    public String authenLogin(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model,
            HttpSession session) {
        System.out.println(email + " " + password + " " + ase.existsByEmail(email));
        User user = ase.findByEmail(email);
        if (user == null) {
            model.addAttribute("errorLogin", "1");
            model.addAttribute("email", email);
            model.addAttribute("password", password);
            return "login";
        } else if (hashPassword.checkPassword(password, user.getPassword()) == false) {
            model.addAttribute("errorLogin", "2");
            model.addAttribute("email", email);
            model.addAttribute("password", password);
            return "login";
        }
        session.setAttribute("roleId", user.getRole());
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}