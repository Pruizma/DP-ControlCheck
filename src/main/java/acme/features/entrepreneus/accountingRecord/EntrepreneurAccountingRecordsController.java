
package acme.features.entrepreneus.accountingRecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.roles.Entrepreneus;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/entrepreneus/accounting-record/")
public class EntrepreneurAccountingRecordsController extends AbstractController<Entrepreneus, AccountingRecord> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private EntrepreneurAccountingRecordListMineService	listMineService;

	@Autowired
	private EntrepreneurAccountingRecordShowService		showService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listMineService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
