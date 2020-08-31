
package acme.features.authenticated.activity;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.investments.Activity;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/activity/")
public class AuthenticatedActivityController extends AbstractController<Authenticated, Activity> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedActivityListByInvestmentService	listActivityByInvestmentService;

	@Autowired
	private AuthenticatedActivityShowService	showService;
	

	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
				super.addCustomCommand(CustomCommand.LIST_BY_INVESTMENT, BasicCommand.LIST, this.listActivityByInvestmentService);
				super.addBasicCommand(BasicCommand.SHOW, this.showService);



	}

}
