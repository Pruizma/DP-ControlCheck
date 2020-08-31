package acme.entities.bookkeeperRequest;


import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.entities.DomainEntity;
import acme.framework.entities.UserAccount;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class BookkeeperRequest extends DomainEntity{

	// Serialisation identifier -----------------------------------------------

			private static final long	serialVersionUID	= 1L;

			// Attributes -------------------------------------------------------------

			@NotBlank
			private String				firmName;

			@NotBlank
			private String				statement;
			
			
			private Boolean				accepted;
			

			@NotNull
			@Valid
			@OneToOne(optional = true)
			private UserAccount			userAccount;

			// Derived attributes -----------------------------------------------------

			// Relationships ----------------------------------------------------------
}
