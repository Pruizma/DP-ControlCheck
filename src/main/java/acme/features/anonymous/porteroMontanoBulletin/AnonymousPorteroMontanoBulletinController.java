
package acme.features.anonymous.porteroMontanoBulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.bulletins.PorteroMontanoBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/portero-montano-bulletin/")
public class AnonymousPorteroMontanoBulletinController extends AbstractController<Anonymous, PorteroMontanoBulletin> {

	@Autowired
	private AnonymousPorteroMontanoBulletinListService		listService;

	@Autowired
	private AnonymousPorteroMontanoBulletinCreateService	createService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
