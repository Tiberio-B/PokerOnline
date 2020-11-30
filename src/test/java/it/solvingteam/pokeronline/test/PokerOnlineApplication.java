package it.solvingteam.pokeronline.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

public class PokerOnlineApplication implements CommandLineRunner  {
	
	@Autowired
	private TestService testService;

	public static void main(String[] args) {
		SpringApplication.run(PokerOnlineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		String casoDaTestare = TestService.INIT;

		System.out.println("################ START   #################");
		System.out.println("################ eseguo il test " + casoDaTestare + "  #################");

		testService.test(casoDaTestare);

		System.out.println("################ FINE   #################");
	}

}
