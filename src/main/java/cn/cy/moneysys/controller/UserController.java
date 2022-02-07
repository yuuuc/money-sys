package cn.cy.moneysys.controller;

import cn.cy.moneysys.entity.Msg;
import cn.cy.moneysys.entity.User;
import cn.cy.moneysys.service.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    /**
     * getUserListByPage
     * @param current
     * @param size
     * @return
     */
    @GetMapping("/getUsers")
    public Msg getUsers(@RequestParam(value = "current", required = false, defaultValue = "1") Long current,
                        @RequestParam(value = "size", required = false, defaultValue = "15") Long size){
        try{
            Page<User> page = new Page<>(current, size);
            Page<User> userPage = userService.selectUserList(page);
            return Msg.createMsg("ok").addData("userPage",userPage);
        }catch (Exception e){
            log.info("getUsers: "+ e.getMessage());
            return Msg.createMsg("ng");
        }
    }

    /**
     * getUsersBySearch
     * @param current
     * @param size
     * @param value
     * @return
     */
    @GetMapping("/getUsersBySearch")
    public Msg getUsersBySearch(@RequestParam(value = "current", required = false, defaultValue = "1") Long current,
                                @RequestParam(value = "size", required = false, defaultValue = "15") Long size,
                                @RequestParam(value = "value", required = true) String value){
        try{
            Page<User> page = new Page<>(current, size);
            Page<User> userPage = userService.selectUserByNameOrTel(page, value);
            return Msg.createMsg("ok").addData("userPage", userPage);
        }catch (Exception e){
            log.info("getUsersBySearch: "+ e.getMessage());
            return Msg.createMsg("ng");
        }
    }

    /**
     * updateUser
     * @param user
     * @return
     */
    @PostMapping("updateUser")
    public Msg updateUser(@RequestBody User user){
        try{
            userService.updateUser(user);
            return Msg.createMsg("ok");
        }catch (Exception e){
            log.info("updateUser: " + e.getMessage());
            return Msg.createMsg("ng");
        }
    }

    /**
     * deleteUser
     * @param user
     * @return
     */
    @PostMapping("deleteUser")
    public Msg deleteUser(@RequestBody User user){
        try{
            userService.deleteUser(user);
            return Msg.createMsg("ok");
        }catch (Exception e){
            log.info("deleteUser: " + e.getMessage());
            return Msg.createMsg("ng");
        }
    }

    /**
     * deleteUsers
     * @param ids
     * @return
     */
    @PostMapping("deleteUserList")
    public Msg deleteUsers(@RequestBody List<String> ids){
        try{
            userService.deleteUserList(ids);
            return Msg.createMsg("ok");
        }catch (Exception e){
            log.info("deleteUsers: " + e.getMessage());
            return Msg.createMsg("ng");
        }
    }



}
