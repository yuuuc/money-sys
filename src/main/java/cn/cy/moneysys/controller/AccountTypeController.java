package cn.cy.moneysys.controller;

import cn.cy.moneysys.entity.AccountsType;
import cn.cy.moneysys.entity.Msg;
import cn.cy.moneysys.service.AccountTypeService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/accountType")
@RestController
public class AccountTypeController {

    @Autowired
    private AccountTypeService accountTypeService;

    @GetMapping("getAccountTypeAll")
    public Msg getAll(){
        try{
            List<AccountsType> accountsTypes = accountTypeService.selectAll();
            return Msg.createMsg("ok").addData("accountsTypes",accountsTypes);
        }catch (Exception e){
            log.info("getAccountTypeAll: " + e.getMessage());
            return Msg.createMsg("ng");
        }
    }

    @GetMapping("getAccountTypes")
    public Msg getAccountTypes(@RequestParam(value = "current", required = false, defaultValue = "1") Long current,
                               @RequestParam(value = "size", required = false, defaultValue = "15") Long size){
        try{
            Page<AccountsType> page = new Page<>(current,size);
            Page<AccountsType> accountsTypePage = accountTypeService.selectAccountTypeByPage(page);
            return Msg.createMsg("ok").addData("accountsTypePage",accountsTypePage);
        }catch (Exception e){
            log.info("getAccountTypes: " + e.getMessage());
            return Msg.createMsg("ng");
        }
    }

    @PostMapping("insertAccountType")
    public Msg insertAccountType(@RequestBody @Validated AccountsType accountsType){
        try{
            accountTypeService.insetAccountType(accountsType);
            return Msg.createMsg("ok");
        }catch (Exception e){
            log.info("insertAccountType: " + e.getMessage());
            return Msg.createMsg("ng");
        }
    }

    @PostMapping("deleteAccountType")
    public Msg deleteAccountType(@RequestBody String aid){
        try {
            accountTypeService.deleteAccountType(aid);
            return Msg.createMsg("ok");
        }catch (Exception e){
            log.info("deleteAccountType: " + e.getMessage());
            return Msg.createMsg("ng");
        }
    }

    @PostMapping("deleteAccountTypeList")
    public Msg deleteAccountTypeList(@RequestBody List<String> ids){
        try{
            accountTypeService.deleteAccountTypeByList(ids);
            return Msg.createMsg("ok");
        }catch (Exception e){
            return Msg.createMsg("ng");
        }
    }

    @PostMapping("updateAccountType")
    public Msg updateAccountType(@RequestBody @Validated AccountsType accountsType){
        try{
            accountTypeService.updateAccountType(accountsType);
            return Msg.createMsg("ok");
        }catch (Exception e){
            return Msg.createMsg("ng");
        }
    }

}
