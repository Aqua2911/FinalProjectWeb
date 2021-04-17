package ca.qc.colval.finalprojectweb.BLL.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompteController {

    @Autowired
    public CompteController() {

    }

    @GetMapping("compte/homepage")
    public String homepage(Model model) {
        return "compte/homepage";
    }
}
