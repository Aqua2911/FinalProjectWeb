package ca.qc.colval.finalprojectweb.BLL.Controllers;

import ca.qc.colval.finalprojectweb.BLL.Models.Compte;
import ca.qc.colval.finalprojectweb.BLL.Models.Contact;
import ca.qc.colval.finalprojectweb.BLL.Models.DTO.*;
import ca.qc.colval.finalprojectweb.BLL.Models.Group;
import ca.qc.colval.finalprojectweb.BLL.Models.Service;
import ca.qc.colval.finalprojectweb.BLL.Services.Interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;

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

    //DIRECTION HOMEPAGE
    @GetMapping("connection")
    public String connection(@ModelAttribute("compteID") String compteID){
        activeCompte = compteService.findCompteById(Long.parseLong(compteID));
        return "redirect:/compte/homepage";
    }

    @GetMapping("homepage")
    public String homepage(Model model) {
        model.addAttribute("compte", activeCompte);
        activeContact = null;
        return "compte/homepage";
    }

    //METHODS FROM HOMEPAGE
    @GetMapping("contacts")
    public String contacts(Model model) {
        List<Contact> contacts = contactService.findContactByCompteId(activeCompte.getCompteId());
        model.addAttribute("contacts", contacts);
        model.addAttribute("contactsCount", contacts.size());
        return "compte/contacts";
    }

    @GetMapping("groups")
    public String groups(Model model) {
        List<Group> groups = groupService.findGroupByCompteId(activeCompte.getCompteId());
        model.addAttribute("groups", groups);
        model.addAttribute("groupsCount", groups.size());
        return "compte/groups";
    }

    @PostMapping("addCredits")
    public String addCredits(){
        compteService.addCredits(activeCompte);
        return "redirect:/compte/homepage";
    }

    @GetMapping("servicesHistory")
    public String servicesHistory(Model model, ContactDTO filterContact) {
        List<Service> services = new ArrayList<>();
        Hashtable<Long, String> contactNamePerId = new Hashtable<>();

        if(filterContact.getFirstName() == null || filterContact.getFirstName().equals("")){
            services = serviceService.findByCompteId(activeCompte.getCompteId());
            List<Contact> contactPerService = contactService.findContactByCompteId(activeCompte.getCompteId());
            for (Contact contact: contactPerService) {
                contactNamePerId.put(contact.getContactId(), contact.getFirstName());
            }
        }
        else{
            Optional<Contact> testContact = contactService.findByFirstNameAndCompteId(filterContact.getFirstName(), activeCompte.getCompteId());
            if(testContact.isPresent()){
                services = serviceService.findByCompteIdAndContactId(activeCompte.getCompteId(), testContact.get().getContactId());
                contactNamePerId.put(testContact.get().getContactId(), testContact.get().getFirstName());
            }
        }
        model.addAttribute("services", services);
        model.addAttribute("contactNames", contactNamePerId);
        model.addAttribute("totalCost", services.stream().mapToDouble(Service::getCost).sum());
        model.addAttribute("filterContact", new ContactDTO());
        return "compte/servicesHistory";
    }

    @PostMapping("logout")
    public String logout(){
        activeCompte = null;
        return "redirect:../";
    }

    //METHODS FROM CONTACTS
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

    //METHODS FROM CONTACT DETAIL
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

    @PostMapping("contactDetail/updateContactGroup")
    public String updateContactGroup(@Valid Group group) {
        System.out.println(group);
        activeContact.setGroupId(group.getGroupId());
        contactService.updateGroup(activeContact);
        return "redirect:/compte/contactDetail/" + activeContact.getContactId();
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

    //METHODS FROM GROUPS
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
}
