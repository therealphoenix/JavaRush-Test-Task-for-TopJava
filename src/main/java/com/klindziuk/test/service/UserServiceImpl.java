package com.klindziuk.test.service;


        import java.util.List;

        import javax.annotation.Resource;

        import com.klindziuk.test.model.User;
        import com.klindziuk.test.repository.UserRepository;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    @Transactional
    public User create(User user) {
        User createdUser = user;

        return userRepository.save(createdUser);
    }

    @Override
    @Transactional
    public User findById(int id) {
        return userRepository.findOne(id);
    }

    @Override
    @Transactional
    public User delete(int id) {
        User deletedUser = userRepository.findOne(id);

        userRepository.delete(deletedUser);

        return deletedUser;
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User update(User user) {
        User updatedUser = userRepository.findOne(user.getId());


        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setAge(user.getAge());
        updatedUser.setAdmin(user.getAdmin());
        updatedUser.setCreatedDate(user.getCreatedDate());

        return updatedUser;
    }

    @Override
    @Transactional
    public List<User> search(String s) {
        return userRepository.findByFirstnameOrLastname(s, s);
    }

}
