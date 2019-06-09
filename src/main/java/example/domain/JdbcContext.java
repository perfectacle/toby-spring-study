package example.domain;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.stream.IntStream;

public class JdbcContext {
    private DataSource dataSourceBean;

    public JdbcContext(DataSource dataSourceBean) {
        this.dataSourceBean = dataSourceBean;
    }

    public void workWithStatementStrategy(StatementStrategy s) throws SQLException {
        try (Connection c = dataSourceBean.getConnection();
             PreparedStatement ps = s.makePreparedStatement(c)) {
            ps.executeUpdate();
        }
    }

    public void executeSql(final String sql, final String ...args) throws SQLException {
        workWithStatementStrategy(c -> {
            PreparedStatement ps = c.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) ps.setString(i+1, args[i]);

            return ps;
        });
    }
}
