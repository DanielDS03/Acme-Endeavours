package acme.features.authenticated.dutie;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.duties.Dutie;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/dutie/")
public class AuthenticatedDutieController extends AbstractController<Authenticated, Dutie>{
@Autowired
protected AuthenticatedDutieListService listService;

@Autowired
protected AuthenticatedDutieShowService showService;

@PostConstruct
protected void initialise() {
	super.addBasicCommand(BasicCommand.LIST, this.listService);
	super.addBasicCommand(BasicCommand.SHOW, this.showService);
}
}

