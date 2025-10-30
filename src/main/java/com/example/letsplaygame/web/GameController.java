package com.example.letsplaygame.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GameController {

    private static final List<String> store = new ArrayList<>();

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("now", LocalDateTime.now());
        model.addAttribute("itens", List.of("Relatório", "Métricas", "Exportação"));
        model.addAttribute("pageTitle", "Dashboard");
        return "dashboard";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("pageTitle", "Login");
        model.addAttribute("loginMessage", "Bem-vindo!");
        return "login";
    }

    @PostMapping("/do-something")
    public String doSomething(Model model) {
        store.add("Novo registro " + LocalDateTime.now());
        model.addAttribute("message", "Feito com sucesso!");
        model.addAttribute("itens", new ArrayList<>(store));
        model.addAttribute("now", LocalDateTime.now());
        model.addAttribute("pageTitle", "Dashboard");
        return "dashboard";
    }
}
