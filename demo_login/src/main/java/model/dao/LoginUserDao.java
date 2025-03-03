package model.dao;

import model.entity.LoginUser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginUserDao extends SuperDao {

    public LoginUser findByLoginId(String loginId) {
        LoginUser user = null;
        String sql = "SELECT * FROM login_user WHERE login_id = ?";
        
        try {
            connect();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, loginId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                user = new LoginUser(
                    rs.getInt("id"),
                    rs.getString("login_id"),
                    rs.getString("password")
                );
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return user;
    }
}
