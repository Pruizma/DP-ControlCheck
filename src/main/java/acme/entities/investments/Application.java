
package acme.entities.investments;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import acme.entities.offer.Offer;
import acme.entities.roles.Investor;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Application extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{2}-[0-9]{6}$", message = "{application.pattern.ticker}")
	private String				ticker;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				moment;

	@NotBlank
	@Pattern(regexp = ".*\\b(ACCEPTED|PENDING|REJECTED)\\b.*", message = "{application.pattern.statement}")
	private String				statement;

	private String				justification;

	@Valid
	@NotNull
	private Money				moneyOffer;

	@NotNull
	@Valid
	@OneToOne
	private Investment			investment;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Investor			investor;

	@Valid
	@OneToOne(optional = true)
	private Offer				offer;
}
