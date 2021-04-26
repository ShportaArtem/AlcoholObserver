package web.command.http.post;

import db.exception.AppException;
import model.Company;
import model.Employee;
import model.User;
import org.apache.log4j.Logger;
import service.EmployeeService;
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

public class UpdateEmployeeCommand implements Command {

    private static final Logger LOG = Logger.getLogger(UpdateEmployeeCommand.class);

    private final EmployeeService employeeService;

    public UpdateEmployeeCommand(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws AppException {

        LOG.debug("Command starts");

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("userEmployeeNow");

        user.setLogin(request.getParameter("loginUser"));
        user.setName(request.getParameter("nameUser"));
        user.setSurname(request.getParameter("surnameUser"));

        Employee employee = new Employee();
        employee.setUserId(user.getId());
        employee.setEmail(request.getParameter("emailUser"));
        employee.setAddress(request.getParameter("address"));
        employee.setPhone(request.getParameter("phone"));
        employeeService.updateEmployee(user, employee);
        LOG.debug("Commands finished");
        return new HttpCommandResult(RequestType.POST, Path.PAGE_EMPLOYEE_POST + "&employeeId=" + employee.getUserId());
    }

}
