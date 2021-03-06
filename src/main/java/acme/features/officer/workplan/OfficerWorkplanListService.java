package acme.features.officer.workplan;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Officer;
import acme.entities.workplans.Workplan;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class OfficerWorkplanListService implements AbstractListService<Officer, Workplan> {
	
	@Autowired
	private OfficerWorkplanRepository repository;

	@Override
	public boolean authorise(final Request<Workplan> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Workplan> request, final Workplan entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "start", "end", "workload");
	}

	@Override
	public List<Workplan> findMany(final Request<Workplan> request) {
		assert request != null;		
		
		final List<Workplan> result=new ArrayList<>();
		final int workplanId=request.getPrincipal().getActiveRoleId();

		for (final Workplan workplan:this.repository.findMany()) {
			if (workplan.getOfficer().equals(this.repository.findOneWorkplanById(workplanId).getOfficer())) {
				result.add(workplan);
			}
		}
		
		return result.stream().sorted(Comparator.comparingDouble(Workplan::getWorkload)).collect(Collectors.toList());
	}
}
