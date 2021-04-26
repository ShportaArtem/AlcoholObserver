package db.repository;

import db.utils.DBUtils;
import model.Owner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OwnerRep {
    private static final String SQL_CREATE_OWNER = "INSERT INTO owner VALUES (?, ?, ?)";
    private static final String SQL_UPDATE_OWNER_BY_ID = "UPDATE owner SET  countOfCompany=? WHERE users_id=?";
    private static final String SQL_FIND_OWNER_BY_ID = "SELECT * FROM owner WHERE users_id=?";

    private Owner extractOwner(ResultSet rs) throws SQLException {
        Owner owner = new Owner();
        owner.setUserId(rs.getInt("users_id"));
        owner.setCountOfCompany(rs.getInt("countOfCompany"));
        owner.setEmail(rs.getString("email"));

        return owner;
    }

    public boolean insertOwner(Connection con, Owner user) throws SQLException {
        boolean res = false;

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = con.prepareStatement(SQL_CREATE_OWNER, Statement.RETURN_GENERATED_KEYS);

            int k = 1;
            pstmt.setInt(k++, user.getCountOfCompany());
            pstmt.setInt(k++, user.getUserId());
            pstmt.setString(k++, user.getEmail());


            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    res = true;
                }
            }
        } finally {
            DBUtils.close(rs);
            DBUtils.close(pstmt);
        }

        return res;
    }

    public boolean updateOwner(Connection con, Owner owner) throws SQLException {
        boolean res = false;

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = con.prepareStatement(SQL_UPDATE_OWNER_BY_ID, Statement.RETURN_GENERATED_KEYS);

            int k = 1;
            pstmt.setInt(k++, owner.getCountOfCompany());
            pstmt.setInt(k, owner.getUserId());

            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    res = true;
                }
            }
        } finally {
            DBUtils.close(rs);
            DBUtils.close(pstmt);
        }

        return res;
    }

    public Owner findOwnerById(Connection con, Integer userId) throws SQLException {
        Owner owner = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = con.prepareStatement(SQL_FIND_OWNER_BY_ID);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                owner = extractOwner(rs);
            }
        } finally {
            DBUtils.close(rs);
            DBUtils.close(pstmt);
        }
        return owner;
    }
}
