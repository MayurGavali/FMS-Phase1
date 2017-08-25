package com.flp.fms.domain;

public class Actor {
	//Private Fields
private int actor_Id;
private String firstName;
private String lastName;

//Default constructor
public Actor() {
}
//Parameterized Constructor
public Actor(int actor_Id, String firstName, String lastName) {
	super();
	this.actor_Id = actor_Id;
	this.firstName = firstName;
	this.lastName = lastName;
	
}
//Setters and Getters
public int getActor_Id() {
	return actor_Id;
}

public void setActor_id(int actor_Id) {
	this.actor_Id = actor_Id;
}

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

//Method To Auto-Generate Actor_Id 
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + actor_Id;
	result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
	result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
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
	Actor other = (Actor) obj;
	if (actor_Id != other.actor_Id)
		return false;
	if (firstName == null) {
		if (other.firstName != null)
			return false;
	} else if (!firstName.equals(other.firstName))
		return false;
	if (lastName == null) {
		if (other.lastName != null)
			return false;
	} else if (!lastName.equals(other.lastName))
		return false;
	return true;
}

//To String Method to Print
@Override
public String toString() {
	return "Actor [actor_Id=" + actor_Id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	
}

}
