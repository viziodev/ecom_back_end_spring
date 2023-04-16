package ism.gestionsapprovisionnement;


import ism.gestionsapprovisionnement.entities.*;
import ism.gestionsapprovisionnement.repositories.*;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class GestionsapprovisionnementApplication implements CommandLineRunner {
	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private CommandeRepository commandeRepository;
	@Autowired
	private CategorieRepository categorieRepository;

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
	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

		  return new CorsFilter(urlBasedCorsConfigurationSource);
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

          //Insertion des Categories
			Categorie cat1=new Categorie(null,"Alimentaire",null);
					categorieRepository.save(cat1);
			Categorie cat2=new Categorie(null,"Cosmetiques",null);
			categorieRepository.save(cat2);


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
                 Article article=new Article();
				 article.setLibelle("Article"+i);
				  article.setAncienPrice(2000*i);
				  article.setNouveauPrice(1800*i);
				  article.setPhoto("https://dummyimage.com/450x300/dee2e6/6c757d.jpg");
				  article.setPromo(i%2==0);
				   article.setQteStock(10*i);
				  article.setCategorie(i%2==0?cat1:cat2);

				articleRepository.save(article);
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
