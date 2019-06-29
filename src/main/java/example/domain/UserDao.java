package example.domain;

import java.util.List;

public interface UserDao {
    void add(final User user);
    User get(final String id);
    List<User> getAll();
    void deleteAll();
    int getCount();
    int update(final User user);
}
