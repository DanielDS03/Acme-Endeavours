
package acme.features.officers.task;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.roles.Officer;
import acme.entities.tasks.Task;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/officer/task/")
public class OfficerTaskController extends AbstractController<Officer, Task> {

	// Internal state 

	@Autowired
	protected OfficerTaskCreateService	createService;

	@Autowired
	private OfficerTaskListService		listService;

	@Autowired
	private OfficerTaskShowService		showService;

	@Autowired
	private OfficerTaskUpdateService		updateService;

	@Autowired
	private OfficerTaskDeleteService		deleteService;

	@Autowired
	protected OfficerTaskPublishService	publishService;
	// Constructor


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		
		super.addCustomCommand(CustomCommand.PUBLISH, BasicCommand.UPDATE, this.publishService);
	}

}
