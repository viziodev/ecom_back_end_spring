package ism.gestionsapprovisionnement.controllers;

import ism.gestionsapprovisionnement.dto.ClientDtoForm;
import ism.gestionsapprovisionnement.entities.Client;
import ism.gestionsapprovisionnement.repositories.ClientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;
    @GetMapping("/client/liste-clients")
    public String listerClient(Model model,
                               @RequestParam(name = "page",defaultValue = "0") int page,
                               @RequestParam(name = "size",defaultValue = "5") int size,
                               @RequestParam(name = "keyword",defaultValue = "") String keyword){
        Page<Client> pageClients=clientRepository.findByTelephoneContains(keyword,PageRequest.of(page,size));
         model.addAttribute("listClients",pageClients.getContent());
         model.addAttribute("pages",new int[pageClients.getTotalPages()]);
         model.addAttribute("currentPage",page);
         model.addAttribute("keyword",keyword);
        return "client";
    }



    @GetMapping("/home")
    public String homePage() {
        return "home";
    }
    ///form-client

    @GetMapping("admin/form-client")
    public String loadForClient(Model model){
        ClientDtoForm client=ClientDtoForm.builder().build();
        model.addAttribute("client",client);
        return "form.client";
    }

    @PostMapping("admin/save-client")
    public String saveClient(
            @Valid ClientDtoForm clientDtoForm,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
          redirectAttributes.addFlashAttribute("mode","error");
            redirectAttributes.addFlashAttribute("message","Erreur de Saisie");
        }else{
            redirectAttributes.addFlashAttribute("mode","succes");
            redirectAttributes.addFlashAttribute("message","Client Enregistre avec Success");
            Client client=clientDtoForm.toEntity();
            clientRepository.save(client);

        }
        return "redirect:/admin/form-client";
    }
}
