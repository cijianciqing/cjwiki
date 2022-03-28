package cj.springboot.wiki.mission.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

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
public class CJMissionSaveRequest {

    private static final long serialVersionUID = 1L;

    private String id;

    @NotBlank(message = "【mission.名称】不能为空")
    private String taskName;


    private String taskDesc;

    private String taskContent;

    private String taskPrincipal;

    @NotBlank(message = "【mission.重要性】不能为空")
    private String taskImportant;


    @NotBlank(message = "【mission.pid】不能为空")
    private String parentId;

    @NotBlank(message = "【mission.finishStatus】不能为空")
    private String finishStatus;

}
