package web.command.http.get;


import db.exception.AppException;
import db.exception.DBException;
import org.apache.log4j.Logger;
import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetBuyNewSlotCommand implements Command{
	private static final Logger LOG = Logger.getLogger(GetBuyNewSlotCommand.class);


	public GetBuyNewSlotCommand() {
		super();
	}

	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
			throws DBException, AppException {
		
		LOG.debug("Command starts");
		
		CommandResult cr = new HttpCommandResult(RequestType.GET,  Path.PAGE_BUY_NEW_SLOT);
		LOG.debug("Command finished");
		return cr;
	}

}
