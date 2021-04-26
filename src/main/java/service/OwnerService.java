package service;

import db.DBManager;
import db.exception.AppException;
import db.exception.DBException;
import db.exception.Messages;
import db.repository.OwnerRep;
import db.utils.DBUtils;
import model.Owner;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class OwnerService {
    private static final Logger LOG = Logger.getLogger(OwnerService.class);
    private DBManager dbManager;
    private OwnerRep ownerRep;

    public OwnerService(DBManager dbManager, OwnerRep ownerRep) {
        this.dbManager = dbManager;
        this.ownerRep = ownerRep;
    }

    public void updateOwner(Owner owner) throws AppException {
        Connection con=null;
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(true);
            ownerRep.updateOwner(con, owner);
        } catch (SQLException ex ) {
            LOG.error(Messages.ERR_CANNOT_UPDATE_OWNER, ex);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_OWNER, ex);
        } finally {
            DBUtils.close(con);
        }

    }

    public Owner findOwnerById(Integer userId) throws AppException {
        Connection con=null;
        Owner owner;
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(true);
            owner = ownerRep.findOwnerById(con, userId);
        }catch (SQLException ex ) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_OWNER, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_OWNER, ex);
        }finally {
            DBUtils.close(con);
        }
        return owner;
    }
}
