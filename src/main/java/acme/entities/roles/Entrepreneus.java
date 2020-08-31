package acme.entities.roles;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Entrepreneus extends UserRole{
	
	// Serialisation identifier -----------------------------------------------

		private static final long	serialVersionUID	= 1L;

		// Attributes -------------------------------------------------------------

		@NotBlank
		private String				startup;

		@NotBlank
		private String				activitySector;
		
		@NotBlank
		private String				qualificationRecord;
		
		@NotBlank
		private String				skillRecord;

		// Derived attributes -----------------------------------------------------

		// Relationships ----------------------------------------------------------

}
