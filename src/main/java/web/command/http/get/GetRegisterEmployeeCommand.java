package web.command.http.get;


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

public class GetRegisterEmployeeCommand implements Command{
	private static final Logger LOG = Logger.getLogger(GetRegisterEmployeeCommand.class);


	public GetRegisterEmployeeCommand() {
		super();
	}

	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Command starts");
		CommandResult cr = new HttpCommandResult(RequestType.GET,  Path.PAGE_REGISTER_EMPLOYEE);
		LOG.debug("Command finished");
		return cr;
	}

}
