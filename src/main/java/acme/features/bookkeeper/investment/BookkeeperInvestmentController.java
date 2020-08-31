
package acme.features.bookkeeper.investment;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.investments.Investment;
import acme.entities.roles.Bookkeeper;

import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/bookkeeper/investment/")
public class BookkeeperInvestmentController extends AbstractController<Bookkeeper, Investment> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private BookkeeperInvestmentListMineService		listMineService;

	@Autowired
	private BookkeeperInvestmentShowService			showService;

	@Autowired
	private BookkeeperInvestmentCreateService		createService;

	@Autowired
	private BookkeeperInvestmentListNotMineService	listNotMineService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addCustomCommand(CustomCommand.LIST_NOT_MINE, BasicCommand.LIST, this.listNotMineService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
