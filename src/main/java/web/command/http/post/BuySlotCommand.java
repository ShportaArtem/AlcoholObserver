package web.command.http.post;

import db.exception.AppException;
import db.exception.DBException;
import model.Owner;
import model.User;
import org.apache.log4j.Logger;
import service.OwnerService;
import web.Path;
import web.command.Command;
import web.command.CommandResult;
import web.command.http.HttpCommandResult;
import web.controller.RequestType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BuySlotCommand implements Command {
    private static final Logger LOG = Logger.getLogger(BuySlotCommand.class);
    private final OwnerService ownerService;

    public BuySlotCommand(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws DBException, AppException {
        LOG.debug("Command starts");
        HttpSession session = request.getSession();
        Owner owner = ownerService.findOwnerById(((User) session.getAttribute("user")).getId());
        owner.setCountOfCompany(owner.getCountOfCompany()+1);
        ownerService.updateOwner(owner);
        CommandResult cr = new HttpCommandResult(RequestType.POST, Path.PAGE_CREATE_COMPANY);
        LOG.debug("Commands finished");
        return cr;
    }
}
