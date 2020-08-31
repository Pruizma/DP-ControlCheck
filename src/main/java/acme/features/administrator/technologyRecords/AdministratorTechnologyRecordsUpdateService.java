
package acme.features.administrator.technologyRecords;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisationParameters.CustomisationParameter;
import acme.entities.technologyRecords.TechnologyRecords;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorTechnologyRecordsUpdateService implements AbstractUpdateService<Administrator, TechnologyRecords> {

	@Autowired
	AdministratorTechnologyRecordsRepository repository;


	@Override
	public boolean authorise(final Request<TechnologyRecords> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<TechnologyRecords> request, final TechnologyRecords entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<TechnologyRecords> request, final TechnologyRecords entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "activitySector", "inventor", "description", "website", "email", "openSource", "stars");

	}

	@Override
	public TechnologyRecords findOne(final Request<TechnologyRecords> request) {
		assert request != null;

		TechnologyRecords result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<TechnologyRecords> request, final TechnologyRecords entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("activitySector")) {
			boolean validActivitySector = false;
			Collection<CustomisationParameter> customisationParameters = this.repository.findCustomisationParameters();
			String activitySectors = customisationParameters.iterator().next().getActivitySectors();
			String[] arrayActivitySectors = activitySectors.split(", ");
			for (String a : arrayActivitySectors) {
				validActivitySector = entity.getActivitySector().equals(a);
				if (validActivitySector) {
					break;
				}
			}
			errors.state(request, validActivitySector, "activitySector", "Activity sector must be one of the Customisation Parameters");
		}
	}

	@Override
	public void update(final Request<TechnologyRecords> request, final TechnologyRecords entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
	}

}
