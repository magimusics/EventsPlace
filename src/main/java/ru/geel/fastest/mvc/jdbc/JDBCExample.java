package ru.geel.fastest.mvc.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import ru.geel.fastest.mvc.bean.User;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * Created by ivangeel on 24.01.17.
 */
@Repository
public class JDBCExample {

    @Autowired
    DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init()
    {
        System.out.println("JDBCExample postConstruct is called. datasource = " + dataSource);
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public User mapRowUser(ResultSet resultSet)throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setFirstname(resultSet.getString("username"));
        user.setLastname(resultSet.getString("lusername"));
        user.setPassword(resultSet.getString("password"));
        user.setEnabled(resultSet.getBoolean("enabled"));
        user.setDescription(resultSet.getString("description"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        user.setImg(resultSet.getString("aphoto"));
        user.setCountry(resultSet.getString("country"));
        user.setCity(resultSet.getString("city"));
        user.setOccupation(resultSet.getString("occupation"));
        user.setBdate(resultSet.getDate("password"));
        return user;
    }

    public List<User> queryAllUsers(){
        System.out.println("JDBCExample: queryAllUsers() is called");
        final String QSQL = "select * from user order by id";
        List<User> userList = this.jdbcTemplate.query(QSQL, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = mapRowUser(resultSet);
                return user;
            }
        });
        return userList;
    }

    public User queryUser(String name){

        System.out.println("JDBCExample: queryUser() is called");
        final String QSQL = "select * from user where username='" + name + "'";
        User username = this.jdbcTemplate.queryForObject(QSQL, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = mapRowUser(resultSet);
                return user;
            }
        });
        return username;
    }

    public void addUser(String username, String lusername, String password, String description, boolean sex,
                        Date bdate, String email, String photo, String country, String city, String occupation){
        System.out.println("JDBCExample: addUser is called");
        final String INSERT_SQL = "insert into user (username, lusername, password, enabled, description, " +
                "bdate, email, aphoto, country, city, occupation) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, lusername);
                preparedStatement.setString(3, password);
                preparedStatement.setBoolean(4, true);
                preparedStatement.setString(5, description);
                preparedStatement.setDate(6, bdate);
                preparedStatement.setString(7, email);
                preparedStatement.setString(8, photo);
                preparedStatement.setString(9, country);
                preparedStatement.setString(10, city);
                preparedStatement.setString(11, occupation);
                return preparedStatement;
            }
        });
    }

    public void addUserAuthority(String username){
        System.out.println("JDBCExample: addAuthority is called");
        final String INSERT_SQL = "insert into authorities (username, authority) values (?, ?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, "ROLE_USER");
                return preparedStatement;
            }
        });
    }
}
