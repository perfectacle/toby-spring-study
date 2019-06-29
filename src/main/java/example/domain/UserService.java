package example.domain;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserService {
    void add(final User user);
    void deleteAll();
    void update(final User user);

    @Transactional(readOnly = true)
    User get(final String id);

    @Transactional(readOnly = true)
    List<User> getAll();

    void upgradeLevels();
}
