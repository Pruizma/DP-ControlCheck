
package acme.features.administrator.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratroDashboardRepository extends AbstractRepository {

	@Query("select count(i) from Investment i where i.quittel != ''")
	Double investmentsWithoutQuittel();

	@Query("select count(i) from Investment i")
	Double numberOfInvestments();

	// Es nuestro caso Offer es OneToOne por lo que si el Offer tiene link, la Application tendrá, y los que no tenga Offer pues no pueden tener link
	@Query("select count(a) from Offer a where a.link != null")
	Double applicationsWithLink();

	//Mismo proceidmiento que el de arriba

	@Query("select count(a) from Offer a where a.passProt != false")
	Double applicationsWithPasswordProtected();

	@Query("select count(a) from Application a")
	Double numberOfApplications();
}
