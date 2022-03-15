package cj.springboot.wiki.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CJUserResponse {
    private Long id;

    private String username;
    private String showname;
    private String token;
}
