
package acme.features.authenticated.bookkeeperRequest;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bookkeeperRequest.BookkeeperRequest;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedBookkeeperRequestCreateService implements AbstractCreateService<Authenticated, BookkeeperRequest> {

	@Autowired
	AuthenticatedBookkeeperRequestRepository repository;


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

		request.bind(entity, errors, "accepted", "userAccount");

	}

	@Override
	public void unbind(final Request<BookkeeperRequest> request, final BookkeeperRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firmName", "statement");

	}

	@Override
	public BookkeeperRequest instantiate(final Request<BookkeeperRequest> request) {
		BookkeeperRequest result;
		result = new BookkeeperRequest();
//		UserAccount u = new UserAccount();
		result.setAccepted(false);
		int userId = request.getPrincipal().getAccountId();
		UserAccount user = this.repository.getUserAccountById(userId);
		result.setUserAccount(user);
		return result;
	}

	@Override
	public void validate(final Request<BookkeeperRequest> request, final BookkeeperRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
		 if (!errors.hasErrors("userAccount")) { 
			 Boolean s = true;
			 BookkeeperRequest b = repository.getBookkeeperRequestByAccountId(request.getPrincipal().getAccountId());
			 if(b != null){
				 s = false;
				 errors.state(request, s, "userAccount", "a.bookkeeper-request.exists");
			 }	
		 }
		 
		 

	}

	@Override
	public void create(final Request<BookkeeperRequest> request, final BookkeeperRequest entity) {
		assert request != null;
		assert entity != null;

		entity.setAccepted(false);
		int userId = request.getPrincipal().getAccountId();
		UserAccount user = this.repository.getUserAccountById(userId);
		entity.setUserAccount(user);

		this.repository.save(entity);

	}

}
