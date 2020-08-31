
package acme.entities.challenges;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Challenge extends DomainEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	private String				title;

	//En el validate hay que poner que cuando se crea, el deadline tiene que ser el momento
	//actual +1 mes desde entonces como minimo en el futuro
	@Temporal(TemporalType.TIMESTAMP)
	private Date				deadline;

	@NotBlank
	private String				description;

	@NotBlank
	private String				rookieGoal;

	@NotNull
	@Valid
	private Money				rookieReward;

	@NotBlank
	private String				averageGoal;

	@NotNull
	@Valid
	private Money				averageReward;

	@NotBlank
	private String				expertGoal;

	@NotNull
	@Valid
	private Money				expertReward;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
}
