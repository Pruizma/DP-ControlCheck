
package acme.features.bookkeeper.accountingRecords;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/bookkeeper/accounting-record/")
public class BookkeperAccountingRecordsController extends AbstractController<Bookkeeper, AccountingRecord> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private BookkeeperAccountingRecordListService	listMineService;

	@Autowired
	private BookkeeperAccountingRecordShowService	showService;

	@Autowired
	private BookkeeperAccountingRecordCreateService	createService;

	@Autowired
	private BookkeeperAccountingRecordUpdateService	updateService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listMineService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}

}
