package cn.cy.moneysys;

import cn.cy.moneysys.Exception.UsernameInDBException;
import cn.cy.moneysys.entity.Account;
import cn.cy.moneysys.entity.AccountsType;
import cn.cy.moneysys.entity.User;
import cn.cy.moneysys.mapper.AccountMapper;
import cn.cy.moneysys.mapper.AccountsTypeMapper;
import cn.cy.moneysys.mapper.UserMapper;
import cn.cy.moneysys.service.UserService;
import cn.cy.moneysys.utils.UUID;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class MoneySysApplicationTests {
  @Autowired
  private AccountMapper accountMapper;
  @Autowired
  private AccountsTypeMapper accountsTypeMapper;
  @Autowired
  private UserMapper userMapper;

  @Autowired
  private UserService userService;


  @Test
  void contextLoads() {
  }

  @Test
  /**
   * insert
   */
  void accountMapper(){
    Date date = new Date();
    Account account = new Account("1","1","1",2000.00D,0,0,date,"123");
    int insert = accountMapper.insert(account);
    System.out.println(insert);
  }

  @Test
  /**
   * delete
   */
  void accountDelete(){
    Account account = new Account();
    account.setAid("1");
    int delete = accountMapper.deleteById(account);
    System.out.println(delete);
  }

  @Test
  /**
   * update
   */
  void accountUpdate(){
    Date date = new Date();
    Account account = new Account("1","1","1",3000.00D,0,0,date,"1111111");
    int i = accountMapper.updateById(account);
    System.out.println(i);
  }

  @Test
  void userPageTest(){
    Page<User> userPage = new Page<>(1,2);
    Page<User> data = userMapper.selectPage(userPage,new QueryWrapper<User>().eq("name",'2').or().eq("tel",'3'));
    System.out.println(data.getRecords().size());
  }


  @Test
  void selectByPageTest(){
    Page<User> userPage = new Page<>(1,2);
    QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
    Page<User> userPage1 = userMapper.selectPage(userPage, userQueryWrapper);
    System.out.println(userPage1.getTotal());
    System.out.println(userPage1.getPages());
    System.out.println(userPage1.getRecords().size());
  }

  @Test
  void selectAccountTest(){
    accountMapper.selectList(new QueryWrapper<Account>().eq("uid","123").between("time",new Date(),new Date()));
  }

  @Test
  void userTest() throws UnsupportedEncodingException, NoSuchAlgorithmException, UsernameInDBException {
    Date date = new Date();
    User user = new User("1", "root", "yu", "root", "18502713475", date, 0, 0);
    userService.insertUser(user);
  }

  @Test
  // 计算两个日期中相隔的天数
  void DateComputed(){
    Date date1 = new Date(2021,10,19);
    Date date2 = new Date(2021,10,1);
    System.out.println((date1.getTime() - date2.getTime()) / 1000 / 3600 / 24);
  }

  @Test
  void md5Test() throws UnsupportedEncodingException, NoSuchAlgorithmException, UsernameInDBException {
    User user = new User();
    user.setUsername("root");
    user.setPassword("root");
    User user1 = userService.selectByNameAndPassword(user);
    System.out.println(user1);
  }

  @Test
  void UUIDTest(){
    String id = UUID.getId();
    System.out.println(id);
  }

  @Test
  void deleteBatchIds(){
    ArrayList<String> strings = new ArrayList<>();
    strings.add("2");
    strings.add("3");
    strings.add("4");
    userMapper.deleteBatchIds(strings);
  }

}
