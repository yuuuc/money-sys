package cn.cy.moneysys.controller;

import cn.cy.moneysys.entity.Account;
import cn.cy.moneysys.entity.Msg;
import cn.cy.moneysys.service.AccountService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Slf4j
@RequestMapping("/account")
@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    /**
     * select by page
     * @param current currentPage
     * @param size pageSize
     * @return Msg
     */
    @GetMapping("getAccounts")
    public Msg getAccountList(@RequestParam(value = "current", required = false, defaultValue = "1") Long current,
                              @RequestParam(value = "size", required = false, defaultValue = "15") Long size){
        try{
            Page<Account> page = new Page<>(current, size);
            Page<Account> accountPage = accountService.selectAccountList(page);
            return Msg.createMsg("ok").addData("accountPage",accountPage);
        }catch (Exception e){
            log.info("getAccountList: "+e.getMessage());
            return Msg.createMsg("ng");
        }
    }

    /**
     * getAccountListByUserId
     * @param current
     * @param size
     * @param value
     * @return
     */
    @GetMapping("getAccountsByUser")
    public Msg getAccountListByUserId(@RequestParam(value = "current", required = false, defaultValue = "1") Long current,
                                      @RequestParam(value = "size", required = false, defaultValue = "15") Long size,
                                      @RequestParam(value = "search", required = true) String value){
        try{
            Page<Account> page = new Page<>(current, size);
            Page<Account> accountPage = accountService.selectAccountListByUsername(page, value);
            return Msg.createMsg("ok").addData("accountPage",accountPage);
        }catch (Exception e){
            log.info("getAccountListByUserId: "+e.getMessage());
            return Msg.createMsg("ng");
        }
    }

    @GetMapping("getAccountsByTime")
    public Msg getAccountListByTime(@RequestParam(value = "startTime", required = false) String start,
                                    @RequestParam(value = "endTime", required = false) String end,
                                    @RequestParam(value = "uid", required = true) String uid) {
        try{

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startTime = simpleDateFormat.parse(start);
            Date endTime = simpleDateFormat.parse(end);
            List<Account> accounts = accountService.selectAccountListByUserIdAndTime(uid, startTime, endTime);
            return Msg.createMsg("ok").addData("accounts",accounts);
        }catch (Exception e){
            log.info("getAccountsByTime: " + e.getMessage());
            return Msg.createMsg("ng");
        }
    }

    @PostMapping("insertAccount")
    public Msg insertAccount(@RequestBody Account account){
        try{
            accountService.insetAccount(account);
            return Msg.createMsg("ok");
        }catch (Exception e){
            log.info("insertAccount: " + e.getMessage());
            return Msg.createMsg("ng");
        }
    }

    @PostMapping("deleteAccount")
    public Msg deleteAccount(@RequestBody Account account){
        try{
            accountService.deleteAccount(account.getAid());
            return Msg.createMsg("ok");
        }catch (Exception e){
            log.info("deleteAccount: " + e.getMessage());
            return Msg.createMsg("ng");
        }
    }

    @PostMapping("deleteAccountList")
    public Msg deleteAccountList(@RequestBody List<String> ids){
        try{
            accountService.deleteAccountByList(ids);
            return Msg.createMsg("ok");
        }catch (Exception e){
            log.info("deleteAccounts: " + e.getMessage());
            return Msg.createMsg("ng");
        }
    }

    @PostMapping("updateAccount")
    public Msg updateAccount(@RequestBody Account account){
        try{
            accountService.updateAccount(account);
            return Msg.createMsg("ok");
        }catch (Exception e){
            log.info("updateAccount: " + e.getMessage());
            return Msg.createMsg("ng");
        }
    }





}
