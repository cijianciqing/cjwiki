package cj.springboot.wiki.antvue.table;

import cj.springboot.wiki.util.dto.CJPageReq;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CJTableQueryReq {

    private String categoryId;

    private String name;

    private CJPageReq cjPageReq;
}
