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

package acme.features.anonymous.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Banner;
import acme.entities.creditCards.CreditCard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractShowService;

@Service
public class AnonymousBannerShowService implements AbstractShowService<Anonymous, Banner> {

	@Autowired
	AnonymousBannerRepository anonymousBannerRepository;


	@Override
	public boolean authorise(final Request<Banner> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Banner> request, final Banner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int id = request.getModel().getInteger("id");
		CreditCard creditCard = this.anonymousBannerRepository.findOneByBannerId(id);
		model.setAttribute("number", creditCard.getCreditCardNumber());
		model.setAttribute("holder", creditCard.getHolder());
		model.setAttribute("brand", creditCard.getBrand());
		model.setAttribute("cvv", creditCard.getCvv());
		model.setAttribute("expirationDate", creditCard.getExpirationDate());
		request.unbind(entity, model, "picture", "name");

	}

	@Override
	public Banner findOne(final Request<Banner> request) {
		assert request != null;

		Banner result;
		int id = request.getModel().getInteger("id");
		result = this.anonymousBannerRepository.findOneById(id);
		return result;
	}

}
