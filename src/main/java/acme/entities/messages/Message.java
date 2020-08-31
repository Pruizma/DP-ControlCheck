
package acme.entities.messages;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import acme.entities.discussionForums.DiscussionForum;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Message extends DomainEntity {

	public static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				messageTitle;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				creationMoment;

	private String				tags;

	@NotBlank
	private String				body;

	private Boolean				sure;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private DiscussionForum		discussionForum;

}
