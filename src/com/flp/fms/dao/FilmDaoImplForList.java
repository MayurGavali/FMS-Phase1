package com.flp.fms.dao;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.flp.fms.domain.Actor;
import com.flp.fms.domain.Category;
import com.flp.fms.domain.Film;
import com.flp.fms.domain.Language;
public class FilmDaoImplForList implements IFilmDao {

	private HashMap<Integer, Film> filmRepository=new HashMap<>();
	
	//Adding List of Categories
	@Override
	public List<Category> getCategory() {
		List<Category> categories=new ArrayList<>();
		categories.add(new Category(1, "Drama"));
		categories.add(new Category(2, "Comedy"));
		categories.add(new Category(3, "Horror"));
		categories.add(new Category(4, "Scientific"));
		categories.add(new Category(5, "Romantic"));
		categories.add(new Category(6, "Action"));
		
		
		return categories;
	}

	//Adding List of Languages
	@Override
	public List<Language> getLanguage() {
		List<Language> languages=new ArrayList<>();
		languages.add(new Language(1, "English"));
		languages.add(new Language(2, "Hindi"));
		languages.add(new Language(3, "Telegu"));
		languages.add(new Language(4, "Marati"));
		languages.add(new Language(5, "Kananta"));
		languages.add(new Language(6, "Tamil"));
		languages.add(new Language(7, "Malayalam"));
		return languages;
	}
	//CURD Operation
		@Override
		public void addFilm(Film film) {
			filmRepository.put(film.getFilm_Id(), film);
			
		}


		@Override
		public Map<Integer, Film> getAllFilms() {
			
			return filmRepository;
		}
		//Searching Film using Film_Id
		@Override
		public Film searchFilm(int filmId) {
				
			return filmRepository.get(filmId);
		}
		
		
		//Searching Film using Title
		@Override
		public Film searchFilm(String title) {
			
			Film result = null;
			
			Collection<Film> films = filmRepository.values();
			Iterator<Film> itr = films.iterator();
			
			while(itr.hasNext()){
				
				Film film = itr.next();
				if(film.getTitle().equals(title)){
					
					result = film;
					break;
				}
			}
			
			return result;
		}
		
		
		//Searching Film using Rating
		@Override
		public List<Film> searchFilmByRating(int rating) {

			List<Film> selectedFilms = new ArrayList<>();
			
			Collection<Film> films = filmRepository.values();
			Iterator<Film> itr = films.iterator();
			
			while(itr.hasNext()){
				
				Film film = itr.next();
				
				if(film.getRating()==rating){
					
					selectedFilms.add(film);
				}
			}
			
			
			return selectedFilms;
		}

		
		//Searching Film using Language
		@Override
		public List<Film> searchFilm(Language language) {
			
			List<Film> selectedFilms = new ArrayList<>();
			
			Collection<Film> films = filmRepository.values();
			Iterator<Film> itr = films.iterator();
			
			while(itr.hasNext()){
				
				Film film = itr.next();
				
				//if original language is same as entered language
				if(film.getOriginal_Language().getLanguage_Id() == language.getLanguage_Id()){
					
					selectedFilms.add(film);
				}
				
				//if film is released in entered language
				else{
					
					List<Language> languages = new ArrayList<>();
					languages = film.getLanguage();
					
					for(Language lang : languages){
						
						if(lang.getLanguage_Id()==language.getLanguage_Id()){
							
							selectedFilms.add(film);
						}
					}
				}
			
			}
			
			return selectedFilms;
		}
			
		
		
		//Searching Film using Actor
		@Override
		public List<Film> searchFilm(Actor actor) {
			
			List<Film> selectedFilms = new ArrayList<>();
			
			Collection<Film> films = filmRepository.values();
			Iterator<Film> itr = films.iterator();
			
			while(itr.hasNext()){
				
				Film film = itr.next();
				Set<Actor> actors = new HashSet<>();
				actors = film.getActor();
				
				for(Actor act : actors){
					
				if(act.getActor_Id()==actor.getActor_Id()){
						
						selectedFilms.add(film);
					}
					
				}
			}
			return selectedFilms;
		}

			
		
		//Search Film by Release Date
		@Override
		public Film searchFilm(String title, Date releaseDate, int rating) {
			
			Film result=null;
			
			Collection<Film> films = filmRepository.values();
			Iterator<Film> itr = films.iterator();
			
			while(itr.hasNext()){
				
				Film film = itr.next();
				
				if(film.getTitle().equals(title) && film.getRelease_Date().equals(releaseDate) && film.getRating()==rating){
					
					result =film;
					break;
				}
			}
			
			return result;
		}

		
		//Function to remove Film by Film_Id 
		@Override
		public void removeFilm(int filmId) {
			
			filmRepository.remove(filmId);
			
		}

		//Function to remove Film by Title
		@Override
		public void removeFilm(String title) {
			
			Collection<Film> allFilms = filmRepository.values();
			Iterator<Film> itr = allFilms.iterator();
			
			int filmId=0;
			
			while(itr.hasNext()){
				
				Film film = itr.next();
				
				if(film.getTitle().equals(title)){
					
					filmId = film.getFilm_Id();
					break;
				}
			}
			
			filmRepository.remove(filmId);	
		}
		

		
		//Function to remove Film by Rating
		@Override
		public void removeFilmByRating(int rating) {
			
			Collection<Film> allFilms = filmRepository.values();
			Iterator<Film> itr = allFilms.iterator();
			
			Set<Integer> selectedFilmIds = new HashSet<>();
			
			while(itr.hasNext()){
				
				Film film = itr.next();
				
				if(film.getRating() == rating){
					
					selectedFilmIds.add(film.getFilm_Id());	
				}
			}
			
			for(Integer filmId : selectedFilmIds){
				
				filmRepository.remove(filmId);
			}
		}

		
		//Function to remove Film by Actor
		@Override
		public void removeFilm(Actor actor) {
							
			Collection<Film> films = filmRepository.values();
			Iterator<Film> itr = films.iterator();
			
			Set<Integer> selectedFilmIds = new HashSet<>();
			
			while(itr.hasNext()){
				
				Film film = itr.next();
				
				//getting the name of all actors in the film
				Set<Actor> actors = new HashSet<>();
				actors = film.getActor();
				
				for(Actor act : actors){
					
					if(act.getActor_Id() == actor.getActor_Id()){
						
						selectedFilmIds.add(film.getFilm_Id());
					}
					
				}
			}
			
			for(Integer filmId : selectedFilmIds){
				
				filmRepository.remove(filmId);
			}

		}

		
		//Function to Update Film
		@Override
		public void updateFilm(Film film) {
				
			addFilm(film);
		}

			

	}

