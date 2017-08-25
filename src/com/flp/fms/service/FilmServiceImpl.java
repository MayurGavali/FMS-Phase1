                                                                                                                                                                                                                                                       package com.flp.fms.service;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.flp.fms.dao.FilmDaoImplForDB;
import com.flp.fms.dao.FilmDaoImplForList;
import com.flp.fms.dao.IFilmDao;
import com.flp.fms.domain.Actor;
import com.flp.fms.domain.Category;
import com.flp.fms.domain.Film;
import com.flp.fms.domain.Language;

public class FilmServiceImpl implements IFilmService {
	
	
//	private IFilmDao filmDao=new FilmDaoImplForList();
	private IFilmDao filmDao=new FilmDaoImplForDB();

	//To Get List Of Languages 
	@Override
	public List<Language> getLanguages() {
		
		return filmDao.getLanguage();
	}

	//To Get List of Category
	@Override
	public List<Category> getCategory() {
		 return filmDao.getCategory();
	}
	
	//To Add New Film in Repository 
	@Override
	public void addFilm(Film film) {
		
		film.setFilm_Id(generate_Film_Id());
		filmDao.addFilm(film);
		
	}
	
	//To Generate New Film_Id
	public int generate_Film_Id(){
		
		int filmId=0;
		
		//Verify filmId has been Duplicated or not
		do{
			double fid=Math.random()*1000;
			filmId=(int)fid;
		}while(isDuplicateFilmId(filmId));
		
		
		return filmId;
	}
	//CHECKING WHETHER GENERATED FILM ID ALREADY EXISTS IN THE REPOSITORY
		public boolean isDuplicateFilmId(int filmId){
			
			boolean flag =false;
			
			//retrieving all the keys in the Film Repository
			Collection<Integer> keys = filmDao.getAllFilms().keySet();
			
			Iterator<Integer> itr = keys.iterator(); 
			while(itr.hasNext()){
				
				if(itr.next() == filmId){
					
					flag = true;
					break;
				}
			}
			
			return flag;
			
		}
		
	//To Fetch The List of Films
	@Override
	public Map<Integer, Film> getAllFilms() {
		
		return filmDao.getAllFilms();
	}
	
	
	
	
	//Retrieving Film from Film Repository using Film_Id
		@Override
		public Film searchFilm(int filmId) {
				
			return filmDao.searchFilm(filmId);
		}
		
		//Retrieving Film from Film Repository using Film_Id
		@Override
		public List<Film> searchFilm(Language language) {
			
			return filmDao.searchFilm(language);
		}
		
		//Retrieving Film from Film Repository using Title
		@Override
		public Film searchFilm(String title) {
			
			return filmDao.searchFilm(title);
		}
		
		
		//Retrieving Film from Film Repository using Rating
		@Override
		public List<Film> searchFilmByRating(int rating) {
			
			return filmDao.searchFilmByRating(rating);
		}
		
		
		//Retrieving Film from Film Repository using Actor
		@Override
		public List<Film> searchFilm(Actor actor) {
			
			return filmDao.searchFilm(actor);
		}
		
		//Retrieving Film from Film Repository using Release_Date
		@Override
		public Film searchFilm(String title, Date releaseDate, int rating) {

			return filmDao.searchFilm(title, releaseDate, rating);
		}

		
		//Removing Film using Film_Id
		@Override
		public void removeFilm(int filmId) {
			
			filmDao.removeFilm(filmId);
		}

		//Removing Film using Title
		@Override
		public void removeFilm(String title) {
			
			filmDao.removeFilm(title);
		}

		//Removing Film using Rating
		@Override
		public void removeFilmByRating(int rating) {
			
			filmDao.removeFilmByRating(rating);
		}
		
		//Removing Film using Actor
		@Override
		public void removeFilm(Actor actor) {
			
			filmDao.removeFilm(actor);
		}

		//To Update Content of Film
		@Override
		public void updateFilm(Film film) {

			filmDao.updateFilm(film);	
		}

		
	}
