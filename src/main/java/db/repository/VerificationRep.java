package db.repository;

import db.utils.DBUtils;
import model.Verification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VerificationRep {
    private static final String SQL_FIND_ALL_VERIFICATIONS_BY_USER_ID = "SELECT * FROM verification WHERE users_id=?";
    private static final String SQL_CREATE_VERIFICATION = "INSERT INTO verification VALUES (DEFAULT, ?, ?, ?, ?)";;
    private static final String SQL_FIND_VERIFICATION_BY_ID = "SELECT * FROM verification WHERE id=?";
    private static final String SQL_UPDATE_VERIFICATION_BY_ID = "UPDATE verification SET description=? WHERE id=?";
    public List<Verification> findAllVerificationsByUserId(Connection con, Integer userId) throws SQLException {
        List<Verification> verifications = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = con.prepareStatement(SQL_FIND_ALL_VERIFICATIONS_BY_USER_ID, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                verifications.add(extractVerification(rs));
            }
        } finally {
            DBUtils.close(rs);
            DBUtils.close(pstmt);
        }
        return verifications;
    }

    private Verification extractVerification(ResultSet rs) throws SQLException {
        Verification verification = new Verification();
        verification.setId(rs.getInt("id"));
        verification.setCheck(rs.getBoolean("check"));
        verification.setDate(rs.getTimestamp("date"));
        verification.setDescription(rs.getString("description"));
        verification.setUserId(rs.getInt("users_id"));
        return verification;
    }

    public Verification addVerification(Connection con, Verification verification) throws SQLException {
        boolean res = false;

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = con.prepareStatement(SQL_CREATE_VERIFICATION, Statement.RETURN_GENERATED_KEYS);

            int k = 1;
            pstmt.setBoolean(k++, verification.isCheck());
            pstmt.setTimestamp(k++, verification.getDate());
            pstmt.setString(k++, verification.getDescription());
            pstmt.setInt(k, verification.getUserId());

            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int userId = rs.getInt(1);
                    verification.setId(userId);
                }
            }
        } finally {
            DBUtils.close(rs);
            DBUtils.close(pstmt);
        }

        return verification;
    }

    public Verification findVerificationById(Connection con, Integer id) throws SQLException {
        Verification verification = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = con.prepareStatement(SQL_FIND_VERIFICATION_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                verification = extractVerification(rs);
            }
        } finally {
            DBUtils.close(rs);
            DBUtils.close(pstmt);
        }
        return verification;
    }

    public boolean insertExplanationForVerification(Connection con, Verification verification) throws SQLException {
        boolean res = false;

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = con.prepareStatement(SQL_UPDATE_VERIFICATION_BY_ID, Statement.RETURN_GENERATED_KEYS);

            int k = 1;
            pstmt.setString(k++, verification.getDescription());
            pstmt.setInt(k, verification.getId());

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
}
