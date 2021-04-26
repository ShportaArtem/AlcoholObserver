package web.command.http.post;

import db.exception.AppException;
import db.exception.DBException;
import model.Employee;
import org.apache.log4j.Logger;
import service.EmployeeService;
import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResetEmployeeCommand implements Command {
    private static final Logger LOG = Logger.getLogger(ResetEmployeeCommand.class);

    private final EmployeeService employeeService;

    public ResetEmployeeCommand(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws DBException, AppException {
        LOG.debug("Command starts");

        Integer id = Integer.parseInt(request.getParameter("employeeId"));
        employeeService.resetEmployeeById(id);


        LOG.debug("Commands finished");
        return new HttpCommandResult(RequestType.POST, Path.PAGE_EMPLOYEE_POST + "&employeeId=" + id);
    }
}
