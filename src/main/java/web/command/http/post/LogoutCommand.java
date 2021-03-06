package web.command.http.post;

import db.exception.AppException;
import db.exception.DBException;
import org.apache.log4j.Logger;
import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Log out command
 * 
 * @author A.Shporta
 */
public class LogoutCommand implements Command{

	private static Logger LOG = Logger.getLogger(LogoutCommand.class);

	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws DBException, AppException {

		LOG.debug("Command starts");
		
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
			LOG.trace("Invalidate session");
		}
		CommandResult cr = new HttpCommandResult(RequestType.POST,  Path.PAGE_LOGIN);
		
		LOG.debug("Commands finished");
		return cr;
	}

}
