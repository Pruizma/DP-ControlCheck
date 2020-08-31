
package acme.features.bookkeeper.accountingRecords;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class BookkeeperAccountingRecordShowService implements AbstractShowService<Bookkeeper, AccountingRecord> {

	@Autowired
	BookkeeperAccountingRecordRepository repository;


	@Override
	public boolean authorise(final Request<AccountingRecord> request) {
		assert request != null;

		boolean result;
		int accountingRecordId;
		AccountingRecord accountingRecord;
		Bookkeeper bookkeeper;
		Principal principal;

		accountingRecordId = request.getModel().getInteger("id");
		accountingRecord = this.repository.findOneById(accountingRecordId);
		bookkeeper = accountingRecord.getBookkeeper();
		principal = request.getPrincipal();
		result = bookkeeper.getUserAccount().getId() == principal.getAccountId();
		return result;
	}

	@Override
	public void unbind(final Request<AccountingRecord> request, final AccountingRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "status", "creation", "body");
		model.setAttribute("bookkeeper", entity.getBookkeeper().getUserAccount().getUsername());
		model.setAttribute("investment", entity.getInvestment().getTicker());
	}

	@Override
	public AccountingRecord findOne(final Request<AccountingRecord> request) {
		assert request != null;

		AccountingRecord result;
		int id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
