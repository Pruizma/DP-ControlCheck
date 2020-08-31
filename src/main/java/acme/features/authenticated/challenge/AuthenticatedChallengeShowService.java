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

package acme.features.authenticated.challenge;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenges.Challenge;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedChallengeShowService implements AbstractShowService<Authenticated, Challenge> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedChallengeRepository repository;

	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Challenge> request, Challenge entity, Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "deadline", "description", "rookieGoal",
				"rookieReward", "averageGoal", "averageReward", "expertGoal", "expertReward");

		
	}

	@Override
	public Challenge findOne(final Request<Challenge> request) {
		// TODO Auto-generated method stub
		assert request != null;
		
		Challenge result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findChallengeById(id);
		
		return result;
	}


	


	
	

}
