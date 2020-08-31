
package acme.features.administrator.bookkeeperRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bookkeeperRequest.BookkeeperRequest;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorBookkeeperRequestUpdateService implements AbstractUpdateService<Administrator, BookkeeperRequest> {

	@Autowired
	AdministratorBookkeeperRequestRepository repository;

	@Autowired
	AdministratorBookkeeperRepository repositoryB;

	@Override
	public boolean authorise(final Request<BookkeeperRequest> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<BookkeeperRequest> request, final BookkeeperRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "firmName", "statement");

	}

	@Override
	public void unbind(final Request<BookkeeperRequest> request, final BookkeeperRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,  "accepted");

	}

	@Override
	public BookkeeperRequest findOne(final Request<BookkeeperRequest> request) {
		assert request != null;

		BookkeeperRequest result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findBookkeeperRequestById(id);
		return result;
	}

	@Override
	public void validate(final Request<BookkeeperRequest> request, final BookkeeperRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<BookkeeperRequest> request, final BookkeeperRequest entity) {
		assert request != null;
		assert entity != null;
		
		if(entity.getAccepted() == true){
			UserAccount u = repository.findUserAccountById( entity.getUserAccount().getId());
			String firmName = entity.getFirmName();
			String statement = entity.getStatement();
			Bookkeeper b = new Bookkeeper();
			b.setUserAccount(u);
			b.setFirmName(firmName);
			b.setStatement(statement);
			u.getRoles().add(new Bookkeeper());
			this.repositoryB.save(b);
		}

		
		this.repository.save(entity);

	}

}
