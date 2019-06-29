//package example.domain;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Component;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//@Component
//public class UserDaoJdbc {
//    private JdbcTemplate jdbcTemplate;
//
//    public UserDaoJdbc() {}
//
//    @Autowired
//    public UserDaoJdbc(final DataSource dataSourceBean) {
//        this.jdbcTemplate = new JdbcTemplate(dataSourceBean);
//    }
//
//    public void setDataSourceBean(final DataSource dataSourceBean) {
//        this.jdbcTemplate = new JdbcTemplate(dataSourceBean);
//    }
//
//    public void add(final User user) {
//        jdbcTemplate.update("insert into users(id, name, password, level, login, recommend) values (?, ?, ?)",
//                user.getId(),
//                user.getName(),
//                user.getPassword(),
//                user.getLevel().intValue(),
//                user.getLogin(),
//                user.getRecommend());
////        jdbcContext.executeSql("insert into users(id, name, password) values (?, ?, ?)",
////                user.getId(),
////                user.getName(),
////                user.getPassword());
//    }
//
//    public User get(String userId) throws SQLException {
//        return jdbcTemplate.queryForObject("select * from users where id = ?",
//                getUserRowMapper(),
//                userId);
//
////        Connection c = dataSourceBean.getConnection();
////
////        PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
////        ps.setString(1, userId);
////
////        ResultSet rs = ps.executeQuery();
////
////        User user = null;
////        if(rs.next()) {
////            user = new User();
////            user.setId(rs.getString("id"));
////            user.setName(rs.getString("name"));
////            user.setPassword(rs.getString("password"));
////        }
////
////        rs.close();
////        ps.close();
////        c.close();
////
////        if(user == null) throw new EmptyResultDataAccessException(1);
////
////        return user;
//    }
//
//    private RowMapper<User> getUserRowMapper() {
//        return (rs, rowNum) -> new User(rs.getString("id"),
//                rs.getString("name"),
//                rs.getString("password"),
//                Level.valueOf(rs.getInt("level")),
//                rs.getInt("login"),
//                rs.getInt("recommend"));
//    }
//
//    public List<User> getAll() {
//        return jdbcTemplate.query("select * from users order by id",
//                getUserRowMapper());
////        return jdbcTemplate.query();
//    }
//
//    public void deleteAll() throws SQLException {
//        jdbcTemplate.update(con -> con.prepareStatement("delete from users"));
////        jdbcTemplate.update("delete from users");
////        jdbcContext.executeSql("delete from users");
////        jdbcContext.workWithStatementStrategy(new StatementStrategy() {
////            @Override
////            public PreparedStatement makePreparedStatement(final Connection c) throws SQLException {
////                return c.prepareStatement("delete from users");
////            }
////        });
//    }
//
//    public int getCount() {
//        assert jdbcTemplate != null;
//        return jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
//
////        try(Connection c = dataSourceBean.getConnection();
////            PreparedStatement ps = c.prepareStatement("select count(*) from users");
////            ResultSet rs = ps.executeQuery()) {
////            rs.next();
////            return rs.getInt(1);
////        } catch (final SQLException e) {
////            // do something
////            throw e;
////        }
//
////        Connection c = null;
////        PreparedStatement ps = null;
////        ResultSet rs = null;
////
////        try {
////            c = dataSourceBean.getConnection();
////            ps = c.prepareStatement("select count(*) from users");
////            rs = ps.executeQuery();
////
////            rs.next();
////
////            return rs.getInt(1);
////        } catch (final SQLException e) {
////            // do something
////            throw e;
////        } finally {
////            try {
////                if (rs != null) rs.close();
////            } catch (SQLException ignore) {}
////            try {
////                if (ps != null) ps.close();
////            } catch (SQLException ignore) {}
////            try {
////                if (c != null) c.close();
////            } catch (SQLException ignore) {}
////        }
//    }
//}
