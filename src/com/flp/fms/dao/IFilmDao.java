package com.flp.fms.dao;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.flp.fms.domain.Actor;
import com.flp.fms.domain.Category;
import com.flp.fms.domain.Film;
import com.flp.fms.domain.Language;

public interface IFilmDao {

	public List<Language>getLanguage();
	public List<Category> getCategory();
	public void addFilm(Film film);
	public void updateFilm(Film film);
	public Map<Integer, Film> getAllFilms();
	public Film searchFilm(int filmId);
	public Film searchFilm(String title);
	public List<Film> searchFilm(Actor actor);
	public List<Film> searchFilm(Language language);
	public List<Film> searchFilmByRating(int rating);
	public Film searchFilm(String title, Date releaseDate, int rating);
	
	public void removeFilm(int filmId);
	public void removeFilm(String title);
	public void removeFilm(Actor actor);
	public void removeFilmByRating(int rating);
}
