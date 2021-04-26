package db.repository;

import db.utils.DBUtils;
import model.Punishment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PunishmentRep {
    private static final String SQL_CREATE_PUNISHMENT = "INSERT INTO punishment VALUES (DEFAULT, ?, ?)";
    private static final String SQL_FIND_PUNISHMENT_BY_ID = "SELECT * FROM punishment WHERE id=?";
    private static final String SQL_UPDATE_PUNISHMENT_BY_ID = "UPDATE punishment SET borderValue=?, description=? WHERE id=?";

    public Punishment insertPunishment(Connection con, Punishment punishment) throws SQLException {
        boolean res = false;

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = con.prepareStatement(SQL_CREATE_PUNISHMENT, Statement.RETURN_GENERATED_KEYS);

            int k = 1;
            pstmt.setInt(k++, punishment.getBorderValue());
            pstmt.setString(k, punishment.getDescription());
            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int carId = rs.getInt(1);
                    punishment.setId(carId);
                    res = true;
                }
            }
        } finally {
            DBUtils.close(rs);
            DBUtils.close(pstmt);
        }

        return punishment;
    }

    public Punishment findPunishmentById(Connection con, Integer id) throws SQLException {
        Punishment punishment = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = con.prepareStatement(SQL_FIND_PUNISHMENT_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                punishment = extractPunishment(rs);
            }
        } finally {
            DBUtils.close(rs);
            DBUtils.close(pstmt);
        }
        return punishment;
    }

    private Punishment extractPunishment(ResultSet rs) throws SQLException {
        Punishment punishment = new Punishment();
        punishment.setId(rs.getInt("id"));
        punishment.setBorderValue(rs.getInt("borderValue"));
        punishment.setDescription(rs.getString("description"));
        return punishment;
    }

    public boolean updatePunishment(Connection con, Punishment punishment) throws SQLException {
        boolean res = false;

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = con.prepareStatement(SQL_UPDATE_PUNISHMENT_BY_ID, Statement.RETURN_GENERATED_KEYS);

            int k = 1;
            pstmt.setInt(k++, punishment.getBorderValue());
            pstmt.setString(k++, punishment.getDescription());
            pstmt.setInt(k, punishment.getId());

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
