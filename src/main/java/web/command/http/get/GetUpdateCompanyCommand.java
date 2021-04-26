package web.command.http.get;

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

public class GetUpdateCompanyCommand implements Command {
    private static final Logger LOG = Logger.getLogger(GetUpdateCompanyCommand.class);

    public GetUpdateCompanyCommand(CompanyService companyService) {
        this.companyService = companyService;
    }

    private CompanyService companyService;
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws DBException, AppException {
        LOG.debug("Command starts");
        Integer companyId = Integer.parseInt(request.getParameter("companyId"));
        Company companyNow = companyService.findCompanyById(companyId);
        request.getSession(false).setAttribute("companyUpdate", companyNow);
        CommandResult cr = new HttpCommandResult(RequestType.GET, Path.PAGE_UPDATE_COMPANY);
        LOG.debug("Commands finished");
        return cr;
    }
}
