
package acme.features.administrator.toolRecords;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisationParameters.CustomisationParameter;
import acme.entities.toolRecords.ToolRecord;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorToolRecordUpdateService implements AbstractUpdateService<Administrator, ToolRecord> {

	@Autowired
	AdministratorToolRecordRepository repository;


	@Override
	public boolean authorise(final Request<ToolRecord> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<ToolRecord> request, final ToolRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<ToolRecord> request, final ToolRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "title", "activitySector", "inventorName", "description", "webSite", "email", "openSource", "stars");
	}

	@Override
	public ToolRecord findOne(final Request<ToolRecord> request) {
		assert request != null;
		ToolRecord result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void validate(final Request<ToolRecord> request, final ToolRecord entity, final Errors errors) {
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
	public void update(final Request<ToolRecord> request, final ToolRecord entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
	}

}
