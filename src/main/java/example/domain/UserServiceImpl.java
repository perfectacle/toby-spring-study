package example.domain;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final MailSender mailSender;

    public UserServiceImpl(final UserDao userDaoJdbc,
                           final MailSender javaMailSender) {
        this.userDao = userDaoJdbc;
        this.mailSender = javaMailSender;
    }

    public void upgradeLevels() {
        final List<User> all = userDao.getAll();
        all.forEach(user -> {
            final boolean b = user.canUpgrade();
            if(b) upgrade(user);
        });

//        // 트랜잭션 동기화 관리자를 통해 동기화 작업을 초기화함.
//        TransactionSynchronizationManager.initSynchronization();
//
//        // DB 커넥션을 생성하고 트랜잭션을 시작함.
//        // DB 커넥션을 가져옴과 도잇에 저장소에 바인딩까지 해줌.
//        final Connection c = DataSourceUtils.getConnection(dataSource);
//        c.setAutoCommit(false);

//
//        userDao.getAll().forEach(user -> {
//            if(user.canUpgrade()) upgrade(user);
//        });

//        userDao.getAll().forEach(user -> {
//            boolean changed = false;
//
//            if(user.getLevel() == Level.BASIC && user.getLogin() >= 50) {
//                user.setLevel(Level.SILVER);
//                changed = true;
//            } else if(user.getLevel() == Level.SILVER && user.getRecommend() >= 30) {
//                user.setLevel(Level.GOLD);
//                changed = true;
//            }
//
//            if(changed) userDao.update(user);
//        });
    }

    protected void upgrade(final User user) {
        user.upgrade();
        userDao.update(user);
        sendUpgradeLevel(user);
    }

    private void sendUpgradeLevel(final User user) {
        final SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setFrom("useradmin@ksug.org");
        simpleMailMessage.setSubject("Upgrade 안내");
        simpleMailMessage.setText("사용자 님의 등급이 " + user.getLevel().name() + "로 업그레이드 되었습니다.");

        mailSender.send(simpleMailMessage);
    }

    public void add(final User user) {
        if(user.getLevel() == null) user.setLevel(Level.BASIC);
        userDao.add(user);
    }

    @Override
    public User get(final String id) {
        return userDao.get(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public void deleteAll() {
        userDao.deleteAll();
    }

    @Override
    public void update(final User user) {
        userDao.update(user);
    }
}
