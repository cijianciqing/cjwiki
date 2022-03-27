package cj.springboot.wiki.antvue.list;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CJListResponse {
    private String avatar;
    private String title;
    private String description;

    private LocalDateTime updateTime;
}
