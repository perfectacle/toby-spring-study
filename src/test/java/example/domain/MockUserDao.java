package example.domain;

import java.util.List;

public class MockUserDao implements UserDao {
    private List<User> users;

    public MockUserDao(final List<User> users) {
        this.users = users;
    }

    @Override
    public void add(final User user) {
        this.users.add(user);
    }

    @Override
    public User get(final String id) {
        return this.users.stream().filter(user -> id.equals(user.getId())).findFirst().get();
    }

    @Override
    public List<User> getAll() {
        return this.users;
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getCount() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int update(final User user) {
        return 1;
    }
}
