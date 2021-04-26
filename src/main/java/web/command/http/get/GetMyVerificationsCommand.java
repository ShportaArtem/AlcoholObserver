package web.command.http.get;

import db.exception.AppException;
import model.User;
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
import java.util.List;

public class GetMyVerificationsCommand implements Command {
    private static final Logger LOG = Logger.getLogger(GetMyVerificationsCommand.class);

    private final VerificationService verificationService;

    public GetMyVerificationsCommand(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");

        HttpSession session = request.getSession(false);
        CommandResult cr = new HttpCommandResult(RequestType.GET, Path.PAGE_MY_VERIFICATIONS);
        List<Verification> verifications = verificationService.findVerificationsByUserId((((User) session.getAttribute("user")).getId()));
        session.setAttribute("verificationsForEmployee", verifications);

        LOG.debug("Command finished");
        return cr;
    }
}
