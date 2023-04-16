package ism.gestionsapprovisionnement.controllers;

import ism.gestionsapprovisionnement.security.entities.AppUser;
import ism.gestionsapprovisionnement.security.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
    @Autowired
    SecurityService service;

    @GetMapping("/")
    public String index(@AuthenticationPrincipal UserDetails userDetails){
        String path="redirect:/login";
        if(userDetails.getAuthorities()
                .stream()
                .filter(role->role.getAuthority().compareTo("Admin")==0)
                .findFirst()
                .isPresent()){
               return "redirect:/client/liste-clients";
        }
       if(userDetails.getAuthorities()
                .stream()
                .filter(role->role.getAuthority().compareTo("Client")==0)
                .findFirst()
                .isPresent()){
                   AppUser user= service.getUserByUsername(userDetails.getUsername());
                   return "redirect:/liste-cmde-client?id="+user.getId();
        }

        return path;
    }
    @GetMapping("/403")
    public String error403(){
        return "errors/403";
    }
    @GetMapping("/404")
    public String error404(){
        return "errors/403";
    }
}
