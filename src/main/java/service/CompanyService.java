package service;

import db.DBManager;
import db.exception.AppException;
import db.exception.DBException;
import db.exception.Messages;
import db.repository.CompanyRep;
import db.repository.PunishmentRep;
import db.utils.DBUtils;
import model.Company;
import model.Punishment;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CompanyService {
    private static final Logger LOG = Logger.getLogger(CompanyService.class);

    private final DBManager dbManager;
    private final CompanyRep companyRep;
    private final PunishmentRep punishmentRep;

    public CompanyService(DBManager dbManager, CompanyRep companyRep, PunishmentRep punishmentRep) {
        this.companyRep = companyRep;
        this.dbManager = dbManager;
        this.punishmentRep = punishmentRep;
    }

    public void deleteCompanyById(Integer companyId) throws AppException {
        Connection con=null;
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(true);
            companyRep.deleteCompany(con, companyId);
        }catch (SQLException ex ) {
            LOG.error(Messages.ERR_CANNOT_DELETE_COMPANY, ex);
            throw new DBException(Messages.ERR_CANNOT_DELETE_COMPANY, ex);
        }finally {
            DBUtils.close(con);
        }

    }

    public List<Company> findAllCompanies() throws AppException{
        Connection con=null;
        List<Company> companies= null;
        try {
            con = dbManager.getConnection();
            companies = companyRep.findAllCompanies(con);
        }catch(SQLException ex ) {
            DBUtils.rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_COMPANIES, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_COMPANIES, ex);
        } finally {
            DBUtils.close(con);
        }
        return companies;
    }

    public List<Company> findCompaniesByUserId(Integer userId) throws AppException {
        List<Company> companies = null;
        Connection con = null;
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            companies = companyRep.findAllCompaniesByUserId(con, userId);
        } catch (SQLException ex) {
            DBUtils.rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_COMPANIES, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_COMPANIES, ex);
        } finally {
            DBUtils.close(con);
        }
        return companies;
    }

    public Punishment insertPunishment(Punishment punishment) throws AppException {
        Connection con = null;
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(true);
            punishment = punishmentRep.insertPunishment(con, punishment);
        } catch (SQLException ex) {
            LOG.error(Messages.ERR_CANNOT_INSERT_PUNISHMENT, ex);
            throw new DBException(Messages.ERR_CANNOT_INSERT_PUNISHMENT, ex);
        } finally {
            DBUtils.close(con);
        }
        return punishment;
    }

    public void insertCompany(Company company) throws AppException {
        Connection con = null;
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(true);
            companyRep.insertCompany(con, company);
        } catch (SQLException ex) {
            LOG.error(Messages.ERR_CANNOT_INSERT_COMPANY, ex);
            throw new DBException(Messages.ERR_CANNOT_INSERT_COMPANY, ex);
        } finally {
            DBUtils.close(con);
        }
    }

    public Company findCompanyById(Integer id) throws AppException {
        Connection con = null;
        Company company;
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(true);
            company = companyRep.findCompanyById(con, id);
        } catch (SQLException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_COMPANIES, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_COMPANIES, ex);
        } finally {
            DBUtils.close(con);
        }
        return company;
    }

    public void updateCompany(Company company) throws AppException {
        Connection con = null;
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(true);
            companyRep.updateCar(con, company);
        } catch (SQLException ex) {
            LOG.error(Messages.ERR_CANNOT_UPDATE_COMPANY, ex);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_COMPANY, ex);
        } finally {
            DBUtils.close(con);
        }

    }

    public void updatePunishment(Punishment punishment) throws AppException {
        Connection con=null;
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(true);
            punishmentRep.updatePunishment(con, punishment);
        } catch (SQLException ex ) {
            LOG.error(Messages.ERR_CANNOT_UPDATE_PUNISHMENT, ex);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_PUNISHMENT, ex);
        } finally {
            DBUtils.close(con);
        }

    }

    public Punishment findPunishmentByCompany(Company company) throws AppException {
        Connection con = null;
        Punishment punishment;
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(true);
            punishment = punishmentRep.findPunishmentById(con, company.getPunishmentId());
        } catch (SQLException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_PUNISHMENT, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_PUNISHMENT, ex);
        } finally {
            DBUtils.close(con);
        }
        return punishment;
    }
}
