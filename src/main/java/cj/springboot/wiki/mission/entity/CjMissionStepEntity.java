package cj.springboot.wiki.mission.entity;

import cj.springboot.wiki.config.mybatis.CJBaseColmns;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author cj
 * @since 2022-03-25
 */
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CjMissionStepEntity{" +
            "id=" + id +
            ", taskId=" + taskId +
            ", avatar=" + avatar +
            ", title=" + title +
            ", description=" + description +
        "}";
    }
}
