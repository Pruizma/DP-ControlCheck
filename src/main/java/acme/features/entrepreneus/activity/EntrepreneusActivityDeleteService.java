package acme.features.entrepreneus.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investments.Activity;
import acme.entities.roles.Entrepreneus;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class EntrepreneusActivityDeleteService implements AbstractDeleteService<Entrepreneus, Activity> {
	//Internal State -----------------------------
		@Autowired
		EntrepreneusActivityRepository repository;

		@Override
		public boolean authorise(final Request<Activity> request) {
			assert request != null;
			return true;
		}

		public void bind(final Request<Activity> request, final Activity entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;

			request.bind(entity, errors);

		}

		@Override
		public void unbind(final Request<Activity> request, final Activity entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "title", "start", "end", "budget");

		}

		@Override
		public Activity findOne(final Request<Activity> request) {
			assert request != null;

			Activity result = new Activity();
			int id;

			id = request.getModel().getInteger("id");
			result = this.repository.findActivityById(id);
			return result;
		}

		@Override
		public void validate(final Request<Activity> request, final Activity entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;

		}

		@Override
		public void delete(final Request<Activity> request, final Activity entity) {
			assert request != null;
			assert entity != null;

			this.repository.delete(entity);
		}

	}
