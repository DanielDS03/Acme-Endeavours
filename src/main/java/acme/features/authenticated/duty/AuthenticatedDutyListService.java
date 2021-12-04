package acme.features.authenticated.duty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;


@Service
public class AuthenticatedDutyListService implements AbstractListService<Authenticated, Duty>{

	@Autowired
	AuthenticatedDutyRepository repository;
	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "start","end","workload", "publica");
		
	}

	@Override
	public Collection<Duty> findMany(final Request<Duty> request) {
assert request != null;
		
		final Collection <Duty>  result= this.repository.findDuty();
		List <Duty>  res = new ArrayList<>();
		
		for(final Duty t:result) {
			if (t.isFinished()) res.add(t);
		}
		res = res.stream().sorted(Comparator.comparingInt(Duty::getTime)).collect(Collectors.toList());
		
		return res;
	}

}
