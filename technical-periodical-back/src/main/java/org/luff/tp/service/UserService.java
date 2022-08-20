package org.luff.tp.service;

import org.luff.tp.mapper.UserMapper;
import org.luff.tp.model.UserModel;
import org.luff.tp.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public UserModel login(String id, String password) {
        User user = userMapper.selectById(id);
        if (user == null) {
            return new UserModel("0", "none");
        } else if (!password.equals(user.getPassword())) {
            return new UserModel("0", "error");
        } else {
            return new UserModel(user.getId(), user.getName());
        }
    }

    public String register(String id, String name, String password) {
        System.out.println("id: " + id);
        System.out.println("name: " + name);
        System.out.println("password: " + password);
        if (id.length() != 11) return "wrong format";
        if (name.length() == 0) return "wrong name length";
        if (password.length() < 6) return "wrong password length";
        User user = userMapper.selectById(id);
        if (user != null) return "exist";
        userMapper.insert(new User(id, name, password));
        return "success";
    }

    public String changePassword(String id, String originalPassword, String newPassword) {
        User user = userMapper.selectById(id);
        if (!originalPassword.equals(user.getPassword())) return "error";
        userMapper.setPassword(id, newPassword);
        return "success";
    }

    public String changeName(String id, String name) {
        userMapper.setName(id, name);
        return "success";
    }
}
