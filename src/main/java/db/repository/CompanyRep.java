package db.repository;

import db.utils.DBUtils;
import model.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompanyRep {
    private static final String SQL_CREATE_COMPANY = "INSERT INTO company VALUES (DEFAULT, ?, ?, ?, ?)";
    private static final String SQL_FIND_COMPANIES_BY_USER_ID = "SELECT * FROM company WHERE users_id=?";
    private static final String SQL_FIND_ALL_COMPANIES = "SELECT * FROM company";
    private static final String SQL_FIND_COMPANY_BY_ID = "SELECT * FROM company WHERE id=?";
    private static final String SQL_UPDATE_COMPANY_BY_ID = "UPDATE company SET name=?, description=? WHERE id=?";
    private static final String SQL_DELETE_COMPANY = "DELETE FROM company WHERE id=?";

    public List<Company> findAllCompaniesByUserId(Connection con, Integer userId) throws SQLException {
        List<Company> companies = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement(SQL_FIND_COMPANIES_BY_USER_ID);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                companies.add(extractCompany(rs));
            }
        } finally {
            DBUtils.close(rs);
            DBUtils.close(stmt);
        }
        return companies;
    }

    public boolean deleteCompany(Connection con, int companyId) throws SQLException {
        PreparedStatement pstmt = null;

        try {
            pstmt = con.prepareStatement(SQL_DELETE_COMPANY);

            pstmt.setInt(1, companyId);

            return pstmt.executeUpdate() > 0;
        } finally {
            DBUtils.close(pstmt);
        }
    }

    public List<Company> findAllCompanies(Connection con) throws SQLException{
        List<Company> users = new ArrayList<>();

        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_FIND_ALL_COMPANIES);

            while (rs.next()) {
                users.add(extractCompany(rs));
            }
        } finally {
            DBUtils.close(rs);
            DBUtils.close(stmt);
        }
        return users;
    }

    private Company extractCompany(ResultSet rs) throws SQLException {
        Company company = new Company();
        company.setId(rs.getInt("id"));
        company.setName(rs.getString("name"));
        company.setDescription(rs.getString("description"));
        company.setPunishmentId(rs.getInt("punishment_id"));
        company.setUserId(rs.getInt("users_id"));
        return company;
    }

    public boolean insertCompany(Connection con, Company company) throws SQLException {
        boolean res = false;

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = con.prepareStatement(SQL_CREATE_COMPANY, Statement.RETURN_GENERATED_KEYS);

            int k = 1;
            pstmt.setString(k++, company.getName());
            pstmt.setString(k++, company.getDescription());
            pstmt.setInt(k++, company.getUserId());
            pstmt.setInt(k, company.getPunishmentId());
            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int carId = rs.getInt(1);
                    company.setId(carId);
                    res = true;
                }
            }
        } finally {
            DBUtils.close(rs);
            DBUtils.close(pstmt);
        }

        return res;
    }

    public Company findCompanyById(Connection con, Integer id) throws SQLException {
        Company company = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = con.prepareStatement(SQL_FIND_COMPANY_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                company = extractCompany(rs);
            }
        } finally {
            DBUtils.close(rs);
            DBUtils.close(pstmt);
        }
        return company;
    }

    public boolean updateCar(Connection con, Company company) throws SQLException {
        boolean res = false;

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = con.prepareStatement(SQL_UPDATE_COMPANY_BY_ID, Statement.RETURN_GENERATED_KEYS);

            int k = 1;
            pstmt.setString(k++, company.getName());
            pstmt.setString(k++, company.getDescription());
            pstmt.setInt(k, company.getId());

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
