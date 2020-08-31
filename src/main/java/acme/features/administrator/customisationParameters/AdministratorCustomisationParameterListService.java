
package acme.features.administrator.customisationParameters;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisationParameters.CustomisationParameter;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorCustomisationParameterListService implements AbstractListService<Administrator, CustomisationParameter> {

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
	public Collection<CustomisationParameter> findMany(final Request<CustomisationParameter> request) {
		assert request != null;
		Collection<CustomisationParameter> result;
		result = this.repository.findManyAll();
		return result;
	}
}
