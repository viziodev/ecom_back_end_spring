package ism.gestionsapprovisionnement;

import ism.gestionsapprovisionnement.entities.*;
import ism.gestionsapprovisionnement.repositories.ArticleRepository;
import ism.gestionsapprovisionnement.repositories.ClientRepository;
import ism.gestionsapprovisionnement.repositories.CommandeRepository;
import ism.gestionsapprovisionnement.repositories.LigneCommandeRepository;
import ism.gestionsapprovisionnement.security.entities.AppRole;
import ism.gestionsapprovisionnement.security.entities.AppUser;
import ism.gestionsapprovisionnement.security.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class GestionsapprovisionnementApplication implements CommandLineRunner {
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CommandeRepository commandeRepository;

	@Autowired
	ArticleRepository articleRepository;
	@Autowired
	LigneCommandeRepository ligneCommandeRepository;

	@Autowired
	SecurityService service;
	public static void main(String[] args) {
		SpringApplication.run(GestionsapprovisionnementApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... args) throws Exception {
		/*for (int i = 1; i < 20; i++) {
			Client cl=new Client();
			cl.setNomComplet("Nom Prenom"+i);
			cl.setTelephone("77101011"+i);
			if(i%2==0){
				Adresse adresse=new Adresse();
				adresse.setVille("Ville"+i);
				adresse.setQuartier("Quartier"+i);
				adresse.setNumVilla("Num00"+i);
				cl.setAdresse(adresse);
			}
			clientRepository.save(cl);
		}
		for (int i = 1; i <=20 ; i++) {
			articleRepository.save(
				new Article(null,"Article"+i,null	)
			);
		}
		for (int i = 1; i <=20 ; i++) {
			Client cl=clientRepository.findByTelephone("77101011"+i);
			Article article=articleRepository.findById(Long.valueOf(i)).get();
			Adresse adresse=new Adresse();
			adresse.setVille("Ville"+i);
			adresse.setQuartier("Quartier"+i);
			adresse.setNumVilla("Num00"+i);
			Commande commande=new Commande(null
					,new Date(),
					100000,
					EtatCommande.Encours,cl,
					adresse);
			commandeRepository.save(commande);
			ligneCommandeRepository.save(
					new LigneCommande(
							null,10000,
							10000*100,100,
							commande,
							article));
		}*/


	}
	@Bean
	CommandLineRunner commandLineRunner(){
		return args->{
			AppRole role1=service.saveRole("Client");
			AppRole role2=service.saveRole("Admin");
			AppUser admin= service.saveUser("Birane Baila Wane","admin","passer");
			service.addRoleToUser("admin","Client");
			service.addRoleToUser("admin","Admin");

		   for (int i = 1; i < 20; i++) {
				Client cl=new Client();
				cl.setNomComplet("Nom Prenom"+i);
				cl.setTelephone("77101011"+i);
				if(i%2==0){
					Adresse adresse=new Adresse();
					adresse.setVille("Ville"+i);
					adresse.setQuartier("Quartier"+i);
					adresse.setNumVilla("Num00"+i);
					cl.setAdresse(adresse);
				}
				cl.setUsername("client"+i);
				cl.setPassword(passwordEncoder().encode("passer"));
				cl.getRoles().add(role1);
				clientRepository.save(cl);
			}
			for (int i = 1; i <=20 ; i++) {
				articleRepository.save(
						new Article(null,"Article"+i,null	)
				);
			}
			for (int i = 1; i <=20 ; i++) {
				Client cl=clientRepository.findByTelephone("77101011"+i);
				Article article=articleRepository.findById(Long.valueOf(i)).get();
				Adresse adresse=new Adresse();
				adresse.setVille("Ville"+i);
				adresse.setQuartier("Quartier"+i);
				adresse.setNumVilla("Num00"+i);
				Commande commande=new Commande(null
						,new Date(),
						100000,
						EtatCommande.Encours,cl,
						adresse);
				commandeRepository.save(commande);
				ligneCommandeRepository.save(
						new LigneCommande(
								null,10000,
								10000*100,100,
								commande,
								article));
			}


		};


	}

}
