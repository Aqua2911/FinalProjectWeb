package ca.qc.colval.finalprojectweb.BLL.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    public IndexController() {

    }

    @GetMapping("")
    public String index(Model model) {
        return "index/index";
    }

    @GetMapping("login")
    public String login() {
        return "index/login";
    }
}
