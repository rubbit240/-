package org.fall.entity.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberVO implements Serializable {
    private static final long serialVersionUID = -664469424067121655L;

    private String loginAcct;

    private String userPswd;

    private String userName;

    private String email;

    private String phoneNum;

    private String code;

}
