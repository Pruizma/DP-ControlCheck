
package acme.features.anonymous.banner;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.banners.Banner;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/banner/")
public class AnonymousBannerController extends AbstractController<Anonymous, Banner> {

	@Autowired
	private AnonymousBannerShowService showService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
