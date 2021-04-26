package web.command.http.post;

import db.exception.AppException;
import org.apache.log4j.Logger;
import service.CompanyService;
import service.EmployeeService;
import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserCommand implements Command {
    private static final Logger LOG = Logger.getLogger(DeleteUserCommand.class);

    private final EmployeeService employeeService;

    public DeleteUserCommand(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");

        Integer id = Integer.parseInt(request.getParameter("userId"));

        employeeService.deleteEmployeeById(id);
        CommandResult cr = new HttpCommandResult(RequestType.POST, Path.PAGE_ALL_USERS_POST);

        LOG.debug("Commands finished");
        return cr;
    }
}
