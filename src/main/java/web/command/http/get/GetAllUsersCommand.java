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
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetAllUsersCommand implements Command {
    private final static Logger LOG = Logger.getLogger(GetAllUsersCommand.class);
    private final EmployeeService employeeService;

    public GetAllUsersCommand(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws DBException, AppException {
        LOG.debug("Command starts");
        HttpSession session = request.getSession(false);
        CommandResult cr = new HttpCommandResult(RequestType.GET, Path.PAGE_ALL_USERS);
        List<User> users = employeeService.findAllUsers();
        session.setAttribute("allUsers", users);
        LOG.debug("Command finished");
        return cr;
    }
}
