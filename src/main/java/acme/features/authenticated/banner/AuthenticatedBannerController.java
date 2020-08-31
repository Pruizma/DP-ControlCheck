
package acme.features.authenticated.banner;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.banners.Banner;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/banner/")
public class AuthenticatedBannerController extends AbstractController<Authenticated, Banner> {

	@Autowired
	private AuthenticatedBannerShowService showService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
