
package acme.features.bookkeeper.investment;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investments.Investment;
import acme.entities.roles.Bookkeeper;
import acme.entities.roles.Entrepreneus;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.helpers.MessageHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class BookkeeperInvestmentCreateService implements AbstractCreateService<Bookkeeper, Investment> {

	@Autowired
	private BookkeeperInvestmentRepository repository;


	@Override
	public boolean authorise(final Request<Investment> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Investment> request, final Investment entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "moment", "round", "title", "description", "money", "url");
	}
	@Override
	public void bind(final Request<Investment> request, final Investment entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public Investment instantiate(final Request<Investment> request) {
		assert request != null;

		Investment result = new Investment();

		int id = request.getPrincipal().getAccountId();
		Entrepreneus entrepreneus = this.repository.findEntrepreneusByUserAccountId(id);

		result = new Investment();

		result.setEntrepreneus(entrepreneus);

		return result;
	}

	@Override
	public void validate(final Request<Investment> request, final Investment entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("money")) {
			boolean moneyCurrencyMax = entity.getMoney().getCurrency().equals("EUR") || entity.getMoney().getCurrency().equals("EUROS") || entity.getMoney().getCurrency().equals("€");
			errors.state(request, moneyCurrencyMax, "money", "a.o.error.money");
		}

		// Mínimo debe ser mayor que cero
		if (!errors.hasErrors("money")) {
			boolean minBiggerThanZero = entity.getMoney().getAmount() >= 0;
			errors.state(request, minBiggerThanZero, "money", "a.o.error.plus");
		}

		//Reference must be unique----------------------------
		if (!errors.hasErrors("reference")) {
			boolean referenceIsNotUnique = !this.uniqueTicker(entity.getTicker());
			String message = MessageHelper.getMessage("employer.job.error.ticker");
			if (referenceIsNotUnique) {
				errors.add("reference", message);
			}
		}

	}

	@Override
	public void create(final Request<Investment> request, final Investment entity) {
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		this.repository.save(entity);
	}

	public boolean uniqueTicker(final String reference) {

		Investment j = this.repository.existsReference(reference);
		Boolean isUnique = j == null;
		return isUnique;
	}

}
