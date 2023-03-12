package dev.schulte.daos;

import dev.schulte.entities.Bruh;
import dev.schulte.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
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

            Bruh bruh = new Bruh();

            while(rs.next()) {
                String code = rs.getString("user_code");
                bruh.setUserId(code);
                long moment = rs.getLong("bruh_moment");
                bruh.setBruhMoment(moment);
                long time = rs.getLong("time");
                bruh.setTime(time);
            }

            return bruh;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Bruh> getAllBruhs() {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from bruh";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            List<Bruh> bruhList = new ArrayList<>();
            while(rs.next()){
                Bruh bruh = new Bruh();
                bruh.setUserId(rs.getString("user_code"));
                bruh.setBruhMoment(rs.getLong("bruh_moment"));
                bruh.setTime(rs.getLong("time"));
                bruhList.add(bruh);
            }
            return bruhList;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Bruh updateBruh(Bruh bruh) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "update bruh set bruh_moment = ?, time = ? where user_code = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setLong(1, bruh.getBruhMoment());
            preparedStatement.setLong(2, bruh.getTime());
            preparedStatement.setString(3, bruh.getUserId());

            preparedStatement.executeUpdate();
            return bruh;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
