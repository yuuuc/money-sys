package cn.cy.moneysys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@TableName("accounts_type")
public class AccountsType implements Serializable {
  @TableId
  private String aid_t;
  @NotNull(message = "title can not null")
  @TableField("title")
  private String title;
  @NotNull(message = "description can not null")
  @TableField("description")
  private String description;
}
