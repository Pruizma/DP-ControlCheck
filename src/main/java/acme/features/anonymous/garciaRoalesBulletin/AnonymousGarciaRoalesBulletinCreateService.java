
package acme.features.anonymous.garciaRoalesBulletin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.GarciaRoalesBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousGarciaRoalesBulletinCreateService implements AbstractCreateService<Anonymous, GarciaRoalesBulletin> {

	// Internal state ---------------------------------------------------------
	@Autowired
	AnonymousGarciaRoalesBulletinRepository repository;


	@Override
	public boolean authorise(final Request<GarciaRoalesBulletin> request) {
		assert request != null;
		return true;
	}

	@Override
	public GarciaRoalesBulletin instantiate(final Request<GarciaRoalesBulletin> request) {
		assert request != null;
		GarciaRoalesBulletin result;
		Date birth;
		try {
			birth = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse("1998/02/02 18:00");
		} catch (ParseException e) {
			birth = null;
		}
		result = new GarciaRoalesBulletin();
		result.setName("Jose Joaquin");
		result.setSurname("Rojas Romero");
		result.setUvus("josrojrom1");
		result.setBirth(birth);
		return result;
	}

	@Override
	public void unbind(final Request<GarciaRoalesBulletin> request, final GarciaRoalesBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "name", "surname", "uvus", "birth");
	}

	@Override
	public void bind(final Request<GarciaRoalesBulletin> request, final GarciaRoalesBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<GarciaRoalesBulletin> request, final GarciaRoalesBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<GarciaRoalesBulletin> request, final GarciaRoalesBulletin entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
	}

}
