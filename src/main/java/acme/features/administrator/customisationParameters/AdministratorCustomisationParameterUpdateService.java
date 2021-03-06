
package acme.features.administrator.customisationParameters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisationParameters.CustomisationParameter;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorCustomisationParameterUpdateService implements AbstractUpdateService<Administrator, CustomisationParameter> {

	@Autowired
	AdministratorCustomisationParameterRepository repository;


	@Override
	public boolean authorise(final Request<CustomisationParameter> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<CustomisationParameter> request, final CustomisationParameter entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "spamWords", "spamThreshold", "activitySectors");
	}

	@Override
	public void bind(final Request<CustomisationParameter> request, final CustomisationParameter entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<CustomisationParameter> request, final CustomisationParameter entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<CustomisationParameter> request, final CustomisationParameter entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	@Override
	public CustomisationParameter findOne(Request<CustomisationParameter> request) {
		assert request!=null;
		
		CustomisationParameter result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		
		return result;
	}

}
