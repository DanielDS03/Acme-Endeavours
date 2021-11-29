package acme.features.anonymous.dutie;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.duties.Dutie;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/dutie/")
public class AnonymousDutieController extends AbstractController<Anonymous, Dutie> {
	
	// Internal state 

	@Autowired
	protected AnonymousDutieListService listService;
	
	@Autowired
	protected AnonymousDutieShowService showService;
	// Constructor

	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
	
}
