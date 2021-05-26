package ca.qc.colval.finalprojectweb.BLL.Controllers;

import ca.qc.colval.finalprojectweb.BLL.Models.Compte;
import ca.qc.colval.finalprojectweb.BLL.Models.DTO.CompteDTO;
import ca.qc.colval.finalprojectweb.BLL.Services.Interfaces.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class IndexController {
    private final CompteService compteService;


    @Autowired
    public IndexController(CompteService compteService) {
        this.compteService = compteService;
    }

    @GetMapping("")
    public String index() {
        return "index/index";
    }

    @GetMapping("login")
    public String login(Model model) {
        model.addAttribute("compte", new CompteDTO());
        return "index/login";
    }

    @GetMapping("signup")
    public String addCompte(Model model) {
        model.addAttribute("compte", new CompteDTO());
        return "index/signup";
    }

    @PostMapping("search")
    public String searchCompte(@Valid CompteDTO compte, RedirectAttributes rattrs){
        Optional<Compte> compteForId = compteService.search(compte);
        if (compteForId.isPresent())
        {
            rattrs.addAttribute("compteID",  compteForId.get().getCompteId());
            return "redirect:/compte/connection";
        }
        else
        {
            return "redirect:/login";
        }
    }

    @PostMapping("save")
    public String saveCompte(@Valid CompteDTO compte) {
        compteService.save(compte);
        return "redirect:/login";
    }

    @PostMapping("create")
    public String createCompte(@Valid CompteDTO compte) {
        compteService.save(compte);
        return "redirect:/login";
    }
}
