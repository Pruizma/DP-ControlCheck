package acme.features.authenticated.entrepreneus;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.roles.Entrepreneus;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/entrepreneus/")
public class AuthenticatedEntrepreneusController extends AbstractController<Authenticated, Entrepreneus>{

	@Autowired
	private AuthenticatedEntrepreneusCreateService	createService;

	@Autowired
	private AuthenticatedEntrepreneusUpdateService	updateService;


	// Constructors

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}
}
