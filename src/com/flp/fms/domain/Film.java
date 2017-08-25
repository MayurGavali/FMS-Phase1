package com.flp.fms.domain;

import java.util.Date;
import java.util.List;
import java.util.Set;


public class Film {
	
	
	//Private Fields
private int film_Id;//system generated id
private String film_Description;
private String title;
private Date release_Date;
private List<Language> language;
private Language Original_Language;
private Date rental_Duration;
private int lenght;
private double replacement_Cost;
private int rating;
private String special_features;
private Set<Actor>actor;
private Category category;


//Default Constructor
public Film() {
}


//Parameterized Constructor

//Setters and Getters
public int getFilm_Id() {
	return film_Id;
}
public Film(int film_Id, String film_Description, String title, Date release_Date, List<Language> language,
		Language original_Lang_Id, Date rental_Duration, int lenght, double replacement_Cost, int rating,
		String special_features, Set<Actor> actor, Category category) {
	super();
	this.film_Id = film_Id;
	this.film_Description = film_Description;
	this.title = title;
	this.release_Date = release_Date;
	this.language = language;
	Original_Language = Original_Language;
	this.rental_Duration = rental_Duration;
	this.lenght = lenght;
	this.replacement_Cost = replacement_Cost;
	this.rating = rating;
	this.special_features = special_features;
	this.actor = actor;
	this.category = category;
}
//Setters and Getters


public String getFilm_Description() {
	return film_Description;
}


public void setFilm_Description(String film_Description) {
	this.film_Description = film_Description;
}


public String getTitle() {
	return title;
}


public void setTitle(String title) {
	this.title = title;
}


public Date getRelease_Date() {
	return release_Date;
}


public void setRelease_Date(Date release_Date) {
	this.release_Date = release_Date;
}


public List<Language> getLanguage() {
	return language;
}


public void setLanguage(List<Language> language) {
	this.language = language;
}


public Language getOriginal_Language() {
	return Original_Language;
}


public void setOriginal_Language(Language original_Language) {
	Original_Language = original_Language;
}


public Date getRental_Duration() {
	return rental_Duration;
}


public void setRental_Duration(Date rental_Duration) {
	this.rental_Duration = rental_Duration;
}


public int getLenght() {
	return lenght;
}


public void setLenght(int lenght) {
	this.lenght = lenght;
}


public double getReplacement_Cost() {
	return replacement_Cost;
}


public void setReplacement_Cost(double replacement_Cost) {
	this.replacement_Cost = replacement_Cost;
}


public int getRating() {
	return rating;
}


public void setRating(int rating) {
	this.rating = rating;
}


public String getSpecial_features() {
	return special_features;
}


public void setSpecial_features(String special_features) {
	this.special_features = special_features;
}


public Set<Actor> getActor() {
	return actor;
}


public void setActor(Set<Actor> actor) {
	this.actor = actor;
}


public Category getCategory() {
	return category;
}


public void setCategory(Category category) {
	this.category = category;
}


public void setFilm_Id(int film_Id) {
	this.film_Id = film_Id;
}

//To String Method
@Override
public String toString() {
	return "Film [fim_id=" + film_Id + ", film_Description=" + film_Description + ", title=" + title + ", release_Date="
			+ release_Date + ", language=" + language + ", Original_Lang_Id=" + Original_Language + ", rental_Duration="
			+ rental_Duration + ", lenght=" + lenght + ", replacement_Cost=" + replacement_Cost + ", rating=" + rating
			+ ", special_features=" + special_features + ", actor=" + actor + ", category=" + category + "]";
}



}
