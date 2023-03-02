package dev.schulte.daos;

import dev.schulte.entities.Bruh;
import dev.schulte.util.ConnectionUtil;

import java.sql.*;
import java.util.List;

public class BruhDAOPostgres implements BruhDAO{

    @Override
    public Bruh createBruh(Bruh bruh) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "insert into bruh values (?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, bruh.getUserId());
            preparedStatement.setLong(2, bruh.getBruhMoment());
            preparedStatement.setLong(3, bruh.getTime());

            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();

            String generatedKey = rs.getString("user_code");
            bruh.setUserId(generatedKey);
            return bruh;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Bruh getBruhByUserId(String userId) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from bruh where user_code = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, userId);

            ResultSet rs = preparedStatement.executeQuery();
            rs.next();

            Bruh bruh = new Bruh();
            bruh.setUserId(rs.getString("user_code"));
            bruh.setBruhMoment(rs.getLong("bruh_moment"));
            bruh.setTime(rs.getLong("time"));

            return bruh;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Bruh> getAllBruhs() {
        return null;
    }

    @Override
    public Bruh updateBruh(Bruh bruh) {
        return null;
    }
}
