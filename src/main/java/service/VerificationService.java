package service;

import db.DBManager;
import db.exception.AppException;
import db.exception.DBException;
import db.exception.Messages;
import db.repository.CompanyRep;
import db.repository.EmployeeRep;
import db.repository.PunishmentRep;
import db.repository.VerificationRep;
import db.utils.DBUtils;
import model.Company;
import model.Employee;
import model.Punishment;
import model.Verification;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VerificationService {
    private static final Logger LOG = Logger.getLogger(VerificationService.class);

    private final DBManager dbManager;
    private final VerificationRep verificationRep;
    private final EmployeeRep employeeRep;
    private final CompanyRep companyRep;
    private final PunishmentRep punishmentRep;

    public VerificationService(DBManager dbManager, VerificationRep verificationRep, EmployeeRep employeeRep, CompanyRep companyRep, PunishmentRep punishmentRep) {
        this.dbManager = dbManager;
        this.verificationRep = verificationRep;
        this.employeeRep = employeeRep;
        this.companyRep = companyRep;
        this.punishmentRep = punishmentRep;
    }

    public List<Verification> findVerificationsByUserId(Integer userId) throws AppException {
        List<Verification> verifications = null;
        Connection con = null;
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            verifications = verificationRep.findAllVerificationsByUserId(con, userId);
        } catch (SQLException ex) {
            DBUtils.rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_VERIFICATIONS, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_VERIFICATIONS, ex);
        } finally {
            DBUtils.close(con);
        }
        return verifications;
    }

    public void insertVerification(Verification verification) throws AppException {
        Connection con = null;
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            verification = verificationRep.addVerification(con, verification);
            if(verification.isCheck()){
                Employee employee = employeeRep.findEmployeeById(con, verification.getUserId());
                Company company = companyRep.findCompanyById(con, employee.getCompanyId());
                Punishment punishment = punishmentRep.findPunishmentById(con, company.getPunishmentId());
                employee.setCountOfViolation(employee.getCountOfViolation()+1);
                if(employee.getCountOfViolation().equals(punishment.getBorderValue())){
                    employee.setFine(true);
                }
                employee.setVerificationId(verification.getId());
                employeeRep.updateEmployeeByVerification(con, employee);
            }
            con.commit();
        } catch (SQLException ex) {
            LOG.error(Messages.ERR_CANNOT_INSERT_VERIFICATION, ex);
            throw new DBException(Messages.ERR_CANNOT_INSERT_VERIFICATION, ex);
        } finally {
            DBUtils.close(con);
        }
    }

    public Verification findVerificationById(Integer id) throws AppException {
        Connection con=null;
        Verification verification;
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(true);
            verification = verificationRep.findVerificationById(con, id);
        }catch (SQLException ex ) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_VERIFICATIONS, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_VERIFICATIONS, ex);
        }finally {
            DBUtils.close(con);
        }
        return verification;
    }

    public void insertExplanationForVerification(Verification verification, Employee employee) throws AppException {
        Connection con=null;
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            verificationRep.insertExplanationForVerification(con, verification);
            employeeRep.updateEmployeeByVerification(con, employee);
            con.commit();
        } catch (SQLException ex ) {
            LOG.error(Messages.ERR_CANNOT_UPDATE_VERIFICATION, ex);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_VERIFICATION, ex);
        } finally {
            DBUtils.close(con);
        }

    }
}
