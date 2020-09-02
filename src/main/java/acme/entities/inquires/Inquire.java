
package acme.entities.inquires;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Inquire extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	// Attributes

	@NotBlank
	private String				title;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				creation;

	@Temporal(TemporalType.TIMESTAMP)
	private Date				deadline;

	@NotBlank
	private String				description;

	@NotNull
	@Valid
	private Money				moneyMin;

	@NotNull
	@Valid
	private Money				moneyMax;

	@Email
	@NotBlank
	private String				email;

}
