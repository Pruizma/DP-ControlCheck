
package acme.features.entrepreneus.accountingRecord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.roles.Entrepreneus;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class EntrepreneurAccountingRecordListMineService implements AbstractListService<Entrepreneus, AccountingRecord> {

	@Autowired
	EntrepreneurAccountingRecordRepository repository;


	@Override
	public boolean authorise(final Request<AccountingRecord> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<AccountingRecord> request, final AccountingRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creation");

	}

	@Override
	public Collection<AccountingRecord> findMany(final Request<AccountingRecord> request) {
		assert request != null;

		Collection<AccountingRecord> result;

		result = this.repository.findManyByInvestmentId(request.getModel().getInteger("id"));

		return result;

	}
}
