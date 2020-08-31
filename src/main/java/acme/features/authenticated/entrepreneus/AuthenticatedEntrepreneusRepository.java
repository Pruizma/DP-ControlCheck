package acme.features.authenticated.entrepreneus;


import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.customisationParameters.CustomisationParameter;
import acme.entities.roles.Entrepreneus;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedEntrepreneusRepository extends AbstractRepository {

	@Query("select e from Entrepreneus e where e.userAccount.id =?1")
	Entrepreneus findOneEntrepreneusByUserAccountId(int i);
	
	@Query("select u from UserAccount u where u.id =?1")
	UserAccount findOneUserAccountById(int i);
	
	@Query("select c from CustomisationParameter c")
	Collection<CustomisationParameter> findCustomisationParameters();
	
}