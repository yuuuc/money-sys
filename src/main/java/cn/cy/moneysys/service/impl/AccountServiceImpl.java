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
        Page page = accountMapper.selectPage(accountPage,new QueryWrapper<Account>());
        return page;
    }

    @Override
    public Page<Account> selectAccountListByUserId(Page accountPage, String uid) {
        return accountMapper.selectPage(accountPage,new QueryWrapper<Account>().eq("uid",uid).orderByAsc("time"));
    }

    @Override
    public List<Account> selectAccountListByUserIdAndTime(String uid, Date startTime, Date endTime) throws SelectAccountSizeMoreThan30Exception {
        long time = startTime.getTime() - endTime.getTime();
        Long days = (time < 0 ? -time : time) / 1000 / 60 / 60 / 24;
        if(days > 30){
            throw new SelectAccountSizeMoreThan30Exception("select Account Size more than 30");
        }
        return accountMapper.selectList(new QueryWrapper<Account>().eq("uid", uid).between("time", startTime, endTime).orderByAsc("time"));
    }

    @Override
    public void insetAccount(Account account) {
        account.setAid(UUID.getId());
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
