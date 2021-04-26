package web.command.http.get;

import db.exception.AppException;
import db.exception.DBException;
import model.Company;
import model.Employee;
import model.Punishment;
import model.User;
import model.Verification;
import org.apache.log4j.Logger;
import service.CompanyService;
import service.EmployeeService;
import service.LoginService;
import service.VerificationService;
import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetEmployeeCommand implements Command {
    private static final Logger LOG = Logger.getLogger(GetEmployeeCommand.class);

    private final LoginService loginService;
    private final EmployeeService employeeService;
    private final VerificationService verificationService;

    public GetEmployeeCommand(LoginService loginService, EmployeeService employeeService, VerificationService verificationService) {
        this.loginService = loginService;
        this.employeeService = employeeService;
        this.verificationService = verificationService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");

        HttpSession session = request.getSession(false);
        CommandResult cr = new HttpCommandResult(RequestType.GET, Path.PAGE_EMPLOYEE);
        Integer id = Integer.parseInt(request.getParameter("employeeId"));
        User user = loginService.findUserById(id);
        Employee employee = employeeService.findEmployeeById(id);
        List<Verification> verifications = verificationService.findVerificationsByUserId(id);
        session.setAttribute("userEmployeeNow", user);
        session.setAttribute("employeeRightNow", employee);
        session.setAttribute("verificationsForEmployee", verifications);

        LOG.debug("Command finished");
        return cr;
    }
}
