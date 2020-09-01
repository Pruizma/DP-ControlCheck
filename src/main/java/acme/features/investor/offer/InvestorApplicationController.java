
package acme.features.investor.offer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.investments.Application;
import acme.entities.roles.Investor;
import acme.features.investor.application.InvestorApplicationShowService;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/investor/offer/")
public class InvestorApplicationController extends AbstractController<Investor, Application> {

	@Autowired
	private InvestorApplicationListPendingNoOfferService	listPendingNoOfferService;

	@Autowired
	private InvestorApplicationShowService					showService;


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_PENDING_NO_OFFER, BasicCommand.LIST, this.listPendingNoOfferService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
