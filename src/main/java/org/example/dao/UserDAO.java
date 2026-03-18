package org.example.dao;

import org.example.model.User;
import org.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // REGISTER (Yeni kullanıcı pasif)
    public void register(User user){

        String sql = "INSERT INTO users(username,password,role,status) VALUES (?,?,?,?)";

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getRole());
            ps.setBoolean(4,false);

            ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // LOGIN (sadece aktif kullanıcı)
    public User login(String username,String password){

        String sql = "SELECT * FROM users WHERE username=? AND password=? AND status=true";

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1,username);
            ps.setString(2,password);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                User user = new User();

                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setRole(rs.getString("role"));
                user.setStatus(rs.getBoolean("status"));

                return user;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    // TÜM KULLANICILARI GETİR
    public List<User> getAllUsers(){

        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM users";

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){

            while(rs.next()){

                User user = new User();

                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setRole(rs.getString("role"));
                user.setStatus(rs.getBoolean("status"));

                users.add(user);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return users;
    }

    // USER AKTİF ET
    public void activateUser(int id){

        String sql = "UPDATE users SET status=true WHERE user_id=?";

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1,id);
            ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // USER PASİF ET
    public void deactivateUser(int id){

        String sql = "UPDATE users SET status=false WHERE user_id=?";

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1,id);
            ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // USER SİL
    public void deleteUser(int id){

        String sql = "DELETE FROM users WHERE user_id=?";

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1,id);
            ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}