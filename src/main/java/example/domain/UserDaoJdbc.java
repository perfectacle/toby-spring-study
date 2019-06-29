package example.domain;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Component
public class UserDaoJdbc implements UserDao {
    private JdbcTemplate jdbcTemplate;

    public UserDaoJdbc(final DataSource dataSourceBean) throws SQLException {
        this.jdbcTemplate = new JdbcTemplate(dataSourceBean);

        final Connection connection = dataSourceBean.getConnection();

        // 트랜잭선 시작
        // 하나의 SQL 문이 성공했다고 자동으로 커밋하는 옵션을 끈 것임.
        connection.setAutoCommit(false);

        try {
            // ...
            connection.commit(); // 트랜잭션 커밋
        } catch (final Exception e) {
            connection.rollback();
        } finally {
            connection.close(); // 커넥션 풀에 커넥션 객체 반환
        }
    }

    @Override
    public void add(final User user) {
        jdbcTemplate.update("insert into users(id, name, password, level, login, recommend, email) values (?, ?, ?, ?, ?, ?, ?)",
                user.getId(),
                user.getName(),
                user.getPassword(),
                user.getLevel().intValue(),
                user.getLogin(),
                user.getRecommend(),
                user.getEmail());
    }

    @Override
    public User get(final String id) {
        return jdbcTemplate.queryForObject("select * from users where id = ?",
                getUserRowMapper(),
                id);
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query("select * from users order by id",
                getUserRowMapper());
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update(con -> con.prepareStatement("delete from users"));
    }

    @Override
    public int getCount() {
        return jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
    }

    @Override
    public int update(final User user) {
        return jdbcTemplate.update("update users set name = ?, password = ?, level = ?, login = ?, recommend = ? where id = ?",
                user.getName(),
                user.getPassword(),
                user.getLevel().intValue(),
                user.getLogin(),
                user.getRecommend()
                , user.getId()
        );
    }

    private RowMapper<User> getUserRowMapper() {
        return (rs, rowNum) -> new User(rs.getString("id"),
                rs.getString("name"),
                rs.getString("password"),
                Level.valueOf(rs.getInt("level")),
                rs.getInt("login"),
                rs.getInt("recommend"),
                rs.getString("email"));
    }
}
