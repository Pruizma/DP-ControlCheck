
package acme.features.anonymous.garciaRoalesBulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.bulletins.GarciaRoalesBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/garcia-roales-bulletin/")
public class AnonymousGarciaRoalesBulletinController extends AbstractController<Anonymous, GarciaRoalesBulletin> {

	// Internal state ---------------------------------------------------------
	@Autowired
	private AnonymousGarciaRoalesBulletinListService	listService;
	@Autowired
	private AnonymousGarciaRoalesBulletinCreateService	createService;


	// Constructors -----------------------------------------------------------
	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
