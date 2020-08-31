
package acme.features.anonymous.fernandezTorreBulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.bulletins.FernandezTorreBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/fernandez-torre-bulletin/")
public class AnonymousFernandezTorreBulletinController extends AbstractController<Anonymous, FernandezTorreBulletin> {

	@Autowired
	private AnonymousFernandezTorreBulletinListService		listService;

	@Autowired
	private AnonymousFernandezTorreBulletinCreateService	createService;


	// Constructors

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
