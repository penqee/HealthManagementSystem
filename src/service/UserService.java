package service;

import model.User;
import repository.UserRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

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
    public ResultSet select(User user) {
        return userRepository.select(user);
    }

    public User ConvertToUser(ResultSet rs){
        try {
            if (rs.next()){
                String uid = rs.getString("user_no");
                String password = rs.getString("user_password");
                String username = rs.getString("user_name");
                String user_phone_number = rs.getString("user_phone_number");

                return new User(uid, password, username, user_phone_number);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}
