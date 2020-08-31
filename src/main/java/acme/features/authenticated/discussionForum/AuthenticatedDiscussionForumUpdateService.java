
package acme.features.authenticated.discussionForum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.discussionForums.DiscussionForum;
import acme.entities.investments.Investment;
import acme.features.authenticated.investment.AuthenticatedInvestmentRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuthenticatedDiscussionForumUpdateService implements AbstractUpdateService<Authenticated, DiscussionForum> {

	@Autowired
	AuthenticatedDiscussionForumRepository	repository;

	@Autowired
	AuthenticatedInvestmentRepository		iRepository;


	@Override
	public boolean authorise(final Request<DiscussionForum> request) {

		boolean result;
		int DFid;

		DiscussionForum dForum;
		DFid = request.getModel().getInteger("id");
		dForum = this.repository.findOneById(DFid);
		result = dForum.getLeader().getUserAccount().getId() == request.getPrincipal().getAccountId();

		return result;
	}

	@Override
	public void bind(final Request<DiscussionForum> request, final DiscussionForum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Investment investment;

		String investmentTicker = request.getModel().getCurrent().get("investment.ticker").toString();
		investment = this.iRepository.findInvestmentByTicker(investmentTicker);

		entity.setInvestment(investment);

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<DiscussionForum> request, final DiscussionForum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "investment.ticker", "aux", "leader.userAccount.username");

	}

	@Override
	public DiscussionForum findOne(final Request<DiscussionForum> request) {
		assert request != null;
		DiscussionForum result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<DiscussionForum> request, final DiscussionForum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("investment.ticker")) {
			boolean validActivitySector = false;
			Investment investment;
			Collection<Investment> validInvestments;

			String investmentTicker = request.getModel().getCurrent().get("investment.ticker").toString();
			investment = this.iRepository.findInvestmentByTicker(investmentTicker);
			validInvestments = this.iRepository.findMany();

			String validInvestmentTickers = "";
			for (Investment i : validInvestments) {
				validInvestmentTickers = validInvestmentTickers + " / " + i.getTicker();
			}

			if (investment != null) {
				validActivitySector = true;
			}
			errors.state(request, validActivitySector, "investment.ticker", "Investment ticker must exist\n Valid investment tickers are: " + validInvestmentTickers);
		}

		if (!errors.hasErrors("aux")) {
			boolean hasToBeTrue = true;
			String aux = entity.getAux();
			String[] arrayAux = aux.split(", ");
			List<String> usernamesList = new ArrayList<>();
			for (String a : arrayAux) {
				if (usernamesList.contains(a)) {
					hasToBeTrue = false;
				}
				usernamesList.add(a);
			}
			List<Authenticated> AllAuthenticated = this.repository.findManyAuthenticated();
			List<String> usernamesList2 = new ArrayList<>();
			for (Authenticated a : AllAuthenticated) {
				usernamesList2.add(a.getUserAccount().getUsername());
			}
			for (String username : usernamesList) {
				if (!usernamesList2.contains(username)) {
					hasToBeTrue = false;
					break;
				}
			}

			errors.state(request, hasToBeTrue, "aux", "You must use usernames that already exist and they cannot be reapeated");

		}
		if (!errors.hasErrors("leader.userAccount.username")) {
			boolean hasToBeTrue = true;
			if (!request.getPrincipal().getUsername().equals(request.getModel().getCurrent().get("leader.userAccount.username"))) {
				hasToBeTrue = false;
			}
			errors.state(request, hasToBeTrue, "leader.userAccount.username", "Leader cannot be change");

		}

	}

	@Override
	public void update(final Request<DiscussionForum> request, final DiscussionForum entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);

	}

}
