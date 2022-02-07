package cn.cy.moneysys.service;

import cn.cy.moneysys.entity.AccountsType;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public interface AccountTypeService {
    List<AccountsType> selectAll();

    Page<AccountsType> selectAccountTypeByPage(Page accountTypePage);

    void insetAccountType(AccountsType accountsType);

    void deleteAccountType(String id);

    void deleteAccountTypeByList(List<String> ids);

    void updateAccountType(AccountsType accountsType);

}
