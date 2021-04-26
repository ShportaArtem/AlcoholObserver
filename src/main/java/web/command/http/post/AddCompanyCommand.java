package web.command.http.post;

import db.exception.AppException;
import db.exception.DBException;
import model.Company;
import model.Punishment;
import model.User;
import org.apache.log4j.Logger;
import service.CompanyService;
import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCompanyCommand implements Command {
    private static final Logger LOG = Logger.getLogger(AddCompanyCommand.class);
    private final CompanyService companyService;

    public AddCompanyCommand(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws DBException, AppException {
        LOG.debug("Command starts");
        Punishment punishment = new Punishment();
        punishment.setBorderValue(Integer.parseInt(request.getParameter("borderValue")));
        punishment.setDescription(request.getParameter("descriptionPunishment"));
        punishment = companyService.insertPunishment(punishment);

        Company company = new Company();
        company.setPunishmentId(punishment.getId());
        company.setUserId(((User) request.getSession().getAttribute("user")).getId());
        company.setDescription(request.getParameter("descriptionCompany"));
        company.setName(request.getParameter("name"));
        companyService.insertCompany(company);

        CommandResult cr = new HttpCommandResult(RequestType.POST, Path.PAGE_COMPANIES_POST);
        LOG.debug("Commands finished");
        return cr;
    }
}
