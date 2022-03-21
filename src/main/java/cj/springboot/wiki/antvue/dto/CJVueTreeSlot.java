package cj.springboot.wiki.antvue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class CJVueTreeSlot {
    private  String title;
    private String icon;

    public CJVueTreeSlot() {
        this.icon = "cjCustom";
    }
}
