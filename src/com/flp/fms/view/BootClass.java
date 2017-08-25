package com.flp.fms.view;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import com.flp.fms.domain.Film;
import com.flp.fms.domain.Language;
import com.flp.fms.service.ActorServiceImpl;
import com.flp.fms.service.FilmServiceImpl;
import com.flp.fms.service.IActorService;
import com.flp.fms.service.IFilmService;

public class BootClass {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		UserInteraction ui = new UserInteraction();
		IFilmService filmService = new FilmServiceImpl();
		IActorService actorService = new ActorServiceImpl();
		
		Film film;
		
		String choice;
		
		do {
			
			menuSelection();
			System.out.println("enter your choice [1-5]: ");
			int option = sc.nextInt();
			switch (option) {

			case 1:
				film = ui.addFilm(filmService.getLanguages(), filmService.getCategory(),
						actorService.getActors());
				filmService.addFilm(film);
				break;

			case 2:
				int filmId = ui.readFilmId();
				film = filmService.searchFilm(filmId);
				if(film==null)
					System.out.println("Entered Film does not Exsist");
					//ui.printFilm(film);
				else{
					
					ui.printFilm(film);
					film = ui.addFilm(filmService.getLanguages(), filmService.getCategory(),
							actorService.getActors());
					film.setFilm_Id(filmId);
					filmService.updateFilm(film);       
				}
					
				
				
				
				break;

			case 3:
				System.out.println("\t1.remove by id\n\t2.remove by title\n\t3.remove by rating\n"
						+ "\t4.remove film by actor\n");
				System.out.println("enter your option");
				int opt=sc.nextInt();
				switch (opt) {
				
					case 1:
						filmService.removeFilm(ui.readFilmId());
						break;
						
					case 2:
						filmService.removeFilm(ui.readTitle());
						break;
						
					case 3:
						filmService.removeFilmByRating(ui.readRating());
						break;
						
					case 4:
						filmService.removeFilm(ui.selectActor(actorService.getActors()));
						break;

				default:
					filmService.removeFilmByRating(ui.readRating());
					break;
				}
				
				break;

			case 4:
				
				System.out.println("\t1.search by Id\n\t2.search by Title\n\t3.search by Rating\n"
						+ "\t4.search by Actor\n\t5.search by Language");
				System.out.println("Enter your option");
				int op=sc.nextInt();
				
				switch(op){
				
					case 1:
						film = filmService.searchFilm(ui.readFilmId());
						ui.printFilm(film);
						break;
						
					case 2:
						film = filmService.searchFilm(ui.readTitle());
						ui.printFilm(film);
						break;
						
					case 3:
						List<Film> films = filmService.searchFilmByRating(ui.readRating());
						ui.getAllFilm(films);;
						break;
						
					case 4:
						List<Film> selectedFilms = filmService.searchFilm(ui.selectActor(actorService.getActors()));
						ui.getAllFilm(selectedFilms);
						break;
						
					case 5:
						List<Language> allLanguages = filmService.getLanguages();
						List<Film> selectedFilms2 = filmService.searchFilm(ui.selectLanguage(allLanguages));
						ui.getAllFilm(selectedFilms2);
						break;
						
					default:
						System.out.println("Wrong Option Entered");
				}
				break;

			case 5:
				ui.getAllFilm(filmService.getAllFilms().values());
				break;

			default:
				System.out.println("Wrong Option Entered");
			}
			
			
			System.out.println("\nDO YOU WANT TO PROCEED TO MAIN MENU? [y/n]");
			choice = sc.next();
			
		} while (choice.charAt(0)=='y'||choice.charAt(0)=='Y');
		

	}
	
	public static void menuSelection(){
		
		System.out.println("****MENU SELECTION****");
		System.out.println("1.Add Film");
		System.out.println("2.Modify Film");
		System.out.println("3.Remove Film");
		System.out.println("4.Search Film");
		System.out.println("5.Get All Film");
		
	}

}
	
	

