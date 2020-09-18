
package acme.features.administrator.dashboard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorDashboardListService implements AbstractListService<Administrator, Dashboard> {

	@Autowired
	AdministratroDashboardRepository repository;


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "investmentsWithoutQuittel", "applicationsWithLink", "applicationsWithPasswordProtected", "investment", "application", "investmentsWithoutQuittel2", "applicationsWithLink2", "applicationsWithPasswordProtected2");

	}

	@Override
	public Collection<Dashboard> findMany(final Request<Dashboard> request) {

		List<Dashboard> result = new ArrayList<>();
		Dashboard d = new Dashboard();

		Double investmentsWithMoreThanTenEuros = this.repository.investmentsWithoutQuittel();
		Double investments = this.repository.numberOfInvestments();

		Double applicationsWithJustification = this.repository.applicationsWithLink();
		Double applicationsWithEvenMoneyOffer = this.repository.applicationsWithPasswordProtected();
		Double applications = this.repository.numberOfApplications();

		Double resultInvestments = (double) (investmentsWithMoreThanTenEuros / investments);
		Double resultApplicationsJust = (double) (applicationsWithJustification / applications);
		Double resultApplicationsMon = (double) (applicationsWithEvenMoneyOffer / applications);

		d.setInvestmentsWithoutQuittel(resultInvestments);
		d.setApplicationsWithLink(resultApplicationsJust);
		d.setApplicationsWithPasswordProtected(resultApplicationsMon);
		d.setApplication(applications);
		d.setInvestment(investments);
		d.setInvestmentsWithoutQuittel2(investmentsWithMoreThanTenEuros);
		d.setApplicationsWithLink2(applicationsWithJustification);
		d.setApplicationsWithPasswordProtected2(applicationsWithEvenMoneyOffer);
		result.add(d);

		return result;
	}

}
