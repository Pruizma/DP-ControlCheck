
package acme.features.entrepreneus.investment;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.investments.Investment;
import acme.entities.roles.Entrepreneus;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/entrepreneus/investment/")
public class EntrepreneusInvestmentController extends AbstractController<Entrepreneus, Investment> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private EntrepreneusInvestmentListMineService	listMineService;

	@Autowired
	private EntrepreneusInvestmentShowService		showService;

	@Autowired
	private EntrepreneusInvestmentUpdateService		updateService;

	@Autowired
	private EntrepreneusInvestmentDeleteService		deleteService;

	@Autowired
	private EntrepreneusInvestmentCreateService		createService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
