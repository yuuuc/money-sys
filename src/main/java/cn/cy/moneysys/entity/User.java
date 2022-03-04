package cn.cy.moneysys.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@TableName("user")
public class User {
  @TableId
  private String uid;
  @NotNull(message = "用户名不能为空")
  @TableField("username")
  private String username;
  @NotNull(message = "姓名不能为空")
  @TableField("name")
  private String name;
  @NotNull(message = "密码不能为空")
  @JSONField(serialize = false)
  @TableField("password")
  private String password;
  @NotNull(message = "手机号不能为空")
  @TableField("tel")
  private String tel;
  @TableField("create_time")
  private Date create_time;
  @TableField("isDel")
  private Integer isDel;
  @TableField("role")
  private Integer role; // 0 , 1
}
