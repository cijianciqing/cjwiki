package cj.springboot.wiki.article.dto;

import cj.springboot.wiki.config.mybatis.CJBaseColmns;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 
 * </p>
 *
 * @author cj
 * @since 2022-03-17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CjArticleSaveRequest  {

    private static final long serialVersionUID = 1L;

    private String id;

    private String articleDesc;

    private String content;

    @NotBlank(message = "【名称】不能为空")
    private String name;

    @NotBlank(message = "【分类】不能为空")
    private String categoryId;

    @NotBlank(message = "【状态】不能为空")
    private String articleState;


}
