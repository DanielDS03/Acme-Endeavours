package acme.features.officer.workplan;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.roles.Officer;
import acme.entities.workplans.Workplan;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/officer/workplan/")
public class OfficerWorkplanController extends AbstractController<Officer, Workplan> {

	@Autowired
	protected OfficerWorkplanShowService showService;
	
	@Autowired
	protected OfficerWorkplanDeleteService deleteService;
	
	@Autowired
	protected OfficerWorkplanCreateService createService;
	
	@Autowired
	protected OfficerWorkplanUpdateService updateService;
	
	@Autowired
	protected OfficerWorkplanListService listService;
	
	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
	}
}
