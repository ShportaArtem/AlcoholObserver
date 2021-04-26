package service;

import db.DBManager;
import db.exception.AppException;
import db.exception.DBException;
import db.exception.Messages;
import db.repository.EmployeeRep;
import db.repository.UserRep;
import db.utils.DBUtils;
import model.Employee;
import model.User;
import org.apache.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
    private static final Logger LOG = Logger.getLogger(EmployeeService.class);

    private final DBManager dbManager;
    private final EmployeeRep employeeRep;
    private final UserRep userRep;

    public EmployeeService(DBManager dbManager, EmployeeRep employeeRep, UserRep userRep) {
        this.dbManager = dbManager;
        this.employeeRep = employeeRep;
        this.userRep = userRep;
    }

    public List<User> findAllUsers() throws AppException{
        Connection con=null;
        List<User> users= null;
        try {
            con = dbManager.getConnection();
            users= userRep.findAllUsers(con);
        }catch(SQLException ex ) {
            DBUtils.rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_USERS, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USERS, ex);
        } finally {
            DBUtils.close(con);
        }
        return users;
    }

    public List<Employee> findEmployeesByCompanyId(Integer companyId) throws AppException {
        List<Employee> employees = null;
        Connection con = null;
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            employees = employeeRep.findAllEmployeesByCompanyId(con, companyId);
        } catch (SQLException ex) {
            DBUtils.rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_EMPLOYEES, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_EMPLOYEES, ex);
        } finally {
            DBUtils.close(con);
        }
        return employees;
    }

    public List<User> findAllUsersByRoleId(Integer roleId) throws AppException {
        List<User> users;
        Connection con = null;
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(true);
            users = userRep.findAllUsersByRoleId(con, roleId);
        } catch (SQLException | NoSuchAlgorithmException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_USERS, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USERS, ex);
        } finally {
            DBUtils.close(con);
        }
        return users;
    }

    public Employee findEmployeeById(int id) throws AppException {
        Employee employee;
        Connection con = null;
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            employee = employeeRep.findEmployeeById(con, id);
            con.commit();
        } catch (SQLException ex) {
            DBUtils.rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, ex);
        } finally {
            DBUtils.close(con);
        }
        return employee;
    }

    public void updateEmployee(User user, Employee employee) throws AppException {
        Connection con = null;
        try {
            con = dbManager.getConnection();
            userRep.updateUserByIdWithoutPassword(con, user);
            employeeRep.updateEmployeeById(con, employee);
            con.commit();
        } catch (SQLException ex) {
            DBUtils.rollback(con);
            LOG.error(Messages.ERR_CANNOT_UPDATE_USER, ex);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_USER, ex);
        } finally {
            DBUtils.close(con);
        }

    }

    public void resetEmployeeById(Integer id) throws AppException {
        Connection con = null;
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(true);
            employeeRep.resetEmployeeById(con, id);
        } catch (SQLException ex) {
            DBUtils.rollback(con);
            LOG.error(Messages.ERR_CANNOT_UPDATE_USER, ex);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_USER, ex);
        } finally {
            DBUtils.close(con);
        }

    }

    public void deleteEmployeeById(Integer employeeId) throws AppException {
        Connection con = null;
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(true);
            userRep.deleteUser(con, employeeId);
        } catch (SQLException ex) {
            LOG.error(Messages.ERR_CANNOT_DELETE_USER, ex);
            throw new DBException(Messages.ERR_CANNOT_DELETE_USER, ex);
        } finally {
            DBUtils.close(con);
        }

    }

}
