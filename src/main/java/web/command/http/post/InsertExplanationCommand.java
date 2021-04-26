package web.command.http.post;

import db.exception.AppException;
import db.exception.DBException;
import model.Employee;
import model.Verification;
import org.apache.log4j.Logger;
import service.VerificationService;
import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class InsertExplanationCommand implements Command {
    private static final Logger LOG = Logger.getLogger(InsertExplanationCommand.class);
    private final VerificationService verificationService;

    public InsertExplanationCommand(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws DBException, AppException {
        LOG.debug("Command starts");
        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("employeeRightNow");
        Verification verification = (Verification) session.getAttribute("verificationForEmployee");
        verification.setDescription(request.getParameter("descriptionForVerification"));
        employee.setVerificationId(null);
        verificationService.insertExplanationForVerification(verification, employee);
        session.removeAttribute("verificationForEmployee");
        CommandResult cr = new HttpCommandResult(RequestType.POST, Path.PAGE_MY_PROFILE_POST);
        LOG.debug("Commands finished");
        return cr;
    }
}
