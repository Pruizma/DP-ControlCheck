
package acme.entities.discussionForums;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import acme.entities.investments.Investment;
import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DiscussionForum extends DomainEntity {

	public static final long			serialVersionUID	= 1L;

	@NotBlank
	private String						title;

	@NotNull
	@Valid
	@OneToOne
	private Investment					investment;

	@Valid
	@NotEmpty
	@ManyToMany
	private Collection<Authenticated>	accounts;

	@NotNull
	@Valid
	@OneToOne(optional = false)
	private Authenticated				leader;

	private String						aux;

}
