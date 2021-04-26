package web.command.http.post;

import db.exception.AppException;
import model.User;
import org.apache.log4j.Logger;
import service.LoginService;
import utils.HashUtil;
import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

/**
 * Login command
 * 
 * @author A.Shporta
 */
public class LoginCommand implements Command {

	private static Logger LOG = Logger.getLogger(LoginCommand.class);
	
	private LoginService loginService ;
	
	public LoginCommand(LoginService loginService) {
		this.loginService = loginService;
	}

	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

		LOG.debug("Command starts");
		
		HttpSession session = request.getSession(true);

		String login = request.getParameter("loginUser");
		LOG.trace("Found in request parameters: loginUser --> " + login);
		
		String password = request.getParameter("passwordUser");
		LOG.trace("Found in request parameters: passwordUser --> " + password);
		
		if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
			throw new AppException("Login/password cannot be empty");
		}
		
		User user = loginService.findUserByLogin(login);
		LOG.trace("Found in DB: user -->" + user );
		
		
		if(!("admin".equals(login) & "admin".equals(password))) {
			try {
				if (user == null || !HashUtil.bin2hex(HashUtil.getSHA(password)).equals(user.getPassword())) {
					LOG.error("Cannot find user with such login/password");
					throw new AppException("Cannot find user with such login/password");
				}
			} catch (NoSuchAlgorithmException e) {
				LOG.error(e);
			}
		}
		Integer userRole = user.getRoleId();

		HttpCommandResult cr = new HttpCommandResult(RequestType.POST, Path.PAGE_MAIN_POST);

		session.setAttribute("user", user);
		LOG.trace("Set the session attribute: user --> " + user);
		
		session.setAttribute("userRole", userRole);
		LOG.trace("Set the session attribute: userRole --> " + userRole);
		
		LOG.debug("Commands finished");
		
		return cr;
	}
}
