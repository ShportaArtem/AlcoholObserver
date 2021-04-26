package web.command.http.post;

import db.exception.AppException;
import model.Company;
import model.Employee;
import model.User;
import org.apache.log4j.Logger;
import service.RegistrationService;
import utils.HashUtil;
import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

public class RegistrationEmployeeCommand implements Command {

    private static final Logger LOG = Logger.getLogger(RegistrationEmployeeCommand.class);

    private RegistrationService registrServ;

    public RegistrationEmployeeCommand(RegistrationService registrServ) {
        super();
        this.registrServ = registrServ;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws AppException {

        LOG.debug("Command starts");

        HttpSession session = request.getSession();
        HttpCommandResult cr = new HttpCommandResult(RequestType.POST, Path.PAGE_EMPLOYEES_POST);
        User user = new User();

        user.setLogin(request.getParameter("loginUser"));

        try {
            user.setPassword(HashUtil.bin2hex(HashUtil.getSHA(request.getParameter("passwordUser"))));
        } catch (NoSuchAlgorithmException e) {
            LOG.error(e);
        }
        user.setRoleId(3);
        user.setName(request.getParameter("nameUser"));
        user.setSurname(request.getParameter("surnameUser"));

        Employee employee = new Employee();
        employee.setEmail(request.getParameter("emailUser"));
        employee.setAddress(request.getParameter("address"));
        employee.setPhone(request.getParameter("phone"));
        employee.setFine(false);
        employee.setCountOfViolation(0);
        employee.setCompanyId(((Company) session.getAttribute("companyNow")).getId());
        registrServ.insertEmployee(user, employee);
        LOG.debug("Commands finished");
        return cr;
    }

}
