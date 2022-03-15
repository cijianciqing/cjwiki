package cj.springboot.wiki.dto.response;

import cj.springboot.wiki.entity.CategoryEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryResponse extends CategoryEntity {
    private List<CategoryResponse> children;

    @Override
    public String toString() {
        return super.toString()
                +"CategoryResponse{" +
                "children=" + children +
                '}';
    }
}
