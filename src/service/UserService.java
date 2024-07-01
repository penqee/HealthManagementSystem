package service;

import model.User;
import repository.UserRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    private UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }
    //增
    public boolean insert(User user) {
        return userRepository.insert(user);
    }
    //删
    public boolean delete(User user) {
        return userRepository.delete(user);
    }
    //改
    public boolean updateByNo(User user) {
        return userRepository.updateByNo(user);
    }
    //查
    public List<User> select(User user) {
        return userRepository.select(user);
    }

}
