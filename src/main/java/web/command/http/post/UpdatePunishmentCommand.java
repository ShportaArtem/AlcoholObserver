package web.command.http.post;

import db.exception.AppException;
import db.exception.DBException;
import model.Punishment;
import org.apache.log4j.Logger;
import service.CompanyService;
import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdatePunishmentCommand implements Command {
    private static final Logger LOG = Logger.getLogger(UpdatePunishmentCommand.class);
    private final CompanyService companyService;

    public UpdatePunishmentCommand(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws DBException, AppException {
        LOG.debug("Command starts");
        Punishment punishmentNow = (Punishment) request.getSession().getAttribute("punishmentNow");
        punishmentNow.setBorderValue(Integer.parseInt(request.getParameter("borderValue")));
        punishmentNow.setDescription(request.getParameter("descriptionPunishment"));
        companyService.updatePunishment(punishmentNow);
        request.getSession().setAttribute("punishmentNow", punishmentNow);
        CommandResult cr = new HttpCommandResult(RequestType.POST, Path.PAGE_PUNISHMENT_POST);
        LOG.debug("Commands finished");
        return cr;
    }
}
