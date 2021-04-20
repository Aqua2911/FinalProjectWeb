package ca.qc.colval.finalprojectweb.BLL.Controllers;

import ca.qc.colval.finalprojectweb.BLL.Models.Compte;
import ca.qc.colval.finalprojectweb.BLL.Models.DTO.CompteDTO;
import ca.qc.colval.finalprojectweb.BLL.Services.Interfaces.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class IndexController {
    private final CompteService compteService;


    @Autowired
    public IndexController(CompteService compteService) {
        this.compteService = compteService;
    }

    @GetMapping("")
    public String index(Model model) {
        return "index/index";
    }

    @GetMapping("login")
    public String login() {
        return "index/login";
    }

    @GetMapping("signup")
    public String addCompte(Model model) {
        model.addAttribute("compte", new CompteDTO());
        return "index/signup";
    }

    @PostMapping("create")
    public String createCompte(@Valid CompteDTO compte) {
        compteService.save(compte);
        return "redirect:/login";
    }
}
