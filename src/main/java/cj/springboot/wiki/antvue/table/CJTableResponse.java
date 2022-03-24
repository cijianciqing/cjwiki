package cj.springboot.wiki.antvue.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CJTableResponse<T> {

    private long total;

    private List<T> list;

}
