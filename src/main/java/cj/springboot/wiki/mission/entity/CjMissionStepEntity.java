package cj.springboot.wiki.mission.entity;

import cj.springboot.wiki.config.mybatis.CJBaseColmns;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author cj
 * @since 2022-03-25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("cj_mission_step")
public class CjMissionStepEntity extends CJBaseColmns {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @TableField("task_id")
    private String taskId;

    @TableField("avatar")
    private String avatar;

    @TableField("title")
    private String title;

    @TableField("description")
    private String description;

    @TableField("step_content")
    private String stepContent;


}
