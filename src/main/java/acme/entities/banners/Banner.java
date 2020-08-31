
package acme.entities.banners;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Banner extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@URL
	private String				picture;

	@NotBlank
	private String				name;

	/*
	 * //Relationships
	 *
	 * @NotNull
	 *
	 * @Valid
	 *
	 * @OneToOne(optional = false)
	 * private CreditCard creditCard;
	 */

}
