
package acme.entities.toolRecords;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ToolRecord extends DomainEntity {

	//Serializacion identifier ------------------------------

	private static final long	serialVersionUID	= 1L;

	//Atributes  --------------------------------------------

	@NotBlank
	private String				title;

	@NotBlank
	private String				activitySector;

	@NotBlank
	private String				inventorName;

	@NotBlank
	private String				description;

	@NotBlank
	@URL
	private String				webSite;

	@NotBlank
	@Email
	private String				email;

	private boolean				openSource;

	@Range(min = -5, max = 5)
	private Integer				stars;

}
