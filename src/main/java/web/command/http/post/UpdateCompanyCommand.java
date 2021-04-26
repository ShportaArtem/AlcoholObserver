package web.command.http.post;

import db.exception.AppException;
import db.exception.DBException;
import model.Company;
import org.apache.log4j.Logger;
import service.CompanyService;
import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateCompanyCommand implements Command {
    private static final Logger LOG = Logger.getLogger(UpdateCompanyCommand.class);
    private final CompanyService companyService;

    public UpdateCompanyCommand(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws DBException, AppException {
        LOG.debug("Command starts");
        Company company = new Company();
        company.setDescription(request.getParameter("descriptionCompany"));
        company.setName(request.getParameter("name"));
        company.setId(((Company) request.getSession().getAttribute("companyUpdate")).getId());
        companyService.updateCompany(company);
        CommandResult cr = new HttpCommandResult(RequestType.POST, Path.PAGE_COMPANIES_POST);
        LOG.debug("Commands finished");
        return cr;
    }
}
