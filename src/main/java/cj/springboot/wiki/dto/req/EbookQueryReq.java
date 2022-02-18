package cj.springboot.wiki.dto.req;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EbookQueryReq  {
    private Long id;

    private String name;

    private CJPageReq cjPageReq;
}
