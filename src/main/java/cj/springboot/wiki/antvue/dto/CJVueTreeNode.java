package cj.springboot.wiki.antvue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class CJVueTreeNode {

    public CJVueTreeNode() {
        this.disabled = false;
        this.selectable = true;
        this.slots = new CJVueTreeSlot();
    }

    private String key;
    private String title;
    private List<CJVueTreeNode> children;
    private boolean disabled;
    private boolean selectable;
    private CJVueTreeSlot slots;
}
