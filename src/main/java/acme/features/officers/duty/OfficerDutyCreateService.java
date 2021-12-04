package acme.features.officers.duty;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.roles.Officer;
import acme.features.administrator.personalization.AdministratorSpamRepository;
import acme.features.administrator.threshold.AdministratorThresholdRepository;
import acme.filter.Filter;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class OfficerDutyCreateService implements AbstractCreateService<Officer, Duty> {

	// Internal state

	@Autowired
	protected OfficerDutyRepository						repository;

		@Autowired
		protected AdministratorThresholdRepository			thresholdRepository;

	@Autowired
	protected AdministratorSpamRepository	personalizationRepository;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description","start","end", "link","workload", "publica");
	}

	@Override
	public Duty instantiate(final Request<Duty> request) {
		assert request != null;

		Duty result;
		Officer officer;
		officer = this.repository.findOneOfficerbyUserAccountById(request.getPrincipal().getActiveRoleId());

		result = new Duty();
		result.setPublica(false);
		result.setOfficer(officer);

		return result;
	}

	@Override
	public void validate(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if (!errors.hasErrors("end") && !errors.hasErrors("start")) {
			errors.state(request, entity.getEnd().after(entity.getStart()), "end", "officer.duty.error.end");
		}
		
		if (!errors.hasErrors("start")) {
			Date moment;
	        moment = new Date(System.currentTimeMillis() - 1);
			errors.state(request, entity.getStart().after(moment), "start", "officer.duty.error.start");
		}
		
		if((!errors.hasErrors("workload"))) {
			errors.state(request, entity.getWorkload().getMinutes()<=59, "workload", "default.error.workload");
		}
		
		if (!errors.hasErrors("start")&&!errors.hasErrors("end")&&!errors.hasErrors("workload")) {
			errors.state(request, Filter.calculate(entity.getStart(), entity.getEnd(), entity.getWorkload()), "workload", "acme.validation.decimal-max",Filter.calculate(entity.getStart(),  entity.getEnd()));
		}
		if((!errors.hasErrors("workload"))) {
			errors.state(request, entity.getWorkload().getTime()>0, "workload", "default.error.workload.zero");
		}
		if (!errors.hasErrors("description")) {
			errors.state(request, Filter.filterString(entity.getDescription(),this.personalizationRepository.findCensoredWords(), this.thresholdRepository.findThresholdById()), "description", "officer.duty.form.error.description");
		}

		if (!errors.hasErrors("title")) {
			errors.state(request, Filter.filterString(entity.getTitle(),this.personalizationRepository.findCensoredWords(), this.thresholdRepository.findThresholdById()), "title", "officer.duty.form.error.title");
		}
	

	}

	@Override
	public void create(final Request<Duty> request, final Duty entity) {
		assert request != null;
		assert entity != null;

		entity.setPublica(false);
		this.repository.save(entity);
	}
	
}

	

