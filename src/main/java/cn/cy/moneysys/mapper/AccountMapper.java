package cn.cy.moneysys.mapper;

import cn.cy.moneysys.entity.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface AccountMapper extends BaseMapper<Account> {
    public List<Account> getAccountsAndUsername(@Param("start") Long current, @Param("size") Long size);

    public List<Account> getAccountsByUsername(@Param("start") Long current, @Param("size") Long size, @Param("value") String value);

    public List<Account> getAccountsByTime(@Param("startTime") Date startTime, @Param("endTime") Date endTime,@Param("uid") String uid);

    public Long getCountByUid(@Param("value") String value);
}
