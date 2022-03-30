package cj.springboot.wiki.mission.entity;

import cj.springboot.wiki.config.mybatis.CJBaseColmns;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author cj
 * @since 2022-03-25
 */
@TableName("cj_mission_info")
public class CjMissionInfoEntity extends CJBaseColmns {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @TableField("task_name")
    private String taskName;

    @TableField("task_desc")
    private String taskDesc;

    @TableField("task_content")
    private String taskContent;

    @TableField("task_principal")
    private String taskPrincipal;

    @TableField("task_important")
    private Integer taskImportant;

    @TableField("start_time")
    private LocalDateTime startTime;

    @TableField("end_time")
    private LocalDateTime endTime;

    @TableField("parent_id")
    private String parentId;

    @TableField("finish_status")
    private String finishStatus;

    {
        finishStatus = "进行中";
        parentId = "0";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public String getTaskPrincipal() {
        return taskPrincipal;
    }

    public void setTaskPrincipal(String taskPrincipal) {
        this.taskPrincipal = taskPrincipal;
    }

    public Integer getTaskImportant() {
        return taskImportant;
    }

    public void setTaskImportant(Integer taskImportant) {
        this.taskImportant = taskImportant;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getFinishStatus() {
        return finishStatus;
    }

    public void setFinishStatus(String finishStatus) {
        this.finishStatus = finishStatus;
    }

    @Override
    public String toString() {
        return "CjMissionInfoEntity{" +
                "id=" + id +
                ", taskName=" + taskName +
                ", taskDesc=" + taskDesc +
                ", taskContent=" + taskContent +
                ", taskPrincipal=" + taskPrincipal +
                ", taskImportant=" + taskImportant +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", parentId=" + parentId +
                ", finishStatus=" + finishStatus +
                "}";
    }
}
