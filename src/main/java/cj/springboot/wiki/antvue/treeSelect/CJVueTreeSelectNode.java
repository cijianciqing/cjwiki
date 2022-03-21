package cj.springboot.wiki.antvue.treeSelect;

import cj.springboot.wiki.antvue.dto.CJVueTreeSlot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CJVueTreeSelectNode {

    private String value;
    private String key;
    private String title;
    private List<CJVueTreeSelectNode> children;

}
