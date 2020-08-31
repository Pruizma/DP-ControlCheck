/*
 * AnonymousUserAccountCreateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.anonymous.cuevasBulletin;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.CuevasBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousCuevasBulletinCreateService implements AbstractCreateService<Anonymous, CuevasBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AnonymousCuevasBulletinRepository repository;


	@Override
	public boolean authorise(final Request<CuevasBulletin> request) {
		assert request != null;

		return true;
	}


	@Override
	public void bind(Request<CuevasBulletin> request, CuevasBulletin entity, Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors);
		
	}


	@Override
	public void unbind(Request<CuevasBulletin> request, CuevasBulletin entity, Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "author", "text");
		
		
	}


	@Override
	public CuevasBulletin instantiate(Request<CuevasBulletin> request) {
		// TODO Auto-generated method stub
		assert request != null;
		
		CuevasBulletin result;
		Date moment;
		
		moment = new Date(System.currentTimeMillis() - 1);
		
		result = new CuevasBulletin();
		result.setAuthor("David Cuevas");
		result.setText("Texto prueba");
		result.setMoment(moment);
		
		return result;
	}


	@Override
	public void validate(Request<CuevasBulletin> request, CuevasBulletin entity, Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}


	@Override
	public void create(Request<CuevasBulletin> request, CuevasBulletin entity) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		
		Date moment;
		
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
		
	}
	
	

}
