package web.command.http.post;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Owner;
import org.apache.log4j.Logger;

import db.exception.AppException;
import db.exception.DBException;
import model.User;
import service.LoginService;
import service.RegistrationService;
import utils.HashUtil;
import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

public class RegistrationCommand implements Command{

private static Logger LOG = Logger.getLogger(RegistrationCommand.class);

	private RegistrationService registrServ;

	public RegistrationCommand(RegistrationService registrServ) {
		super();
		this.registrServ = registrServ;
	}
	
	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
			throws DBException, AppException {
		
		LOG.debug("Command starts");
		
		HttpSession session = request.getSession();
		HttpCommandResult cr = new HttpCommandResult(RequestType.POST,Path.PAGE_MAIN_POST);
		User user = new User();

		user.setLogin(request.getParameter("loginUser"));
		LOG.trace("Found in request parameters: login --> " + user.getLogin());
		
		try {
			user.setPassword(HashUtil.bin2hex(HashUtil.getSHA(request.getParameter("passwordUser"))));
		} catch (NoSuchAlgorithmException e) {
			LOG.error(e);
		}
		user.setRoleId(2);
		user.setName(request.getParameter("nameUser"));
		LOG.trace("Found in request parameters: name --> " + user.getName());
		
		user.setSurname(request.getParameter("surnameUser"));
		LOG.trace("Found in request parameters: surname --> " + user.getSurname());
		Owner owner = new Owner();
		owner.setCountOfCompany(0);
		owner.setEmail(request.getParameter("emailUser"));

		user = registrServ.insertUser(user, owner);

		
		int methodMain = 2;
		session.setAttribute("methodMain", methodMain);
		session.setAttribute("user", user);
		session.setAttribute("userRole", user.getRoleId());
		LOG.trace("Set the session attribute: methodMain --> " + methodMain);
		
		LOG.debug("Commands finished");
		return cr;
	}

}
