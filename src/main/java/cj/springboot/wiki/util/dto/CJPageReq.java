package cj.springboot.wiki.util.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
@Getter
@Setter
@ToString
public class CJPageReq {
    @NotNull(message = "【页码】不能为空")
    public int page;

    @NotNull(message = "【每页条数】不能为空")
    @Max(value = 1000, message = "【每页条数】不能超过1000")
    public int size;


}
