
package acme.entities.offer;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import acme.entities.investments.Application;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Offer extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				title;

	@URL
	private String				link;

	private Boolean				passProt;

	private String				pass;

	@Valid
	@OneToOne(optional = false)
	private Application			application;

}
