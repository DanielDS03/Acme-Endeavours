
package acme.features.officers.duty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.roles.Officer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class OfficerDutyShowService implements AbstractShowService<Officer, Duty> {

	@Autowired
	protected OfficerDutyRepository repository;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;
		boolean result;
		int dutyId;
		Duty duty;
		Officer officer;
		Principal principal;

		dutyId = request.getModel().getInteger("id");
		duty = this.repository.findOneDutyById(dutyId);
		principal=request.getPrincipal();
		officer=duty.getOfficer();
		
		result =duty.getPublica() || !duty.getPublica() && officer.getUserAccount().getId()==principal.getAccountId();
		return result;
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "start", "end", "link", "workload","publica");
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

}
