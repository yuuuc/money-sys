package cn.cy.moneysys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("accounts")
@ToString
public class Account implements Serializable {
  @TableId
  private String aid;
  @NotNull(message = "uid不能为空")
  @TableField("uid")
  private String uid;
  @NotNull(message = "aid_t不能为空")
  @TableField("aid_t")
  private String aid_t;
  @NotNull(message = "金额不能为空")
  @TableField("money")
  private Double money;
  @NotNull(message = "收支不能为空")
  @TableField("isOut")
  private Integer isOut;
  @TableField("isDel")
  private Integer isDel;
  @TableField("time")
  private Date time;
  @TableField("description")
  private String description;
}
