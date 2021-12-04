package acme.features.anonymous.duty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractShowService;

@Service
public class AnonymousDutyShowService implements AbstractShowService<Anonymous, Duty>{

	@Autowired
	protected AnonymousDutyRepository repository;
	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request !=null;
		boolean result;
		int dutyId;
		Duty duty;
		
		dutyId= request.getModel().getInteger("id");
		duty = this.repository.findOneDutyById(dutyId);
		result =duty!=null&&duty.getPublica().equals(true)&&!duty.isFinished();
		return result;
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title","description","start","end","link","workload");
		model.setAttribute("dutyId", entity.getId());
	}

	@Override
	public Duty findOne(final Request<Duty> request) {
		assert request != null;
		
		Duty result;
		int id;
		
		id= request.getModel().getInteger("id");
		result = this.repository.findOneDutyById(id);
		
		return result;
	}
	

}
