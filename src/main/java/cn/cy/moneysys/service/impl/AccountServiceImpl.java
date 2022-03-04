package cn.cy.moneysys.service.impl;

import cn.cy.moneysys.Exception.SelectAccountSizeMoreThan30Exception;
import cn.cy.moneysys.entity.Account;
import cn.cy.moneysys.mapper.AccountMapper;
import cn.cy.moneysys.service.AccountService;
import cn.cy.moneysys.utils.UUID;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Page<Account> selectAccountList(Page accountPage) {
        Long aLong = accountMapper.selectCount(new QueryWrapper<Account>());
        accountPage.setTotal(aLong);
        accountPage.setPages(aLong % accountPage.getSize() == 0 ? aLong / accountPage.getSize() : aLong / accountPage.getSize() + 1 );
        if (accountPage.getCurrent() > accountPage.getPages()){
            accountPage.setCurrent(accountPage.getPages());
        }
        if(accountPage.getCurrent() <= 0){
            accountPage.setCurrent(1);
        }
        List<Account> accountsAndUsername = accountMapper.getAccountsAndUsername((accountPage.getCurrent() - 1) * accountPage.getSize(), accountPage.getSize());
        accountPage.setRecords(accountsAndUsername);
        return accountPage;
    }

    @Override
    public Page<Account> selectAccountListByUserId(Page accountPage, String uid) {
        return accountMapper.selectPage(accountPage,new QueryWrapper<Account>().eq("uid",uid).orderByAsc("time"));
    }

    @Override
    public Page<Account> selectAccountListByUsername(Page accountPage, String value) {
        Long total = accountMapper.getCountByUid(value);
        accountPage.setTotal(total);
        accountPage.setPages(total % accountPage.getSize() == 0 ? total / accountPage.getSize() : total / accountPage.getSize() + 1 );
        if (accountPage.getCurrent() > accountPage.getPages()){
            accountPage.setCurrent(accountPage.getPages());
        }
        if(accountPage.getCurrent() <= 0){
            accountPage.setCurrent(1);
        }
        List<Account> accountsByUsername = accountMapper.getAccountsByUsername((accountPage.getCurrent() - 1) * accountPage.getSize(), accountPage.getSize(), value);
        accountPage.setRecords(accountsByUsername);
        return accountPage;
    }

    @Override
    public List<Account> selectAccountListByUserIdAndTime(String uid, Date startTime, Date endTime) throws SelectAccountSizeMoreThan30Exception {
        long time = startTime.getTime() - endTime.getTime();
        Long days = (time < 0 ? -time : time) / 1000 / 60 / 60 / 24;
        if(days > 30){
            throw new SelectAccountSizeMoreThan30Exception("select Account Size more than 30");
        }
        return accountMapper.getAccountsByTime(startTime,endTime,uid);
    }

    @Override
    public void insetAccount(Account account) {
        account.setAid(UUID.getId());
        Date date = new Date();
        account.setTime(date);
        accountMapper.insert(account);
    }

    @Override
    public void deleteAccount(String aid) {
        accountMapper.deleteById(aid);
    }

    @Override
    public void deleteAccountByList(List<String> ids) {
        accountMapper.deleteBatchIds(ids);
    }

    @Override
    public void updateAccount(Account account) {
        accountMapper.updateById(account);
    }
}
