package service;

import java.sql.Connection;
import java.sql.SQLException;

import db.repository.EmployeeRep;
import db.repository.OwnerRep;
import model.Employee;
import model.Owner;
import org.apache.log4j.Logger;

import db.DBManager;
import db.exception.AppException;
import db.exception.DBException;
import db.exception.Messages;
import db.repository.UserRep;
import db.utils.DBUtils;
import model.User;

public class RegistrationService {
    private static final Logger LOG = Logger.getLogger(RegistrationService.class);

    private final DBManager dbManager;
    private final UserRep userRep;
    private final OwnerRep ownerRep;
    private final EmployeeRep employeeRep;

    public RegistrationService(DBManager dbManager, UserRep userRep, OwnerRep ownerRep, EmployeeRep employeeRep) {
        this.dbManager = dbManager;
        this.userRep = userRep;
        this.ownerRep = ownerRep;
        this.employeeRep = employeeRep;
    }

    public User insertUser(User user, Owner owner) throws AppException {
        Connection con = null;
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            user = userRep.insertUser(con, user);
            owner.setUserId(user.getId());
            ownerRep.insertOwner(con, owner);
            con.commit();
        } catch (SQLException ex) {
            LOG.error(Messages.ERR_CANNOT_INSERT_USER, ex);
            throw new DBException(Messages.ERR_CANNOT_INSERT_USER, ex);
        } finally {
            DBUtils.close(con);
        }
        return user;
    }

    public User insertEmployee(User user, Employee employee) throws AppException {
        Connection con = null;
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            user = userRep.insertUser(con, user);
            employee.setUserId(user.getId());
            employeeRep.insertEmployee(con, employee);
            con.commit();
        } catch (SQLException ex) {
            LOG.error(Messages.ERR_CANNOT_INSERT_USER, ex);
            throw new DBException(Messages.ERR_CANNOT_INSERT_USER, ex);
        } finally {
            DBUtils.close(con);
        }
        return user;
    }
}
