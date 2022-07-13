package org.fall.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginMemberVO implements Serializable {
    private static final long serialVersionUID = -7924209240507627974L;

    private Integer id;
    private String userName;
    private String email;
}
