package ca.qc.colval.finalprojectweb.BLL.Controllers;

import ca.qc.colval.finalprojectweb.BLL.Models.Compte;
import ca.qc.colval.finalprojectweb.BLL.Models.Contact;
import ca.qc.colval.finalprojectweb.BLL.Models.DTO.ContactDTO;
import ca.qc.colval.finalprojectweb.BLL.Models.DTO.GroupDTO;
import ca.qc.colval.finalprojectweb.BLL.Models.DTO.PhoneDTO;
import ca.qc.colval.finalprojectweb.BLL.Models.Group;
import ca.qc.colval.finalprojectweb.BLL.Services.Interfaces.*;
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
    private final GroupService groupService;
    private final ServiceService serviceService;
    private Compte activeCompte;
    private Contact activeContact;

    @Autowired
    public CompteController(CompteService compteService, ContactService contactService, PhoneService phoneService, GroupService groupService, ServiceService serviceService) {
        this.compteService = compteService;
        this.contactService = contactService;
        this.phoneService = phoneService;
        this.groupService = groupService;
        this.serviceService = serviceService;
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
        activeContact = contactService.readOne(contactId).get();
        model.addAttribute("phones", phoneService.findPhoneByContactId(contactId));
        model.addAttribute("contact", activeContact);
        model.addAttribute("groups", groupService.findGroupByCompteId(activeCompte.getCompteId()));
        if(activeContact.getGroupId() == null) {
            model.addAttribute("currentGroup", "None");
        }
        else {
            model.addAttribute("currentGroup", groupService.readOne(activeContact.getGroupId()).get().getGroupName());
        }
        model.addAttribute("updatedGroup", new Group());
        return "/compte/contactDetails";
    }

    @PostMapping("contactDetail/updateContactGroup")
    public String updateContactGroup(@Valid Group group) {
        System.out.println(group);
        activeContact.setGroupId(group.getGroupId());
        contactService.updateGroup(activeContact);
        return "redirect:/compte/contactDetail/" + activeContact.getContactId();
    }

    @GetMapping("contactDetail/addPhone")
    public String createPhone(Model model) {
        model.addAttribute("phone", new PhoneDTO());
        model.addAttribute("contactID", activeContact.getContactId());
        return "compte/addPhone";
    }

    @PostMapping("createPhone")
    public String createPhone(@Valid PhoneDTO phone) {
        phone.setContactId(activeContact.getContactId());
        System.out.println("Post controller");
        phoneService.save(phone);
        return "redirect:/compte/contactDetail/" + activeContact.getContactId();
    }

    @GetMapping("groups")
    public String groups(Model model) {
        List<Group> groups = groupService.findGroupByCompteId(activeCompte.getCompteId());
        model.addAttribute("groups", groups);
        model.addAttribute("groupsCount", groups.size());
        return "compte/groups";
    }

    @GetMapping("addGroup")
    public String createGroup(Model model) {
        model.addAttribute("group", new GroupDTO());
        return "compte/addGroup";
    }

    @PostMapping("createGroup")
    public String createGroup(@Valid GroupDTO group) {
        group.setCompteId(activeCompte.getCompteId());
        groupService.save(group);
        return "redirect:/compte/groups";
    }

    @PostMapping("contactDetail/call")
    public String call(){
        serviceService.addCall(activeCompte.getCompteId(), activeContact.getContactId());
        return "redirect:/compte/homepage";
    }

    @PostMapping("contactDetail/SMS")
    public String SMS(){
        serviceService.addSMS(activeCompte.getCompteId(), activeContact.getContactId());
        return "redirect:/compte/homepage";
    }
}
