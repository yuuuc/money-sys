package cn.cy.moneysys.controller;

import cn.cy.moneysys.entity.Msg;
import cn.cy.moneysys.entity.User;
import cn.cy.moneysys.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
public class CommonController {

    @Autowired
    UserService userService;

    /**
     * login
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Msg login(@RequestBody User user){
        try{
            User dbUser = userService.selectByNameAndPassword(user);
            if(dbUser == null){
                return Msg.createMsg("fail");
            }else{
                return Msg.createMsg("success").addData("uid",dbUser.getUid());
            }
        }catch (Exception e){
            log.info("login: " + e.getMessage());
            return Msg.createMsg("ng");
        }
    }

    /**
     * register
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Msg register(@RequestBody @Validated User user){
        try{
            userService.insertUser(user);
            return Msg.createMsg("ok");
        }catch (Exception e){
            log.info("register: "+ e.getMessage());
            return Msg.createMsg("ng");
        }
    }

}
