package cn.cy.moneysys.mapper;

import cn.cy.moneysys.entity.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface AccountMapper extends BaseMapper<Account> {

}
