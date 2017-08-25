package com.flp.fms.domain;

public class Category {
	
//Private fields
private int category_Id;
private String category_Name;


//Default Constructor
public Category() {
}




/**
 * @param category_Id
 * @param category_Name
 */

//parametrised constructor
public Category(int category_Id, String category_Name) {
	super();
	this.category_Id = category_Id;
	this.category_Name = category_Name;
}



//generating the gettrers and setters
public int getCategory_Id() {
	return category_Id;
}




public void setCategory_Id(int category_Id) {
	this.category_Id = category_Id;
}




public String getCategory_Name() {
	return category_Name;
}




public void setCategory_Name(String category_Name) {
	this.category_Name = category_Name;
}




//To String Method
@Override
public String toString() {
	return "Category [category_Id=" + category_Id + ", Category_Name=" + category_Name +  "]";
}
}
