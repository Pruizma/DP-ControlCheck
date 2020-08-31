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

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.CuevasBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousCuevasBulletinListService implements AbstractListService<Anonymous, CuevasBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AnonymousCuevasBulletinRepository repository;


	@Override
	public boolean authorise(final Request<CuevasBulletin> request) {
		assert request != null;

		return true;
	}
	
	@Override
	public Collection<CuevasBulletin> findMany(final Request<CuevasBulletin> request){
		assert request != null;
		
		Collection<CuevasBulletin> result;
		
		result = this.repository.findMany();
		
		return result;
	}


	@Override
	public void unbind(final Request<CuevasBulletin> request, final CuevasBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text", "moment");

		
	}

	

}
