package com.flp.fms.service;

import java.util.List;
import java.util.Set;

import com.flp.fms.dao.ActorDaoImplForList;
import com.flp.fms.dao.IActorDao;
import com.flp.fms.domain.Actor;

public class ActorServiceImpl implements IActorService{
	private IActorDao actorDao= new ActorDaoImplForList();
	@Override
	public List<Actor> getActors(){
		return actorDao.getActors();
	}
	}

