
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
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedDiscussionForumCreateService implements AbstractCreateService<Authenticated, DiscussionForum> {

	@Autowired
	AuthenticatedDiscussionForumRepository	repository;

	@Autowired
	AuthenticatedInvestmentRepository		iRepository;


	@Override
	public boolean authorise(final Request<DiscussionForum> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<DiscussionForum> request, final DiscussionForum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Investment investment;

		String investmentTicker = request.getModel().getCurrent().get("investment.ticker").toString();
		investment = this.iRepository.findInvestmentByTicker(investmentTicker);

		List<Authenticated> authenticated = new ArrayList<>();

		String username = request.getModel().getCurrent().get("aux").toString();
		String[] arrayUsernames = username.split(", ");
		for (String a : arrayUsernames) {
			authenticated.add(this.repository.findAuthenticatedByUsername(a));
		}

		entity.setInvestment(investment);
		entity.getAccounts().addAll(authenticated);

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<DiscussionForum> request, final DiscussionForum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "investment.ticker", "aux");

	}

	@Override
	public DiscussionForum instantiate(final Request<DiscussionForum> request) {
		assert request != null;

		DiscussionForum result;

		result = new DiscussionForum();
		Authenticated authenticated;
		Collection<Authenticated> ca = new ArrayList<>();

		Integer a = request.getPrincipal().getActiveRoleId();
		authenticated = this.repository.findOneAuthenticatedById(a);
		ca.add(authenticated);

		result.setAccounts(ca);
		result.setLeader(authenticated);
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
			if (request.getLocale().toLanguageTag().equals("en")) {
				errors.state(request, validActivitySector, "investment.ticker", "Investment ticker must exist\n Valid investment tickers are: " + validInvestmentTickers);
			} else {
				errors.state(request, validActivitySector, "investment.ticker", "El código de inversión debe existir\n Códigos de inversión válidos son: " + validInvestmentTickers);

			}
		}
		if (!errors.hasErrors("aux")) {
			boolean hasToBeTrue = true;
			String aux = request.getModel().getCurrent().get("aux").toString();
			String[] arrayAux = aux.split(", ");
			List<String> usernamesList = new ArrayList<>();
			for (String a : arrayAux) {
				usernamesList.add(a);
			}
			List<Authenticated> AllAuthenticated = this.repository.findManyAuthenticated();
			List<String> usernamesList2 = new ArrayList<>();
			for (Authenticated a : AllAuthenticated) {
				usernamesList2.add(a.getUserAccount().getUsername());
			}
			for (String username : usernamesList) {
				if (request.getPrincipal().getUsername().equals(username) || !usernamesList2.contains(username)) {
					hasToBeTrue = false;
					break;
				}
			}

			if (request.getLocale().toLanguageTag().equals("en")) {
				errors.state(request, hasToBeTrue, "aux", "You must use usernames that already exist and you cannot write your username (it will appear as leader)");
			} else {
				errors.state(request, hasToBeTrue, "aux", "Debes usar nombres de usuario que existan y no puedes escribir el tuyo (aparecerá como líder)");
			}

		}

	}

	@Override
	public void create(final Request<DiscussionForum> request, final DiscussionForum entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
