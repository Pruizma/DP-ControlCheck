
package acme.features.entrepreneus.application;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.investments.Application;
import acme.entities.roles.Entrepreneus;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/entrepreneus/application/")
public class EntrepreneusApplicationController extends AbstractController<Entrepreneus, Application> {

	@Autowired
	private EntrepreneusApplicationListMineService		listMineService;

	@Autowired
	private EntrepreneusApplicationListFechaService		listFechaService;

	@Autowired
	private EntrepreneusApplicationListTickerService	listTickerService;

	@Autowired
	private EntrepreneusApplicationListPendingService	listPendingService;

	@Autowired
	private EntrepreneusApplicationShowService			showService;

	@Autowired
	private EntrepreneusApplicationUpdateService		updateService;


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addCustomCommand(CustomCommand.LIST_FECHA, BasicCommand.LIST, this.listFechaService);
		super.addCustomCommand(CustomCommand.LIST_TICKER, BasicCommand.LIST, this.listTickerService);
		super.addCustomCommand(CustomCommand.LIST_PENDING, BasicCommand.LIST, this.listPendingService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}
}
