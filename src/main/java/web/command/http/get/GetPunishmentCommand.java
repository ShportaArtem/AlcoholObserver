package web.command.http.get;

import db.exception.AppException;
import db.exception.DBException;
import model.Company;
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
import javax.servlet.http.HttpSession;

public class GetPunishmentCommand implements Command {
    private static final Logger LOG = Logger.getLogger(GetPunishmentCommand.class);
    private final CompanyService companyService;

    public GetPunishmentCommand(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws DBException, AppException {
        LOG.debug("Command starts");

        HttpSession session = request.getSession(false);
        CommandResult cr = new HttpCommandResult(RequestType.GET, Path.PAGE_PUNISHMENT);
        Object attribute = session.getAttribute("companyNow");
        if (attribute != null){
            Company company = (Company) attribute;
            Punishment punishment = companyService.findPunishmentByCompany(company);
            session.setAttribute("punishmentNow", punishment);
        }
        LOG.debug("Command finished");
        return cr;
    }
}
