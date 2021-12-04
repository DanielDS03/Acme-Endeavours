
package acme.features.officers.duty;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.duties.Duty;
import acme.entities.roles.Officer;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/officer/duty/")
public class OfficerDutyController extends AbstractController<Officer, Duty> {

	// Internal state 

	@Autowired
	protected OfficerDutyCreateService	createService;

	@Autowired
	private OfficerDutyListService		listService;

	@Autowired
	private OfficerDutyShowService		showService;

	@Autowired
	private OfficerDutyUpdateService		updateService;

	@Autowired
	private OfficerDutyDeleteService		deleteService;

	@Autowired
	protected OfficerDutyPublishService	publishService;
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
