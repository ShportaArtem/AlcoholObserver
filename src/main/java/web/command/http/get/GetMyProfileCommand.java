package web.command.http.get;

import db.exception.AppException;
import model.Employee;
import model.User;
import model.Verification;
import org.apache.log4j.Logger;
import service.EmployeeService;
import service.VerificationService;
import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetMyProfileCommand implements Command {
    private static final Logger LOG = Logger.getLogger(GetMyProfileCommand.class);
    private final EmployeeService employeeService;
    private final VerificationService verificationService;

    public GetMyProfileCommand(EmployeeService employeeService, VerificationService verificationService) {
        this.employeeService = employeeService;
        this.verificationService = verificationService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");
        HttpSession session = request.getSession(false);
        Employee employee = employeeService.findEmployeeById(((User) session.getAttribute("user")).getId());
        session.setAttribute("employeeRightNow", employee);
        if(employee.getVerificationId()!=null){
            Verification verification = verificationService.findVerificationById(employee.getVerificationId());
            session.setAttribute("verificationForEmployee", verification);
        }
        CommandResult cr = new HttpCommandResult(RequestType.GET,  Path.PAGE_MY_PROFILE);
        LOG.debug("Command finished");
        return cr;
    }
}
