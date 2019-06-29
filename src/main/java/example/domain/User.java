package example.domain;

import java.lang.reflect.Method;
import java.util.Objects;

public class User {
    private String id;
    private String name;
    private String password;
    private Level level;
    private int login;
    private int recommend;
    private String email;

    public static final int MIN_LOGIN_COUNT_FOR_SILVER = 50;
    public static final int MIN_RECOMMEND_COUNT_FOR_GOLD = 30;

    public User(final String id, final String name, final String password, final Level level, final int login, final int recommend, final String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.level = level;
        this.login = login;
        this.recommend = recommend;
        this.email = email;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(final Level level) {
        this.level = level;
    }

    public int getLogin() {
        return login;
    }

    public void setLogin(final int login) {
        this.login = login;
    }

    public int getRecommend() {
        return recommend;
    }

    public void setRecommend(final int recommend) {
        this.recommend = recommend;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final User user = (User) o;
        return login == user.login &&
                recommend == user.recommend &&
                Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(password, user.password) &&
                level == user.level &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, level, login, recommend, email);
    }

    public void upgrade() {
        if(this.getLevel().nextLevel() != null) {
            this.setLevel(this.getLevel().nextLevel());
        } else {
            throw new IllegalArgumentException(this.getLevel() + "은 더이상 업그레이드가 불가능합니다.");
        }
    }

    public boolean canUpgrade() {
        switch (this.getLevel()) {
            case BASIC: return this.getLogin() >= MIN_LOGIN_COUNT_FOR_SILVER;
            case SILVER: return this.getRecommend() >= MIN_RECOMMEND_COUNT_FOR_GOLD;
            case GOLD: return false;
            default: throw new IllegalArgumentException("Unknown level: " + this.getLevel());

            // default: return false; 로 처리해도 되나 새로운 레벨이 추가됐을 때 무조건 false로 처리하게 된다.
            // 에러가 나지 않으니 업그레이드 로직을 제대로 짰다고 착각할 수도 있으니 명시적으로 오류를 던져주게 끔 처리하는 게 좋다.
        }
    }
}
