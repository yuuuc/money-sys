package cn.cy.moneysys.service;

import cn.cy.moneysys.Exception.SelectAccountSizeMoreThan30Exception;
import cn.cy.moneysys.entity.Account;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Date;
import java.util.List;

public interface AccountService {
    Page<Account> selectAccountList(Page accountPage);

    Page<Account> selectAccountListByUserId(Page accountPage, String uid);

    Page<Account> selectAccountListByUsername(Page accountPage, String value);

    List<Account> selectAccountListByUserIdAndTime(String uid, Date startTime, Date endTime) throws SelectAccountSizeMoreThan30Exception;

    void insetAccount(Account account);

    void deleteAccount(String aid);

    void deleteAccountByList(List<String> ids);

    void updateAccount(Account account);
}
