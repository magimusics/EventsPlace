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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        user.setIdUser(resultSet.getInt("id"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setEnabled(resultSet.getBoolean("enabled"));
        user.setDescription(resultSet.getString("description"));
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

    public void addUser(String username, String password, String description){
        System.out.println("JDBCExample: addUser is called");
        final String INSERT_SQL = "insert into user (username, password, enabled, description) values (?, ?, ?, ?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setBoolean(3, true);
                preparedStatement.setString(4, description);
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
