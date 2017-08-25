package com.flp.fms.domain;

public class Language {
	//Private Fields
private int language_Id;
private String  language_Name;

//Default constructor
public Language() {
}
//Parameterized constructor
public Language(int language_Id, String language_Name) {
	super();
	this.language_Id = language_Id;
	this.language_Name = language_Name;
	
}
//Setters and Getters
public int getLanguage_Id() {
	return language_Id;
}
public void setLanguage_Id(int language_Id) {
	this.language_Id = language_Id;
}
public String getLanguage_Name() {
	return language_Name;
}
public void setLanguage_Name(String language_Name) {
	this.language_Name = language_Name;
}

//To Auto-Generate Language Id 
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + language_Id;
	result = prime * result + ((language_Name == null) ? 0 : language_Name.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Language other = (Language) obj;
	if (language_Id != other.language_Id)
		return false;
	if (language_Name == null) {
		if (other.language_Name != null)
			return false;
	} else if (!language_Name.equals(other.language_Name))
		return false;
	return true;
}

//To String Method
@Override
public String toString() {
	return "Language [language_Id=" + language_Id + ", language_Name=" + language_Name + "]";
}

}
