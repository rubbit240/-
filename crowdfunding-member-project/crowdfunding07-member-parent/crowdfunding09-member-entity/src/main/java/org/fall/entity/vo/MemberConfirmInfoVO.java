package org.fall.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberConfirmInfoVO implements Serializable {
    private static final long serialVersionUID = -9014245872027794965L;

    //	易付宝账号
    private String paynum;

    //	法人身份证号
    private String cardnum;

}
