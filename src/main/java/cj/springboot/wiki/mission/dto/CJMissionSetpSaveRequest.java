package cj.springboot.wiki.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

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
public class CJMissionSetpSaveRequest {

    private static final long serialVersionUID = 1L;

    private String id;

    @NotBlank(message = "【missionStep.标题】不能为空")
    private String title;


    private String avatar;

    private String description;

    @NotBlank(message = "【missionStep.mission】不能为空")
    private String taskId;

    private String stepContent;

}
