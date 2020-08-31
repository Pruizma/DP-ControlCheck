
package acme.features.anonymous.pardoLopezBulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.bulletins.PardoLopezBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/pardo-lopez-bulletin/")
public class AnonymousPardoLopezBulletinController extends AbstractController<Anonymous, PardoLopezBulletin> {

	@Autowired
	private AnonymousPardoLopezBulletinListService		listService;

	@Autowired
	private AnonymousPardoLopezBulletinCreateService	createService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
