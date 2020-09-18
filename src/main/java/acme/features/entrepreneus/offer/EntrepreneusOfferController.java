
package acme.features.entrepreneus.offer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.offer.Offer;
import acme.entities.roles.Entrepreneus;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/entrepreneus/offer/")
public class EntrepreneusOfferController extends AbstractController<Entrepreneus, Offer> {

	@Autowired
	private EntrepreneusOfferListMineService	listMineService;

	@Autowired
	private EntrepreneusOfferShowService		showService;

	@Autowired
	private EntrepreneusOfferUpdateService		updateService;


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}
}
