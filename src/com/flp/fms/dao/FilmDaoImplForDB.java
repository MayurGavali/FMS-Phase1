package com.flp.fms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

public class FilmDaoImplForDB implements  IFilmDao {

	
	public Connection getConnection(){
	Connection connection=null;
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/database123","root","root");
		
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	return connection;
}
	
	@Override
	public List<Language> getLanguage() {
	
		List<Language> languages=new ArrayList<>();
		
        Connection con=getConnection();
		
		String sql="select language_Id,language_Name from languagee";
		
		try {
			Statement stmt=con.createStatement();
			
			ResultSet rs=stmt.executeQuery(sql);
			
			while(rs.next()){
				
				languages.add(new Language(rs.getInt(1), rs.getString(2)));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return languages;
	}
	

	@Override
	public List<Category> getCategory() {
		
		List<Category> categories=new ArrayList<>();
		
		   Connection con=getConnection();
			
			String sql="select category_id,category_Name from categoryy";
			
			try {
				Statement stmt=con.createStatement();
				
				ResultSet rs=stmt.executeQuery(sql);
				
				while(rs.next()){
					
					categories.add(new Category(rs.getInt(1), rs.getString(2)));
					
				}
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
		return categories;
	
		
		
		}

	//CURD Operation 
	
	@Override
	public void addFilm(Film film) {
		
		 Connection con=getConnection();
			
			String sql="insert into film(title,description,releaseYear,originalLanguage,rentalDuration,length,replacementCost,ratings,specialFeatures,category)"
												+ "	 values(?,?,?,?,?,?,?,?,?,?)";
			
			try {
				PreparedStatement pst=con.prepareStatement(sql);
				pst.setString(1, film.getTitle());
				pst.setString(2, film.getFilm_Description());
				pst.setDate(3, new java.sql.Date(film.getRelease_Date().getTime()));
				pst.setInt(4, film.getOriginal_Language().getLanguage_Id());
				pst.setDate(5, new java.sql.Date(film.getRental_Duration().getTime()));
				pst.setInt(6,film.getLenght());
				pst.setDouble(7, film.getReplacement_Cost());
				pst.setInt(8, film.getRating());
				pst.setString(9, film.getSpecial_features());
				
				pst.setInt(10, film.getCategory().getCategory_Id());
				
				int count=pst.executeUpdate();
				
				storeLanguages(film);
				storeActors(film);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	
	public int retrieveFilmId(){
		Connection con=getConnection();
		int filmid=0;
		
		String sql="select filmid from film order by filmid desc limit 1";
		
		try {
			Statement stmt=con.createStatement();
			
			ResultSet rs=stmt.executeQuery(sql);
			
			while(rs.next()){
				
				filmid=rs.getInt(1);
				
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return filmid;
	}
	
	public void storeLanguages(Film film){
		Connection con=getConnection();
		
		String sql="insert into film_language(film_id,language_id)"
				                          +"values(?,?)";
		try {
			PreparedStatement pst=con.prepareStatement(sql);
			List<Language> langs=film.getLanguage();
			Iterator<Language> itr= langs.iterator();
			
			while(itr.hasNext()){
			pst.setInt(1, retrieveFilmId());
			int langid=itr.next().getLanguage_Id();
			pst.setInt(2, langid);
			int count=pst.executeUpdate();
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void storeActors(Film film){
		Connection con=getConnection();
		
		String sql="insert into film_actors(film_id,actor_id)"
				                          +"values(?,?)";
		try {
			PreparedStatement pst=con.prepareStatement(sql);
			Set<Actor> actors=film.getActor();
			Iterator<Actor> itr= actors.iterator();
			
			while(itr.hasNext()){
			pst.setInt(1, retrieveFilmId());
			int actid=itr.next().getActor_Id();
			pst.setInt(2, actid);
			int count=pst.executeUpdate();
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public Map<Integer, Film> getAllFilms() {
		Connection con=getConnection();	
		Map<Integer, Film> films=new HashMap<Integer, Film>();
		 String sql="select * from film";
		 
			try {
				PreparedStatement pst=(PreparedStatement) con.prepareStatement(sql);
				ResultSet rs=pst.executeQuery();
				while(rs.next())
				{
					Film film=new Film();
					film.setFilm_Id(rs.getInt(1));
					film.setFilm_Description(rs.getString(3));
					film.setTitle(rs.getString(2));
					film.setRelease_Date(rs.getDate(4));
					
					String subsql;
					subsql="select language_name from languagee where language_Id="+rs.getInt(5);
					PreparedStatement pst1=con.prepareStatement(subsql);
					ResultSet rs3=pst1.executeQuery();
					Language lang=new Language();
					if(rs3.next())
					{
						lang.setLanguage_Id(rs.getInt(5));
						lang.setLanguage_Name(rs3.getString(1));
					}
					film.setOriginal_Language(lang);
					film.setRental_Duration(rs.getDate(6));
					film.setLenght(rs.getInt(7));
					film.setReplacement_Cost(rs.getInt(8));
					film.setRating(rs.getInt(9));
					film.setSpecial_features(rs.getString(10));
					
					subsql="select category_name from categoryy where category_id="+rs.getInt(11);
					PreparedStatement pst3=con.prepareStatement(subsql);
				    rs3=pst3.executeQuery();
					if(rs3.next())
					{
						Category cat=new Category();
						cat.setCategory_Id(rs.getInt(11));
						cat.setCategory_Name(rs3.getString(1));
						film.setCategory(cat);
					}
					
					subsql="select language_id from film_language where film_id="+rs.getInt(1);
					
					pst3=con.prepareStatement(subsql);
				    rs3=pst3.executeQuery();
				    List<Language> languages=new ArrayList<>();
					while(rs3.next())
					{
												
						String subsql1="select language_name from languagee where language_Id="+rs3.getInt(1);
						PreparedStatement pst2=con.prepareStatement(subsql1);
						ResultSet rs1=pst2.executeQuery();
						while(rs1.next()){
							Language langs=new Language();
							langs.setLanguage_Id(rs3.getInt(1));
							langs.setLanguage_Name(rs1.getString(1));
							languages.add(langs);
							
						}
					}
					film.setLanguage(languages);
					subsql="select actor_id from film_actors where film_id="+rs.getInt(1);
				
					pst3=con.prepareStatement(subsql);
				    rs3=pst3.executeQuery();
				    Set<Actor> actors=new HashSet<>();
					while(rs3.next())
					{
						String subsql1="select firstName,lastName from actorr where actor_Id="+rs3.getInt(1);
						PreparedStatement pst2=con.prepareStatement(subsql1);
						ResultSet rs1=pst2.executeQuery();
						while(rs1.next()){
							Actor actr=new Actor();
							actr.setFirstName(rs1.getString(1));
							
							actr.setLastName(rs1.getString(2));
							actr.setActor_id(rs3.getInt(1));
							actors.add(actr);
							
						}
					}
					film.setActor(actors);
					film.setLanguage(languages);
					
					films.put(film.getFilm_Id(), film);
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			return films;
	}
		




	@Override
	public void updateFilm(Film film) {
		 Connection con=getConnection();
			
		
		 String sql="Update film Set title=?,description=?,releaseYear=?,originallanguage=?,rentalDuration=?,length=?,replacementCost=?,ratings=?,specialFeatures=?,category=? where filmid="+film.getFilm_Id();
		 
		 try {
			 	PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1,film.getTitle() );
				pst.setString(2, film.getFilm_Description());	
				pst.setDate(3,new java.sql.Date(film.getRelease_Date().getTime()));
				pst.setInt(4,film.getOriginal_Language().getLanguage_Id());
				pst.setDate(5,new java.sql.Date(film.getRental_Duration().getTime()));
				pst.setInt(6,film.getLenght());
				pst.setDouble(7,film.getReplacement_Cost());
				pst.setInt(8,film.getRating());
				pst.setString(9,film.getSpecial_features());
				Category cat=film.getCategory();
				pst.setInt(10,cat.getCategory_Id());
				int count=pst.executeUpdate();
				//if insertion to film table is success execute
				if(count>0){
					
					//insertion to third party tables
					int filmId=film.getFilm_Id();
					
							
					sql="delete from film_actor where filmid="+filmId;
					PreparedStatement stmt = con.prepareStatement(sql);
					int count1= stmt.executeUpdate();
					
					sql="insert into film_actor(filmid,Actor_id) values(?,?)";
					pst = con.prepareStatement(sql);
					
					//getting all the actors in the film
					Set<Actor> actors = film.getActor();			
					for(Actor act: actors){
						pst.setInt(1, filmId );
						pst.setInt(2, act.getActor_Id());
						
						count=pst.executeUpdate();
					}
					
					sql="delete from film_language where filmid="+filmId;
					 stmt = con.prepareStatement(sql);
					 count1= stmt.executeUpdate();
									
					sql="insert into film_language(filmid,language_id) values(?,?)";
					pst = con.prepareStatement(sql);
					
					//getting all the other languages
					List<Language> languages = film.getLanguage();				
					for(Language lang: languages){
						pst.setInt(1, filmId );
						pst.setInt(2, lang.getLanguage_Id());
						
						count=pst.executeUpdate();
					}

				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
	}


	@Override
	public void removeFilm(int filmid) {
		Connection con=getConnection();
		
		String sql= "DELETE film,film_actor,film_language FROM film LEFT JOIN film_actor ON film.filmid = film_actor.filmid LEFT JOIN film_language ON film_language.filmid = film.filmid WHERE film.filmid  =?" ;

		try {
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, filmid);
			int count=pst.executeUpdate();
								
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}


	@Override
	public void removeFilm(Actor actor) {
		Connection con=getConnection();
		Map<Integer, Film> films=getAllFilms();
		
		for(Map.Entry<Integer, Film> f : films.entrySet()){
			Film film=f.getValue();
			Set<Actor> act=film.getActor();
			for(Actor act1: act){
			if(act1.getActor_Id()==actor.getActor_Id()){
		String sql= "DELETE film,film_actor,film_language FROM film LEFT JOIN film_actor ON film.filmid = film_actor.filmid LEFT JOIN film_language ON film_language.filmid = film.filmid WHERE film.filmid  =?" ;

		try {
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, film.getFilm_Id());
			int count=pst.executeUpdate();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	  }
	 }		
	}


	@Override
	public void removeFilmByRating(int rating) {
		Connection con=getConnection();
		Map<Integer, Film> films=getAllFilms();
		
		for(Map.Entry<Integer, Film> f : films.entrySet()){
			Film film=f.getValue();
			int rat=film.getRating();
			
			if(rat==rating){
		String sql= "DELETE film,film_actor,film_language FROM film LEFT JOIN film_actor ON film.filmid = film_actor.filmid LEFT JOIN film_language ON film_language.filmid = film.filmid WHERE film.filmid  =?" ;

		try {
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, film.getFilm_Id());
			int count=pst.executeUpdate();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	  
	 }		
	}


	@Override
	public void removeFilm(String title) {
		Connection con=getConnection();
		Map<Integer, Film> films=getAllFilms();
		
		for(Map.Entry<Integer, Film> f : films.entrySet()){
			Film film=f.getValue();
			String str=film.getTitle();
			
			if(str.equals(title)){
		String sql= "DELETE film,film_actor,film_language FROM film LEFT JOIN film_actor ON film.filmid = film_actor.filmid LEFT JOIN film_language ON film_language.filmid = film.filmid WHERE film.filmid  =?" ;

		try {
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, film.getFilm_Id());
			int count=pst.executeUpdate();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	  
	 }		
	}


	@Override
	public List<Film> searchFilm(Actor actor) {
		
		Map<Integer, Film> films=getAllFilms();
		List<Film> result=new ArrayList<>();
		
		for(Map.Entry<Integer, Film> f : films.entrySet()){
			Film film=f.getValue();
			Set<Actor> act=film.getActor();
			for(Actor act1: act){
			if(act1.getFirstName().equals(actor.getFirstName())){
				result.add(film);
	   }
	  }
	 }		
		return result;
	}


	@Override
	public List<Film> searchFilm(Language language) {
		Map<Integer, Film> films=getAllFilms();
		List<Film> result=new ArrayList<>();
		
		for(Map.Entry<Integer, Film> f : films.entrySet()){
			Film film=f.getValue();
			Language langs=film.getOriginal_Language();
			
			if(langs.getLanguage_Name().equals(language.getLanguage_Name())){
				result.add(film);
	   }
	  
	 }		
		return result;
	}


	@Override
	public List<Film> searchFilmByRating(int rating) {
		Map<Integer, Film> films=getAllFilms();
		List<Film> result=new ArrayList<>();
		
		for(Map.Entry<Integer, Film> f : films.entrySet()){
			Film film=f.getValue();
						
			if(film.getRating()==rating){
				result.add(film);
	   
	  }
	 }		
		return result;
	}


	@Override
	public Film searchFilm(String title) {
		Map<Integer, Film> films=getAllFilms();
		Film result=new Film();
		
		for(Map.Entry<Integer, Film> f : films.entrySet()){
			Film film=f.getValue();
						
			if(film.getTitle().equals(title)){
				result=film;
	   
			}
	 }		
		return result;
	}


	@Override
	public Film searchFilm(int filmId) {
		Map<Integer, Film> films=getAllFilms();
		Film result=new Film();
		
		for(Map.Entry<Integer, Film> f : films.entrySet()){
			Film film=f.getValue();
						
			if(film.getFilm_Id()==filmId){
				result=film;
	   		}
		}		
		
		return result;
	}

	
	
	
	@Override
	public Film searchFilm(String title, Date releaseDate, int rating) {
		// TODO Auto-generated method stub
		return null;
	}




}