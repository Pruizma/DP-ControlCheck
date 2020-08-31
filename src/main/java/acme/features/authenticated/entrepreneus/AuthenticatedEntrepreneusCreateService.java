package acme.features.authenticated.entrepreneus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisationParameters.CustomisationParameter;
import acme.entities.roles.Entrepreneus;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.entities.UserAccount;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedEntrepreneusCreateService implements AbstractCreateService<Authenticated, Entrepreneus> {

	@Autowired
	private AuthenticatedEntrepreneusRepository repository;


	@Override
	public boolean authorise(final Request<Entrepreneus> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Entrepreneus> request, final Entrepreneus entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Entrepreneus> request, final Entrepreneus entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "startup", "activitySector", "qualificationRecord", "skillRecord");

	}

	@Override
	public Entrepreneus instantiate(final Request<Entrepreneus> request) {
		assert request != null;

		Entrepreneus result;
		Principal principal;
		int userAccountId;
		UserAccount userAccount;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		userAccount = this.repository.findOneUserAccountById(userAccountId);

		result = new Entrepreneus();
		result.setUserAccount(userAccount);

		return result;
	}

	@Override
	public void validate(final Request<Entrepreneus> request, final Entrepreneus entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("activitySector")) {
			List<String> customParam = new ArrayList<String>();
			boolean validActivitySector = false;
			Collection<CustomisationParameter> customisationParameters = this.repository.findCustomisationParameters();
			String activitySectors = customisationParameters.iterator().next().getActivitySectors();
			String[] arrayActivitySectors = activitySectors.split(", ");
			for (String a : arrayActivitySectors) {
				validActivitySector = entity.getActivitySector().equals(a);
				customParam.add(a);
				if (validActivitySector) {
					break;
				}
				
			}
			String msgError = "Activity sector must be one of the Customisation Parameters: " + customParam;
			errors.state(request, validActivitySector, "activitySector", msgError );
		}
	}

	@Override
	public void create(final Request<Entrepreneus> request, final Entrepreneus entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

	@Override
	public void onSuccess(final Request<Entrepreneus> request, final Response<Entrepreneus> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}