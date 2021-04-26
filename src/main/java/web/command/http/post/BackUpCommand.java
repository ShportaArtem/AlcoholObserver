package web.command.http.post;

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
import javax.swing.JOptionPane;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;

public class BackUpCommand implements Command {
    private static final Logger LOG = Logger.getLogger(BackUpCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws DBException, AppException {
        LOG.debug("Command starts");
        boolean res = backupdbtosql();
        if(res){
            request.getSession().setAttribute("backupResult", "Backup completed!");
        }else {
            request.getSession().setAttribute("backupResult", "Backup failure!");
        }
        CommandResult cr = new HttpCommandResult(RequestType.POST, Path.PAGE_MAIN_POST);
        LOG.debug("Commands finished");
        return cr;
    }

    public boolean backupdbtosql() {
        try {
            String savePath = "C:\\Users\\User\\eclipse-workspace\\AlcoholObserver\\src\\main\\resources\\backup" + "\\backup.sql";

            /*NOTE: Used to create a cmd command*/
            String executeCmd = "C:\\Program Files\\MySQL\\MySQL Workbench 8.0 CE\\mysqldump -uroot" + " -proot" + " --add-drop-database -B " + "observerdb" + " -r " + savePath;

            /*NOTE: Executing the command here*/
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);

            int processComplete = runtimeProcess.waitFor();

            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            return processComplete == 0;

        } catch (IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "Error at Backuprestore" + ex.getMessage());
        }
        return false;

    }
}
