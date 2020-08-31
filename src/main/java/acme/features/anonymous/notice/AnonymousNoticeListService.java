
package acme.features.anonymous.notice;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.notices.Notice;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousNoticeListService implements AbstractListService<Anonymous, Notice> {

	@Autowired
	AnonymousNoticeRepository repository;


	@Override
	public boolean authorise(final Request<Notice> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Notice> findMany(final Request<Notice> request) {
		assert request != null;

		Collection<Notice> result;
		Calendar calendar = Calendar.getInstance();
		Date d = calendar.getTime();
		result = this.repository.findManyAllFuture(d);

		return result;
	}

	@Override
	public void unbind(final Request<Notice> request, final Notice entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "headerImage", "title", "moment", "deadline", "body");
	}

}
