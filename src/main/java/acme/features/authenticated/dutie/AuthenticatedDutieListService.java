package acme.features.authenticated.dutie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Dutie;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;


@Service
public class AuthenticatedDutieListService implements AbstractListService<Authenticated, Dutie>{

	@Autowired
	AuthenticatedDutieRepository repository;
	@Override
	public boolean authorise(final Request<Dutie> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Dutie> request, final Dutie entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "start","end","workload", "publica");
		
	}

	@Override
	public Collection<Dutie> findMany(final Request<Dutie> request) {
assert request != null;
		
		final Collection <Dutie>  result= this.repository.findDutie();
		List <Dutie>  res = new ArrayList<>();
		
		for(final Dutie t:result) {
			if (t.isFinished()) res.add(t);
		}
		res = res.stream().sorted(Comparator.comparingInt(Dutie::getTime)).collect(Collectors.toList());
		
		return res;
	}

}
