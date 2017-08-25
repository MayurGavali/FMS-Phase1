package com.flp.fms.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.flp.fms.domain.Actor;

public class ActorDaoImplForList implements IActorDao {
public List<Actor> getActors(){
	
	
	//List of Actors
	List<Actor> actors=new ArrayList<>();
	actors.add(new Actor(101,"Tom","Jerry"));
	actors.add(new Actor(102,"Sharuk","Khan"));
	actors.add(new Actor(103,"Kamal","Kashan"));
	actors.add(new Actor(104,"Jack","Thomson"));
	actors.add(new Actor(105,"Annie","Jessie"));
	return actors;
}
}
