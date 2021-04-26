package web.command.http.get;

import db.exception.AppException;
import db.exception.DBException;
import model.User;
import org.apache.log4j.Logger;
import service.EmployeeService;
import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetAddVerificationCommand implements Command{
	private static final Logger LOG = Logger.getLogger(GetAddVerificationCommand.class);

	private final EmployeeService employeeService;

	public GetAddVerificationCommand(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		LOG.debug("Command starts");
		List<User> users = employeeService.findAllUsersByRoleId(3);
		CommandResult cr = new HttpCommandResult(RequestType.GET,  Path.PAGE_ADD_VERIFICATION);
		request.getSession().setAttribute("allEmployees", users);
		LOG.debug("Command finished");
		return cr;
	}

}
