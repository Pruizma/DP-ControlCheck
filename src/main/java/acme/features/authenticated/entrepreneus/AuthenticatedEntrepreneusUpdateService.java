package acme.features.authenticated.entrepreneus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Entrepreneus;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuthenticatedEntrepreneusUpdateService implements AbstractUpdateService<Authenticated, Entrepreneus> {

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
	public Entrepreneus findOne(final Request<Entrepreneus> request) {
		assert request != null;

		Entrepreneus result;
		Principal principal;
		int userAccountId;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();

		result = this.repository.findOneEntrepreneusByUserAccountId(userAccountId);

		return result;
	}

	@Override
	public void validate(final Request<Entrepreneus> request, final Entrepreneus entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<Entrepreneus> request, final Entrepreneus entity) {
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