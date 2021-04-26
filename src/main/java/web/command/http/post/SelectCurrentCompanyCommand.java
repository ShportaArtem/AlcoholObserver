package web.command.http.post;

import db.exception.AppException;
import db.exception.DBException;
import model.Company;
import org.apache.log4j.Logger;
import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SelectCurrentCompanyCommand implements Command {
    private static final Logger LOG = Logger.getLogger(SelectCurrentCompanyCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws DBException, AppException {
        LOG.debug("Command starts");
        HttpSession session = request.getSession(false);
        List<Company> companies = (List<Company>) session.getAttribute("companiesNow");
        final Integer companyId = Integer.parseInt(request.getParameter("companyId"));
        Company companyNow = companies.stream().filter(c -> c.getId().equals(companyId)).findFirst().get();
        session.setAttribute("companyNow", companyNow);
        CommandResult cr = new HttpCommandResult(RequestType.POST, Path.PAGE_COMPANIES_POST);
        LOG.debug("Commands finished");
        return cr;
    }
}
