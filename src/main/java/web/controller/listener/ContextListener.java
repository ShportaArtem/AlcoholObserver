package web.controller.listener;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import db.repository.CompanyRep;
import db.repository.EmployeeRep;
import db.repository.OwnerRep;
import db.repository.PunishmentRep;
import db.repository.VerificationRep;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import db.DBManager;
import db.exception.DBException;
import db.repository.UserRep;
import service.CompanyService;
import service.EmployeeService;
import service.LoginService;
import service.OwnerService;
import service.RegistrationService;
import service.VerificationService;
import web.command.http.HttpCommandDispatcher;
import web.command.http.get.DefaultCommand;
import web.command.http.get.GetALLCompaniesCommand;
import web.command.http.get.GetAddVerificationCommand;
import web.command.http.get.GetAllUsersCommand;
import web.command.http.get.GetBuyNewSlotCommand;
import web.command.http.get.GetCompaniesCommand;
import web.command.http.get.GetEmployeeCommand;
import web.command.http.get.GetEmployeesCommand;
import web.command.http.get.GetMainCommand;
import web.command.http.get.GetMyProfileCommand;
import web.command.http.get.GetMyVerificationsCommand;
import web.command.http.get.GetPunishmentCommand;
import web.command.http.get.GetRegisterEmployeeCommand;
import web.command.http.get.GetRegistrationCommand;
import web.command.http.get.GetUpdateCompanyCommand;
import web.command.http.post.AddCompanyCommand;
import web.command.http.post.AddVerificationCommand;
import web.command.http.post.BackUpCommand;
import web.command.http.post.BuySlotCommand;
import web.command.http.post.DeleteCompanyCommand;
import web.command.http.post.DeleteEmployeeCommand;
import web.command.http.post.DeleteUserCommand;
import web.command.http.post.InsertExplanationCommand;
import web.command.http.post.LoginCommand;
import web.command.http.post.LogoutCommand;
import web.command.http.post.RegistrationCommand;
import web.command.http.post.RegistrationEmployeeCommand;
import web.command.http.post.ResetEmployeeCommand;
import web.command.http.post.SelectCurrentCompanyCommand;
import web.command.http.post.UpdateCompanyCommand;
import web.command.http.post.UpdateEmployeeCommand;
import web.command.http.post.UpdatePunishmentCommand;


/**
 * Context listener.
 * 
 * @author A.Shporta
 * 
 */
public class ContextListener implements ServletContextListener {

	private static final Logger LOG = Logger.getLogger(ContextListener.class);

	public void contextDestroyed(ServletContextEvent event) {
		log("Servlet context destruction starts");
		log("Servlet context destruction finished");
	}

	public void contextInitialized(ServletContextEvent event) {
		log("Servlet context initialization starts");
		ServletContext context = event.getServletContext();
		String localesFileName = context.getInitParameter("locales");

		initLog4J(context);
		// obtain reale path on server
		String localesFileRealPath = context.getRealPath(localesFileName);
		// locad descriptions
		Properties locales = new Properties();
		try {
			locales.load(new FileInputStream(localesFileRealPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// save descriptions to servlet context
		context.setAttribute("locales", locales);
		locales.list(System.out);

		initContext(context);
		log("Servlet context initialization finished");
	}
	
	/**
	 * Initializes log4j framework.
	 * 
	 * @param servletContext
	 */
	private void initLog4J(ServletContext servletContext) {
		log("Log4J initialization started");
		try {
			PropertyConfigurator.configure(servletContext.getRealPath("WEB-INF/log4j.properties"));
			LOG.debug("Log4j has been initialized");
		} catch (Exception ex) {
			log("Cannot configure Log4j");
			ex.printStackTrace();
		}
		log("Log4J initialization finished");
	}
	
	/**
	 * Initializes CommandDispatcher with repositories and services.
	 * 
	 * @param context
	 */
	private void initContext(ServletContext context) {
		DBManager dbManager = null;
		try {
			dbManager = new DBManager();
		} catch (DBException e) {
			LOG.error(e);
		}
		
		UserRep userRep = new UserRep();
		OwnerRep ownerRep = new OwnerRep();
		CompanyRep companyRep = new CompanyRep();
		PunishmentRep punishmentRep = new PunishmentRep();
		EmployeeRep employeeRep = new EmployeeRep();
		VerificationRep verificationRep = new VerificationRep();

		LoginService loginService = new LoginService(dbManager, userRep);
		RegistrationService registrationService = new RegistrationService(dbManager, userRep, ownerRep, employeeRep);
		CompanyService companyService = new CompanyService(dbManager, companyRep, punishmentRep);
		OwnerService ownerService = new OwnerService(dbManager, ownerRep);
		EmployeeService employeeService = new EmployeeService(dbManager, employeeRep, userRep);
		VerificationService verificationService = new VerificationService(dbManager, verificationRep, employeeRep, companyRep, punishmentRep);

		HttpCommandDispatcher dispatcher = new HttpCommandDispatcher(new DefaultCommand());
		
		dispatcher.addCommand("login", new LoginCommand(loginService));
		dispatcher.addCommand("registration", new RegistrationCommand(registrationService));
		dispatcher.addCommand("getMain", new GetMainCommand());
		dispatcher.addCommand("getRegistration", new GetRegistrationCommand());
		dispatcher.addCommand("logout", new LogoutCommand());
		dispatcher.addCommand("getCompanies", new GetCompaniesCommand(companyService));
		dispatcher.addCommand("openBuyNewSlot", new GetBuyNewSlotCommand());
		dispatcher.addCommand("buySlot", new BuySlotCommand(ownerService));
		dispatcher.addCommand("addCompany", new AddCompanyCommand(companyService));
		dispatcher.addCommand("selectCurrentCompany", new SelectCurrentCompanyCommand());
		dispatcher.addCommand("openUpdateCompany", new GetUpdateCompanyCommand(companyService));
		dispatcher.addCommand("updateCompany", new UpdateCompanyCommand(companyService));
		dispatcher.addCommand("getPunishment", new GetPunishmentCommand(companyService));
		dispatcher.addCommand("updatePunishment", new UpdatePunishmentCommand(companyService));
		dispatcher.addCommand("getEmployees", new GetEmployeesCommand(employeeService));
		dispatcher.addCommand("openRegisterNewEmployee", new GetRegisterEmployeeCommand());
		dispatcher.addCommand("registerEmployee", new RegistrationEmployeeCommand(registrationService));
		dispatcher.addCommand("openEmployee", new GetEmployeeCommand(loginService, employeeService, verificationService));
		dispatcher.addCommand("updateEmployee", new UpdateEmployeeCommand(employeeService));
		dispatcher.addCommand("deleteEmployee", new DeleteEmployeeCommand(employeeService));
		dispatcher.addCommand("resetViolation", new ResetEmployeeCommand(employeeService));
		dispatcher.addCommand("openAddVerificationCommand", new GetAddVerificationCommand(employeeService));
		dispatcher.addCommand("addVerification", new AddVerificationCommand(verificationService));
		dispatcher.addCommand("getMyProfile", new GetMyProfileCommand(employeeService, verificationService));
		dispatcher.addCommand("insertDescriptionForVerification", new InsertExplanationCommand(verificationService));
		dispatcher.addCommand("getMyVerifications", new GetMyVerificationsCommand(verificationService));
		dispatcher.addCommand("doBackUp", new BackUpCommand());
		dispatcher.addCommand("getALlCompanies", new GetALLCompaniesCommand(companyService));
		dispatcher.addCommand("deleteCompany", new DeleteCompanyCommand(companyService));
		dispatcher.addCommand("getUsers", new GetAllUsersCommand(employeeService));
		dispatcher.addCommand("deleteUser", new DeleteUserCommand(employeeService));


		context.setAttribute("dispatcher", dispatcher);
		
		
		LOG.debug("Command dispatcher was successfully initialized");
	}

	private void log(String msg) {
		System.out.println("[ContextListener] " + msg);
	}
}
