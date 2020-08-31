
package acme.features.administrator.overture;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.overtures.Overture;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorOvertureUpdateService implements AbstractUpdateService<Administrator, Overture> {

	// Internal state ---------------------------------------------------------
	@Autowired
	AdministratorOvertureRepository repository;


	@Override
	public boolean authorise(final Request<Overture> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Overture> request, final Overture entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Overture> request, final Overture entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "minMoney", "maxMoney", "email");

	}

	@Override
	public Overture findOne(final Request<Overture> request) {
		assert request != null;
		Overture result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOvertureById(id);
		return result;
	}

	@Override
	public void validate(final Request<Overture> request, final Overture entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//Deadline cannot be in the past
		if (!errors.hasErrors("deadline")) {
			Date now = new Date(System.currentTimeMillis() - 1);
			boolean deadlinePassed = entity.getDeadline().after(now);
			if (!deadlinePassed) {
				errors.state(request, deadlinePassed, "deadline", "a.o.error.deadline.passed");
			}
		}

		//minMoney and maxMoney must be in euros
		if (!errors.hasErrors("minMoney")) {
			boolean moneyCurrencyMin = entity.getMinMoney().getCurrency().equals("EUR") || entity.getMinMoney().getCurrency().equals("EUROS") || entity.getMinMoney().getCurrency().equals("€");
			errors.state(request, moneyCurrencyMin, "minMoney", "a.o.error.min");
		}

		if (!errors.hasErrors("maxMoney")) {
			boolean moneyCurrencyMax = entity.getMaxMoney().getCurrency().equals("EUR") || entity.getMaxMoney().getCurrency().equals("EUROS") || entity.getMaxMoney().getCurrency().equals("€");
			errors.state(request, moneyCurrencyMax, "maxMoney", "a.o.error.max");
		}

		// Mínimo debe ser mayor que cero
		if (!errors.hasErrors("minMoney")) {
			boolean minBiggerThanZero = entity.getMinMoney().getAmount() >= 0;
			errors.state(request, minBiggerThanZero, "minMoney", "a.o.error.minNegative");
		}

		// Maximo debe ser mas grande que el minimo
		if (!errors.hasErrors("maxMoney")) {
			boolean maxBiggerThanMin = entity.getMaxMoney().getAmount() > entity.getMinMoney().getAmount();
			errors.state(request, maxBiggerThanMin, "maxMoney", "a.o.error.bigger");
		}
	}

	@Override
	public void update(final Request<Overture> request, final Overture entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}
}
