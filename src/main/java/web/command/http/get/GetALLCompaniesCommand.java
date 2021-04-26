package web.command.http.get;

import db.exception.AppException;
import db.exception.DBException;
import model.Company;
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
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetALLCompaniesCommand implements Command {
    private final static Logger LOG = Logger.getLogger(GetALLCompaniesCommand.class);
    private final CompanyService companyService;

    public GetALLCompaniesCommand(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws DBException, AppException {
        LOG.debug("Command starts");

        HttpSession session = request.getSession(false);
        CommandResult cr = new HttpCommandResult(RequestType.GET, Path.PAGE_ALL_COMPANIES);
        List<Company> companies = companyService.findAllCompanies();
        if (companies.size() > 0) {
            session.setAttribute("allCompaniesNow", companies);
        }

        LOG.debug("Command finished");
        return cr;
    }
}
