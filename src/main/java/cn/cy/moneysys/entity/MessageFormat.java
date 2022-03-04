package cn.cy.moneysys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("messageformat")
public class MessageFormat implements Serializable {
    @TableId
    private String cmid;
    @TableField("parentid")
    private Integer parentid;
    @NotNull(message = "标题不能为空!")
    @TableField("title")
    private String title;
    @NotNull(message = "自定义格式化文本不能为空!")
    @TableField("description")
    private String description;
    @TableField("isShow")
    private Integer isShow;

}
