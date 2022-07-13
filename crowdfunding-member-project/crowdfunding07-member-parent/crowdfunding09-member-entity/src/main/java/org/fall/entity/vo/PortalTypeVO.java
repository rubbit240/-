package org.fall.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortalTypeVO implements Serializable {
    private static final long serialVersionUID = 1879389756275303099L;

    private Integer id;
    private String name;
    private String remark;
    private List<PortalProjectVO> portalProjectVOList;
}
