
package acme.features.officers.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Officer;
import acme.entities.tasks.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class OfficerTaskShowService implements AbstractShowService<Officer, Task> {

	@Autowired
	protected OfficerTaskRepository repository;


	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;
		boolean result;
		int taskId;
		Task task;
		Officer officer;
		Principal principal;

		taskId = request.getModel().getInteger("id");
		task = this.repository.findOneTaskById(taskId);
		principal=request.getPrincipal();
		officer=task.getOfficer();
		
		result =task.getPublica() || !task.getPublica() && officer.getUserAccount().getId()==principal.getAccountId();
		return result;
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "start", "end", "link", "workload","publica");
		model.setAttribute("taskId", entity.getId());
	}

	@Override
	public Task findOne(final Request<Task> request) {
		assert request != null;

		Task result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneTaskById(id);

		return result;
	}

}
