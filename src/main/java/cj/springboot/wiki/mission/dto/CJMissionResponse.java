package cj.springboot.wiki.mission.dto;

import cj.springboot.wiki.mission.entity.CjMissionInfoEntity;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CJMissionResponse extends CjMissionInfoEntity {
    private List<CJMissionResponse> children;


}
