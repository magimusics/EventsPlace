package ru.geel.fastest.mvc.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import ru.geel.fastest.mvc.bean.SUser;
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
        user.setBdate(resultSet.getDate(8));
        user.setEmail(resultSet.getString("email"));
        user.setImg(resultSet.getString("aphoto"));
        user.setCountry(resultSet.getString("country"));
        user.setCity(resultSet.getString("city"));
        user.setOccupation(resultSet.getString("occupation"));
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

        //System.out.println("JDBCExample: queryUser() is called");
        final String QSQL = "select * from user where email='" + name + "'";
        User username = this.jdbcTemplate.queryForObject(QSQL, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = mapRowUser(resultSet);
                return user;
            }
        });
        return username;
    }

    public User queryUserdyId(int userId){

        System.out.println("JDBCExample: queryUser() is called");
        final String QSQL = "select * from user where id='" + userId + "'";
        User username = this.jdbcTemplate.queryForObject(QSQL, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = mapRowUser(resultSet);
                return user;
            }
        });
        return username;
    }

    public void addUser(User user){
        System.out.println("JDBCExample: addUser is called");
        final String INSERT_SQL = "insert into user (username, email, lusername, password, enabled, description, " +
                "bdate, aphoto, country, city, occupation) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL);
                preparedStatement.setString(1, user.getFirstname());
                preparedStatement.setString(2, user.getEmail());
                preparedStatement.setString(3, user.getLastname());
                preparedStatement.setString(4, user.getPassword());
                preparedStatement.setBoolean(5, true);
                preparedStatement.setString(6, user.getDescription());
                preparedStatement.setDate(7, user.getBdate());
                preparedStatement.setString(8, user.getImg());
                preparedStatement.setString(9, user.getCountry());
                preparedStatement.setString(10, user.getCity());
                preparedStatement.setString(11, user.getOccupation());
                return preparedStatement;
            }
        });
    }

    public void addUserAuthority(String username){
        System.out.println("JDBCExample: addAuthority is called");
        final String INSERT_SQL = "insert into authorities (email, authority) values (?, ?)";
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

    public void updatePhoto(String username, String path){
        System.out.println("JDBCExample: updatePhoto has been called");
        jdbcTemplate.execute("UPDATE user SET aphoto = '" + path + "' WHERE email = '" + username + "';");
    }

    public List<User> showAllParticipants(int eventId){
        String SQLQ = "select * from user, eventParticipants e where e.idUsername=user.id and e.idEvent="+eventId;
        List<User> userList = this.jdbcTemplate.query(SQLQ, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = mapRowUser(resultSet);
                return user;
            }
        });
        return userList;
    }

    public void updateSettings(SUser sUser, String email, Date bDate){
        System.out.println(String.format("UPDATE user SET username='%s', lastname='%s', country='%s', city='%s', occupation='%s', bdate='%s'," +
                        "description='%s' WHERE email='%s'", sUser.getName(), sUser.getLastname(), sUser.getCountry(), sUser.getCity(), sUser.getOccupation(),
                bDate, sUser.getDescription(), email));
        jdbcTemplate.execute(String.format("UPDATE user SET username='" + sUser.getName() + "', lusername='%s', country='%s', city='%s', occupation='%s', bdate='%s'," +
                "description='%s' WHERE email='%s'", sUser.getLastname(), sUser.getCountry(), sUser.getCity(), sUser.getOccupation(),
                bDate, sUser.getDescription(), email));
    }

    public List<User> queryUsers(String[] names){
        String QSQL = null;
        if(names.length==2) {
            QSQL = "select * from user where username like '" + names[0] + "' and lusername like '" + names[1] + "'";
        }
        if(names.length==1) {
            QSQL = "select * from user where username like '" + names[0] + "'";
        }
        List<User> userList = this.jdbcTemplate.query(QSQL, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = mapRowUser(resultSet);
                return user;
            }
        });
        return userList;
    }
}
