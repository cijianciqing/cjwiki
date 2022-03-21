package cj.springboot.wiki.antvue.table;

import cj.springboot.wiki.dto.req.CJPageReq;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CJTableQueryReq {

    private String categoryId;

    private String name;

    private CJPageReq cjPageReq;
}
