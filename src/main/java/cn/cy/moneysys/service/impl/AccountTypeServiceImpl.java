package cn.cy.moneysys.service.impl;

import cn.cy.moneysys.entity.AccountsType;
import cn.cy.moneysys.mapper.AccountsTypeMapper;
import cn.cy.moneysys.service.AccountTypeService;
import cn.cy.moneysys.utils.UUID;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountTypeServiceImpl implements AccountTypeService {
    @Autowired
    private AccountsTypeMapper accountsTypeMapper;

    @Override
    public List<AccountsType> selectAll() {
        return accountsTypeMapper.selectList(new QueryWrapper<AccountsType>());
    }

    @Override
    public Page<AccountsType> selectAccountTypeByPage(Page accountTypePage) {
        Page tempPage = accountsTypeMapper.selectPage(accountTypePage, new QueryWrapper<AccountsType>());
        if (tempPage.getCurrent() > tempPage.getPages()){
            accountTypePage.setCurrent(tempPage.getPages());
            tempPage = accountsTypeMapper.selectPage(accountTypePage, new QueryWrapper<AccountsType>());
        }
        if (tempPage.getCurrent() <= 0) {
            accountTypePage.setCurrent(1);
            tempPage = accountsTypeMapper.selectPage(accountTypePage, new QueryWrapper<AccountsType>());
        }
        return tempPage;
    }

    @Override
    public void insetAccountType(AccountsType accountsType) {
        accountsType.setAid_t(UUID.getId());
        accountsTypeMapper.insert(accountsType);
    }

    @Override
    public void deleteAccountType(String id) {
        accountsTypeMapper.deleteById(id);
    }

    @Override
    public void deleteAccountTypeByList(List<String> ids) {
        accountsTypeMapper.deleteBatchIds(ids);
    }

    @Override
    public void updateAccountType(AccountsType accountsType) {
        accountsTypeMapper.updateById(accountsType);
    }

    @Override
    public AccountsType selectAccountTypeByTitle(String value) {

        return accountsTypeMapper.selectOne(new QueryWrapper<AccountsType>().eq("title",value));
    }
}
