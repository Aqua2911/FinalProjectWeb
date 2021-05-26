package ca.qc.colval.finalprojectweb.BLL.Controllers;

import ca.qc.colval.finalprojectweb.BLL.Models.Compte;
import ca.qc.colval.finalprojectweb.BLL.Services.Interfaces.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("compte")
public class CompteController {
    private final CompteService compteService;
    private Compte activeCompte;

    @Autowired
    public CompteController(CompteService compteService) {
        this.compteService = compteService;
    }

    @GetMapping("connection")
    public String connection(@ModelAttribute("compteID") String compteID){
        activeCompte = compteService.findCompteById(Long.parseLong(compteID));
        System.out.println(activeCompte);
        return "redirect:/compte/homepage";
    }

    @GetMapping("homepage")
    public String homepage(Model model) {
        model.addAttribute("compte", activeCompte);
        return "compte/homepage";
    }

    @PostMapping("addCredits")
    public String addCredits(){
        compteService.addCredits(activeCompte);
        return "redirect:/compte/homepage";
    }
}
