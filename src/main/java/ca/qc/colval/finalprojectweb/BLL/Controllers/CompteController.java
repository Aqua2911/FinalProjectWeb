package ca.qc.colval.finalprojectweb.BLL.Controllers;

import ca.qc.colval.finalprojectweb.BLL.Models.Compte;
import ca.qc.colval.finalprojectweb.BLL.Models.Contact;
import ca.qc.colval.finalprojectweb.BLL.Models.DTO.ContactDTO;
import ca.qc.colval.finalprojectweb.BLL.Models.DTO.PhoneDTO;
import ca.qc.colval.finalprojectweb.BLL.Services.Interfaces.CompteService;
import ca.qc.colval.finalprojectweb.BLL.Services.Interfaces.ContactService;
import ca.qc.colval.finalprojectweb.BLL.Services.Interfaces.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("compte")
public class CompteController {
    private final CompteService compteService;
    private final ContactService contactService;
    private final PhoneService phoneService;
    private Compte activeCompte;
    private Long activeContactId;

    @Autowired
    public CompteController(CompteService compteService, ContactService contactService, PhoneService phoneService) {
        this.compteService = compteService;
        this.contactService = contactService;
        this.phoneService = phoneService;
    }

    @GetMapping("connection")
    public String connection(@ModelAttribute("compteID") String compteID){
        activeCompte = compteService.findCompteById(Long.parseLong(compteID));
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

    @GetMapping("contacts")
    public String contacts(Model model) {
        List<Contact> contacts = contactService.findContactByCompteId(activeCompte.getCompteId());
        model.addAttribute("contacts", contacts);
        model.addAttribute("contactsCount", contacts.size());
        return "compte/contacts";
    }

    @GetMapping("addContact")
    public String createContact(Model model) {
        model.addAttribute("contact", new ContactDTO());
        return "compte/addContact";
    }

    @PostMapping("create")
    public String createContact(@Valid ContactDTO contact) {
        contact.setCompteId(activeCompte.getCompteId());
        contactService.save(contact);
        return "redirect:/compte/contacts";
    }

    @GetMapping("contactDetail/{contactId}")
    public String contactDetail(Model model, @PathVariable Long contactId) {
        activeContactId = contactId;
        model.addAttribute("phones", phoneService.findPhoneByContactId(contactId));
        model.addAttribute("contact", contactService.readOne(contactId).get());
        return "/compte/contactDetails";
    }

    @GetMapping("contactDetail/addPhone")
    public String createPhone(Model model) {
        model.addAttribute("phone", new PhoneDTO());
        model.addAttribute("contactID", activeContactId);
        return "compte/addPhone";
    }

    @PostMapping("createPhone")
    public String createPhone(@Valid PhoneDTO phone) {
        phone.setContactId(activeContactId);
        System.out.println("Post controller");
        phoneService.save(phone);
        return "redirect:/compte/contactDetail/" + activeContactId;
    }
}
