package db.repository;

import db.utils.DBUtils;
import model.Employee;
import model.Owner;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRep {
    private static final String SQL_FIND_EMPLOYEES_BY_COMPANY_ID = "SELECT * FROM employee WHERE company_id=?";
    private static final String SQL_CREATE_EMPLOYEE = "INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_FIND_EMPLOYEE_BY_ID = "SELECT * FROM employee WHERE users_id=?";
    private static final String SQL_UPDATE_EMPLOYEE_BY_ID = "UPDATE employee SET phone=?, email=?, address=? WHERE users_id=?";
    private static final String SQL_UPDATE_EMPLOYEE_BY_VERIFICATION = "UPDATE employee SET countOfViolation=?, fine=?, verification_id=? WHERE users_id=?";
    private static final String SQL_RESET_VIOLATION_EMPLOYEE_BY_ID = "UPDATE employee SET countOfViolation=?, fine=? WHERE users_id=?";


    private Employee extractEmployee(ResultSet rs) throws SQLException {
        Employee employee = new Employee();
        employee.setUserId(rs.getInt("users_id"));
        employee.setCountOfViolation(rs.getInt("countOfViolation"));
        employee.setFine(rs.getBoolean("fine"));
        employee.setPhone(rs.getString("phone"));
        employee.setEmail(rs.getString("email"));
        employee.setAddress(rs.getString("address"));
        employee.setCompanyId(rs.getInt("company_id"));
        employee.setVerificationId(rs.getInt("verification_id"));
        return employee;
    }

    public List<Employee> findAllEmployeesByCompanyId(Connection con, Integer companyId) throws SQLException {
        List<Employee> employees = new ArrayList<>();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement(SQL_FIND_EMPLOYEES_BY_COMPANY_ID);
            stmt.setInt(1, companyId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                employees.add(extractEmployee(rs));
            }
        } finally {
            DBUtils.close(rs);
            DBUtils.close(stmt);
        }
        return employees;
    }

    public boolean insertEmployee(Connection con, Employee user) throws SQLException {
        boolean res = false;

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = con.prepareStatement(SQL_CREATE_EMPLOYEE, Statement.RETURN_GENERATED_KEYS);

            int k = 1;
            pstmt.setInt(k++, user.getUserId());
            pstmt.setInt(k++, user.getCountOfViolation());
            pstmt.setBoolean(k++, user.isFine());
            pstmt.setString(k++, user.getPhone());
            pstmt.setString(k++, user.getEmail());
            pstmt.setString(k++, user.getAddress());
            pstmt.setInt(k++, user.getCompanyId());
            pstmt.setNull(k, Types.INTEGER);


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


    public Employee findEmployeeById(Connection con, int id) throws SQLException {
        Employee employee = null;
        PreparedStatement pstmt;
        ResultSet rs;
        pstmt = con.prepareStatement(SQL_FIND_EMPLOYEE_BY_ID);
        pstmt.setInt(1, id);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            employee = extractEmployee(rs);
        }
        return employee;
    }

    public boolean updateEmployeeById(Connection con, Employee employee) throws SQLException {
        boolean res = false;

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = con.prepareStatement(SQL_UPDATE_EMPLOYEE_BY_ID, Statement.RETURN_GENERATED_KEYS);

            int k = 1;
            pstmt.setString(k++, employee.getPhone());
            pstmt.setString(k++, employee.getEmail());
            pstmt.setString(k++, employee.getAddress());
            pstmt.setInt(k, employee.getUserId());

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

    public boolean updateEmployeeByVerification(Connection con, Employee employee) throws SQLException {
        boolean res = false;

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = con.prepareStatement(SQL_UPDATE_EMPLOYEE_BY_VERIFICATION, Statement.RETURN_GENERATED_KEYS);

            int k = 1;
            pstmt.setInt(k++, employee.getCountOfViolation());
            pstmt.setBoolean(k++, employee.isFine());
            if (employee.getVerificationId() != null) {
                pstmt.setInt(k++, employee.getVerificationId());
            } else {
                pstmt.setNull(k++, Types.INTEGER);
            }
            pstmt.setInt(k, employee.getUserId());

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

    public boolean resetEmployeeById(Connection con, Integer id) throws SQLException {
        boolean res = false;

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = con.prepareStatement(SQL_RESET_VIOLATION_EMPLOYEE_BY_ID, Statement.RETURN_GENERATED_KEYS);

            int k = 1;
            pstmt.setInt(k++, 0);
            pstmt.setBoolean(k++, false);
            pstmt.setInt(k, id);

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
