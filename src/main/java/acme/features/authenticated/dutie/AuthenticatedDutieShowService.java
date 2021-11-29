package acme.features.authenticated.dutie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Dutie;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedDutieShowService implements AbstractShowService<Authenticated, Dutie>{

	@Autowired
	protected AuthenticatedDutieRepository repository;
	@Override
	public boolean authorise(final Request<Dutie> request) {
		assert request !=null;
		boolean result;
		int dutieId;
		Dutie dutie;
		
		dutieId= request.getModel().getInteger("id");
		dutie = this.repository.findOneDutieById(dutieId);
		result =dutie!=null&&dutie.getPublica().equals(true)&&dutie.isFinished();
		return result;
	}

	@Override
	public void unbind(final Request<Dutie> request, final Dutie entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title","description","start","end","link","workload");
		model.setAttribute("dutieId", entity.getId());
	}

	@Override
	public Dutie findOne(final Request<Dutie> request) {
		assert request != null;
		
		Dutie result;
		int id;
		
		id= request.getModel().getInteger("id");
		result = this.repository.findOneDutieById(id);
		
		return result;
	}
	

}
