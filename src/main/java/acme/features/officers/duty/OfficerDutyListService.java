package acme.features.officers.duty;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.roles.Officer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class OfficerDutyListService implements AbstractListService<Officer, Duty> {
	
	@Autowired
	private OfficerDutyRepository repository;

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
		
		request.unbind(entity, model, "title", "start","description", "end", "workload");
	}

	@Override
	public List<Duty> findMany(final Request<Duty> request) {
		assert request != null;		
		
		final List<Duty> result=new ArrayList<>();
		final int workerId=request.getPrincipal().getActiveRoleId();

		for (final Duty duty:this.repository.findMany()) {
			if (duty.getOfficer().equals(this.repository.findOneOfficerbyUserAccountById(workerId))) {
				result.add(duty);
			}
		}
		
		return result.stream().sorted(Comparator.comparingInt(Duty::getTime)).collect(Collectors.toList());
	}


}

