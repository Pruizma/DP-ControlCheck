
package acme.features.anonymous.technologyRecords;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.technologyRecords.TechnologyRecords;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/technology-records/")
public class AnonymousTechnologyRecordsController extends AbstractController<Anonymous, TechnologyRecords> {

	@Autowired
	private AnonymousTechnologyRecordsListService	listService;

	@Autowired
	private AnonymousTechnologyRecordsShowService	showService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
