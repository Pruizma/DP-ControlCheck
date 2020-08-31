
package acme.features.anonymous.ruizMateosBulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.bulletins.RuizMateosBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/ruiz-mateos-bulletin/")
public class AnonymousRuizMateosBulletinController extends AbstractController<Anonymous, RuizMateosBulletin> {

	@Autowired
	private AnonymousRuizMateosBulletinListService		listService;

	@Autowired
	private AnonymousRuizMateosBulletinCreateService	createService;


	// Constructors

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
