package acme.features.officer.workplan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Officer;
import acme.entities.workplans.Workplan;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class OfficerWorkplanShowService implements AbstractShowService<Officer, Workplan> {

	@Autowired
	protected OfficerWorkplanRepository repository;
	
	@Override
	public boolean authorise(final Request<Workplan> request) {
		assert request != null;
		boolean result;
		int taskId;		
		Officer officer;
		Principal principal;
		Workplan workplan;
		
		taskId = request.getModel().getInteger("id");
		workplan = this.repository.findOneWorkplanById(taskId);
		principal = request.getPrincipal();
		officer= workplan.getOfficer();
		result = officer.getUserAccount().getId()==principal.getAccountId();
		return result;
	}

	@Override
	public void unbind(final Request<Workplan> request, final Workplan entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity,model,"title","start","end","workload","finish","publica");
		model.setAttribute("taskId", entity.getId());
	}

	@Override
	public Workplan findOne(final Request<Workplan> request) {
		assert request != null;		
		
		Workplan result;
		int id;
		
		id = request.getModel().getInteger("id");
		result=this.repository.findOneWorkplanById(id);
		
		return result;
	}

}
