package web.command.http.post;

import db.exception.AppException;
import db.exception.DBException;
import model.Company;
import model.Punishment;
import model.User;
import model.Verification;
import org.apache.log4j.Logger;
import service.CompanyService;
import service.VerificationService;
import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

public class AddVerificationCommand implements Command {
    private static final Logger LOG = Logger.getLogger(AddVerificationCommand.class);
    private final VerificationService verificationService;

    public AddVerificationCommand(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws DBException, AppException {
        LOG.debug("Command starts");
        Verification verification = new Verification();
        String check = request.getParameter("check");
        verification.setCheck(check.equals("No"));
        verification.setUserId(Integer.parseInt(request.getParameter("employeeId")));
        verification.setDate(new Timestamp(System.currentTimeMillis()));
        verificationService.insertVerification(verification);

        CommandResult cr = new HttpCommandResult(RequestType.POST, Path.PAGE_MAIN_POST);
        LOG.debug("Commands finished");
        return cr;
    }
}
