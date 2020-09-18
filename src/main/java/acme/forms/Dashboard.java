
package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	private static final long	serialVersionUID	= 1L;

	Double						investmentsWithoutQuittel;

	Double						applicationsWithLink;

	Double						applicationsWithPasswordProtected;

	Double						investment;

	Double						application;

	Double						investmentsWithoutQuittel2;

	Double						applicationsWithLink2;

	Double						applicationsWithPasswordProtected2;

}
