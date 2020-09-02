
package acme.entities.investments;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.roles.Entrepreneus;
import acme.entities.roles.Investor;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Investment extends DomainEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Column(unique = true)
	@NotBlank
	@Length(min = 6, max = 14)
	@Pattern(regexp = "^([A-Z]||\\d){3}-([0-9]||\\d){2}-([0-9]||\\d){6}$", message = "{master.pattern.ticker}")
	private String				ticker;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				moment;

	@NotBlank
	@Pattern(regexp = "^SEED$|ANGEL$|SERIES-A$|SERIES-B$|SERIES-C$|BRIDGE$", message = "{master.pattern.round}")
	private String				round;

	@NotBlank
	private String				title;

	@NotBlank
	private String				description;

	// Validador que este en € y que no sea menor a 0
	@NotNull
	@Valid
	private Money				money;

	@URL
	private String				url;

	private boolean				finalMode;

	//============>> Control Check <<=================

	@Length(max = 256)
	private String				filing;

	//============================================
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Entrepreneus		entrepreneus;

	@Valid
	@ManyToOne(optional = true)
	private Investor			investor;

}
