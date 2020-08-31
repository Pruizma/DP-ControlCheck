
package acme.features.entrepreneus.activity;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.investments.Activity;
import acme.entities.roles.Entrepreneus;
import acme.features.entrepreneus.activity.EntrepreneusActivityCreateService;
import acme.features.entrepreneus.activity.EntrepreneusActivityDeleteService;
import acme.features.entrepreneus.activity.EntrepreneusActivityUpdateService;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/entrepreneus/activity/")
public class EntrepreneusActivityController extends AbstractController<Entrepreneus, Activity> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private EntrepreneusActivityListByInvestmentService	listActivityByInvestmentService;

	@Autowired
	private EntrepreneusActivityShowService	showService;
	@Autowired
	private EntrepreneusActivityCreateService	createService;
	
	@Autowired
	private EntrepreneusActivityUpdateService	updateService;
	
	@Autowired
	private EntrepreneusActivityDeleteService	deleteService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
				super.addCustomCommand(CustomCommand.LIST_BY_INVESTMENT, BasicCommand.LIST, this.listActivityByInvestmentService);
				super.addBasicCommand(BasicCommand.SHOW, this.showService);
				super.addBasicCommand(BasicCommand.CREATE, this.createService);
				super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
				super.addBasicCommand(BasicCommand.DELETE, this.deleteService);

	}

}
