
package acme.entities.overtures;

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
public class Overture extends DomainEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	private String				title;

	//Momento de Creacion
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				moment;

	//Fecha Limite
	@Temporal(TemporalType.TIMESTAMP)
	private Date				deadline;

	@NotBlank
	private String				description;

	//Intervalo del Dinero, dinero minimo
	@Valid
	@NotNull
	private Money				minMoney;

	//Intervalo del Dinero, dinero maximo
	@Valid
	@NotNull
	private Money				maxMoney;

	@Email
	private String				email;
}
