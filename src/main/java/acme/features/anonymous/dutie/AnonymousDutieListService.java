package acme.features.anonymous.dutie;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Dutie;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousDutieListService implements AbstractListService<Anonymous, Dutie> {

	@Autowired
	AnonymousDutieRepository repository;
	
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
		
		Collection <Dutie>  result;
		
		result = this.repository.findDutie().stream().filter(t->!t.isFinished()).sorted(Comparator.comparingInt(Dutie::getTime)).collect(Collectors.toList());

		return result;
	}

}
