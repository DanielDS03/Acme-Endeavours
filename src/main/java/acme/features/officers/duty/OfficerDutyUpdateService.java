/*
 * AuthenticatedProviderUpdateService.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.officers.duty;
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
import acme.framework.services.AbstractUpdateService;

@Service
public class OfficerDutyUpdateService implements AbstractUpdateService<Officer, Duty> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected OfficerDutyRepository repository;
	
	@Autowired
	protected AdministratorThresholdRepository			thresholdRepository;

	@Autowired
	protected AdministratorSpamRepository	personalizationRepository;

	// AbstractUpdateService<Authenticated, Provider> interface ---------------
	
	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;
		boolean result;
		int dutyId;
		Duty duty;

		dutyId = request.getModel().getInteger("id");
		duty = this.repository.findOneDutyById(dutyId);
		result = duty != null && duty.getOfficer().getId() == request.getPrincipal().getActiveRoleId();

		return result;
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

		request.unbind(entity, model, "title","description", "start", "end", "workload","link", "publica");
		model.setAttribute("dutyId", entity.getId());
	}

	@Override
	public Duty findOne(final Request<Duty> request) {
		assert request != null;
		
		Duty result;
		
		int id;
		
		id = request.getModel().getInteger("id");
		
		result = this.repository.findOneDutyById(id);
		
		return result;
	}

	@Override
	public void validate(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("end")) {
			errors.state(request, entity.getEnd().after(entity.getStart()), "end", "officer.duty.error.end");
		}
		
		if((!errors.hasErrors("workload"))) {
			errors.state(request, entity.getWorkload().getMinutes()<=59, "workload", "default.error.workload");
		}
		
		if (!errors.hasErrors("start")&&!errors.hasErrors("end")&&!errors.hasErrors("workload")) {
			errors.state(request, Filter.calculate(entity.getStart(), entity.getEnd(), entity.getWorkload()), "workload", "acme.validation.decimal-max",Filter.calculate(entity.getStart(),  entity.getEnd()));
		}

		if (!errors.hasErrors("description")) {
			errors.state(request, Filter.filterString(entity.getDescription(),this.personalizationRepository.findCensoredWords(), this.thresholdRepository.findThresholdById()), "description", "officer.duty.form.error.description");
		}

		if (!errors.hasErrors("title")) {
			errors.state(request, Filter.filterString(entity.getTitle(),this.personalizationRepository.findCensoredWords(), this.thresholdRepository.findThresholdById()), "title", "officer.duty.form.error.title");
		}
		if((!errors.hasErrors("workload"))) {
			errors.state(request, entity.getWorkload().getTime()>0, "workload", "default.error.workload.zero");
		}
	}

	@Override
	public void update(final Request<Duty> request, final Duty entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}


}
