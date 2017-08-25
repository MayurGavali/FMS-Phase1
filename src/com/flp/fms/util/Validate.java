package com.flp.fms.util;

import java.util.Iterator;
import java.util.List;

import com.flp.fms.domain.Language;

public class Validate {

	//To Validate Title
	public static boolean isValidTitle(String title){
		return title.matches("[A-Za-z0-9.,! ]+");
	}
	
	//To Validate Release Date
	public static boolean isValidDate(String releasedate){
		return releasedate.matches("[0123][0-9]-(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec)-[12][890]\\d{2}");
	}
	
	//To Validate Rating
	public static boolean  isValidRating(int rate){
		if(rate>0 && rate<=5)
			return true;
		else 
			return false;
	}
	
	//To Validate Length
	public static boolean  isValidLength(int len){
		if(len>0 && len<=1000)
			return true;
		else 
			return false;
	}
	
	//To Validate Replacement cost
	public static boolean  isValidCost(double cost){
		if(cost>0)
			return true;
		else 
			return false;
	}
	
	
	//Check Duplicate Language in the List
		public static boolean checkDuplicateLanguage(List<Language> languages,Language language){
			boolean flag=false;
			
			Iterator<Language> it= languages.iterator();
			if(languages.isEmpty())
			{
				flag=false;
			}else{
				while(it.hasNext()){
					Language language2=it.next();
					if(language.equals(language2))
					{
						flag=true;
						break;
					}
				}
			}
			return flag;
		}
		
}
