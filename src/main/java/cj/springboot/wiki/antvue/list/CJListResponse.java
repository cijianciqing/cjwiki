package cj.springboot.wiki.antvue.list;

import cj.springboot.wiki.mission.entity.CjMissionStepEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CJListResponse {
    private String id;
    private String avatar;
    private String title;
    private String description;

}
