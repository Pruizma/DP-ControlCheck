
package acme.features.investor.investment;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.investments.Investment;
import acme.entities.roles.Investor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/investor/investment/")
public class InvestorInvestmentController extends AbstractController<Investor, Investment> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private InvestorInvestmentListService	listService;

	@Autowired
	private InvestorInvestmentShowService	showService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
	}

}
