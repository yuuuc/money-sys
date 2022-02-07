package cn.cy.moneysys.service;

import cn.cy.moneysys.Exception.UsernameInDBException;
import cn.cy.moneysys.entity.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;


public interface UserService {
  User selectByNameAndPassword(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException, UsernameInDBException;

  Page<User> selectUserList(Page userPage);

  User selectUserById(String uid);

  Page<User> selectUserByNameOrTel(Page userPage, String value);

  void updateUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException, UsernameInDBException;

  void deleteUser(User user);

  void deleteUserList(List<String> ids);

  void insertUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException, UsernameInDBException;
}
