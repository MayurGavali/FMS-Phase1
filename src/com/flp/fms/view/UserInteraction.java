package com.flp.fms.view;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
//import java.sql.Date;
import java.util.Scanner;
import java.util.Set;

import com.flp.fms.domain.Actor;
import com.flp.fms.domain.Category;
import com.flp.fms.domain.Film;
import com.flp.fms.domain.Language;
import com.flp.fms.util.Validate;

public class UserInteraction {
	
	Scanner sc=new Scanner(System.in);
	
	//Function to prompt the user to enter the Film_Id
		public int readFilmId(){
			
			System.out.println("Enter Film Id");
			return sc.nextInt();
		}
		
		//Function to prompt the user to enter the Title
		public String readTitle(){
				
			System.out.println("Enter Film Title");
			String title = sc.nextLine();
			return title;
		}
		//Function to prompt the user to enter the Actor's FirstName
		public String readFirstName(){
				
			System.out.println("Enter First Name of Actor");
			return sc.next();
		}
		
		//Function to prompt the user to enter the Actor's LastName
		public String readLastName(){
					
			System.out.println("Enter Last name of Actor");
			return sc.next();
		}
		
		//Function to prompt the user to enter the Film Rating
		public int readRating(){
					
			System.out.println("Enter Film Rating");
			return sc.nextInt();
		}
		public Film addFilm(List<Language> languages,List<Category> categories,List<Actor> allActors){
			
			Film film = new Film();
			
			//Local variables
			String title;
			String releaseDate;
			Language originalLanguage;
			String rentalDuration;
			int length;
			int ratings;
			Set<Language> otherLanguages;
			Set<Actor> actors;
			Category category;
			
			boolean flag = false;
			
			
			//To get Film Title and to Validate it.
			do{
				
				System.out.println("Enter Film Title");
				title = sc.nextLine();
				
				flag = Validate.isValidTitle(title);
				
				if(!flag)
					System.out.println("Inavlid title. Enter a valid title!");
				
			}while(!flag);
			
			film.setTitle(title);
			
			//To get Film Description
			System.out.println("Enter Film Description: ");
			film.setFilm_Description(sc.nextLine());
			
			

			//To get Release Date and Validate it.
			do{
				
				System.out.println("Enter Release Date [dd-mmm-yyyy]: ");
				releaseDate = sc.next();
				
				flag = Validate.isValidDate(releaseDate);
				
				if(flag){
					
					Date currentDate = new Date();
					Date releaseYear = new Date(releaseDate);
					
					//Checking whether release date is on or before current date
					if(releaseYear.before(currentDate)||releaseYear.equals(currentDate))
						film.setRelease_Date(releaseYear);
					
					else{
						System.out.println("Invalid date. Release date should be on or before current date");
						flag = false;
					}
				}
				
				else
					System.out.println("Inavlid date format. Please enter correct format!");
				
			}while(!flag);
			
			
			//To get Rental duration and Validate it.
			do{
				
				System.out.println("Enter Rental Duration [dd-mmm-yyyy]: ");
				rentalDuration = sc.next();
				
				flag = Validate.isValidDate(rentalDuration);
				
				if(flag){
					
					Date rental_duration = new Date(rentalDuration);
					
					//checking whether rental duration is on or after release date
					if(film.getRelease_Date().before(rental_duration)||film.getRelease_Date().equals(rental_duration))
						film.setRental_Duration(rental_duration);
					
					else{
						System.out.println("Invalid date. Rental duration should be on or before release date");
						flag = false;
					}
				}
				
				else
					System.out.println("Inavlid date format. Please enter correct format!");
				
			}while(!flag);
			
			//To get Film Length and Validate it.
			do{
				
				System.out.println("Enter Length of the Film in Minutes: ");
				length = sc.nextInt();
				
				flag = Validate.isValidLength(length);
				
				if(!flag)
					System.out.println("Invalid length. Length must be between 0 and 1000");
			}while(!flag);
			
			film.setLenght(length);
		
			
			
			//To get Replacement cost
			System.out.println("Enter Replacement Cost: ");
			film.setReplacement_Cost(sc.nextDouble());
			
			
			
			//To get Category
			category = selectCategory(categories);
			film.setCategory(category);
			
			
			
			//To get Special Feature
			System.out.println("Enter Special Features: ");
			film.setSpecial_features(sc.next());
			
			
			
			//To get and Validate Rating
			do{
				
				System.out.println("Enter Rating for the Film: ");
				ratings = sc.nextInt();
				
				flag = Validate.isValidRating(ratings);
				
				if(!flag)
					System.out.println("Enter Valid Rating!");
				
			}while(!flag);
			
			film.setRating(ratings);	
			
			
			
			//Choose Language
			System.out.println("Choose Original Language");
			Language language= selectLanguage(languages);
			film.setOriginal_Language(language);
			
			
			//Add all languages
			List<Language> languages2=new ArrayList<>();
			String choice;
			boolean flag_langs;
			do{
				System.out.println("Choose All Languages for the Film:");
				Language language1= selectLanguage(languages);
				
				
				flag_langs=Validate.checkDuplicateLanguage(languages2, language1);
				if(!flag_langs)
					languages2.add(language1);
				else
					System.out.println("Language already Exists. Please try other languages!");
				
				
				System.out.println("Wish to add More Languages?[y|n]");
				choice=sc.next();
			}while(choice.charAt(0)=='y'||choice.charAt(0)=='Y');
			film.setLanguage(languages2);
			

			
			
			//To get all Actors
			actors = new HashSet<>();
			System.out.println("Add all actors");
			String choice1;
			do{
				Actor actor = selectActor(allActors);
				actors.add(actor);
								
				System.out.println("do you want to enter another actor? [y/n]");
				choice1 = sc.next();
				
			}while(choice1.charAt(0)=='y'||choice1.charAt(0)=='Y');
							
			film.setActor(actors);
			
			
			
			return film;
		}
		
		
		
		//Function to select the Language from the List
		public Language selectLanguage(List<Language> languages){
			
			Language selectedLanguage=null;
			boolean flag = false;
			
			do{	
				
				//Print Language Details
				System.out.println("\tLANGUAGES");
				for(Language language:languages)
					System.out.println(language.getLanguage_Id() + "\t" + language.getLanguage_Name());
				
				System.out.println("Choose Language:");
				int option=sc.nextInt();
				
				//Checking whether a valid Language Id
				for(Language language: languages)
				{
					if(option==language.getLanguage_Id())
					{
						flag=true;
						selectedLanguage=language;					
						break;
					}
				}
				
				//Print Error Message for invalid Language Id
				if(!flag)
					System.out.println("Please select valid Language Id!");
				
			}while(!flag);	
			
			return selectedLanguage;
		}
		
		
		
		//Function to select the Category from the List
		public Category selectCategory(List<Category> categories){
			
			Category selectedCategory=null;
			boolean flag = false;
			
			do{	
				
				//Print Category Details
				System.out.println("\tCATEGORIES");
				for(Category category:categories)
					System.out.println(category.getCategory_Id() + "\t" + category.getCategory_Name());
				
				System.out.println("Choose the Category:");
				int option=sc.nextInt();
				
				//Checking whether a valid Language Id
				for(Category category:categories)
				{
					if(option==category.getCategory_Id())
					{
						flag=true;
						selectedCategory=category;					
						break;
					}
				}
				
				//Print Error Message for invalid Category Id
				if(!flag)
					System.out.println("Please select valid Category Id!");
				
			}while(!flag);	
			
			return selectedCategory;
		}
		
		
		
		//Function to select an Actor from the List
		public Actor selectActor(List<Actor> allActors){
			
			Actor selectedActor = null;
			boolean flag = false;
			
			do{	
				
				//Print Actor Details
				System.out.println("\tACTORS");
				for(Actor actor: allActors)
					System.out.println(actor.getActor_Id() + "\t" + actor.getFirstName() + " " + actor.getLastName());
				
				System.out.println("Choose the Actor:");
				int option=sc.nextInt();
				
				//Checking whether the entered Actor Id is valid
				for(Actor actor: allActors)
				{
					if(option==actor.getActor_Id())
					{
						flag=true;
						selectedActor=actor;					
						break;
					}
				}
				
				//Print Error Message for invalid Actor Id
				if(!flag)
					System.out.println("Please select valid Actor Id!");
				
			}while(!flag);	
			
			
			return selectedActor;
		}
		
		
		
		//To List all the Film in the List
		public void getAllFilm(Collection<Film> films){
			
			//getting all the films in the Film repository
			//Collection<Film> films = allFilms.values();
			
			if(films.isEmpty())
				System.out.println("No Film Exists!");
			
			else{
				
				System.out.println("FILM ID"+ "\t" + " TITLE" + "\t" + "DESCRIPTION" +"\t"
					+ "RELEASE_YEAR" + "\t" + "ORIGINAL_LANGUAGE" + "\t" + "OTHER_LANGUAGES" + "\t"
					+ "RENTAL_DURATION"	+ "\t" + "LENGTH" + "\t" + "REPLACEMENT_COST" + "\t"
					+ "RATING" + "\t" + "SPECIAL_FEATURES" + "\t" + "ACTORS" + "\t" + "CATEGORY");
						
						
				for(Film film: films){
							
					printFilm(film);
				}
			}
		}
		
		

		//Function to Print the Film
		public void printFilm(Film film){
			
			
			if (film!=null) {
				
				String otherLanguages = "";
				String allActors = "";
				//getting the name of all languages in which film is released
				for (Language language : film.getLanguage()) {

					otherLanguages = otherLanguages + language.getLanguage_Name() + ", ";
				}
				//getting the name of all the actors in the film
				for (Actor actor : film.getActor()) {

					allActors = allActors + actor.getFirstName() + " " + actor.getLastName() + ", ";
				}
				System.out.println("\n" + film.getFilm_Id() + "\t" + film.getTitle() + "\t" + film.getFilm_Description() + "\t"
						+ film.getRelease_Date() + "\t" + film.getOriginal_Language().getLanguage_Name() + "\t"
						+ otherLanguages + "\t" + film.getRental_Duration() + "\t" + film.getLenght() + "\t"
						+ film.getReplacement_Cost() + "\t" + film.getRating() + "\t" + film.getSpecial_features() + "\t"
						+ allActors + "\t" + film.getCategory().getCategory_Name());
			}
			
			else
				System.out.println("Film does not exist!");
		
		
		}
		
		
	}//end of the class
		
