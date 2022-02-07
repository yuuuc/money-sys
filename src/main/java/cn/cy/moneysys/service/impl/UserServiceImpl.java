package cn.cy.moneysys.service.impl;

import cn.cy.moneysys.Exception.UsernameInDBException;
import cn.cy.moneysys.entity.User;
import cn.cy.moneysys.mapper.UserMapper;
import cn.cy.moneysys.service.UserService;
import cn.cy.moneysys.utils.MD5;
import cn.cy.moneysys.utils.UUID;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper userMapper;

    @Override
    public User selectByNameAndPassword(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String encode = MD5.EncoderByMd5(user.getPassword());
        User dbUser = userMapper.selectOne(new QueryWrapper<User>()
                .eq("username", user.getUsername())
                .eq("password", encode));
        return dbUser;
    }

    @Override
  public Page<User> selectUserList(Page userPage) {
        Page page = userMapper.selectPage(userPage, new QueryWrapper<User>());
        return page;
  }

  @Override
  public User selectUserById(String uid) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("uid", uid));
  }

  @Override
  public Page<User> selectUserByNameOrTel(Page userPage, String value) {
        return userMapper.selectPage(userPage,new QueryWrapper<User>().eq("name",value).or().eq("tel",value));
  }

  @Override
  public void updateUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException, UsernameInDBException {
      String username = user.getUsername();
      User isUser = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
      if(isUser != null){
          throw new UsernameInDBException("database have " + username +" that you don't repeat");
      }
      String encoder = MD5.EncoderByMd5(user.getPassword());
      user.setPassword(encoder);
      userMapper.updateById(user);
  }

  @Override
  public void deleteUser(User user) {
        userMapper.deleteById(user);
  }

  @Override
  public void deleteUserList(List<String> ids) {
        userMapper.deleteBatchIds(ids);
  }

  @Override
  public void insertUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException, UsernameInDBException {
      String username = user.getUsername();
      User isUser = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
      if(isUser != null){
        throw new UsernameInDBException("database have " + username +" that you don't repeat");
      }
      user.setUid(UUID.getId());
        String encoder = MD5.EncoderByMd5(user.getPassword());
        user.setCreate_time(new Date());
        user.setRole(1);
        user.setIsDel(0);
        user.setPassword(encoder);
        userMapper.insert(user);
  }
}
