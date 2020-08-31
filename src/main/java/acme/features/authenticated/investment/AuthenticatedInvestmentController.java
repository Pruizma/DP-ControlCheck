
package acme.features.authenticated.investment;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.investments.Investment;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/investment/")
public class AuthenticatedInvestmentController extends AbstractController<Authenticated, Investment> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedInvestmentListService	listService;

	@Autowired
	private AuthenticatedInvestmentShowService	showService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
	}

}
