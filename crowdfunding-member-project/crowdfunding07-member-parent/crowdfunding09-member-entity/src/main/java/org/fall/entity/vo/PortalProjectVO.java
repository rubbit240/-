package org.fall.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortalProjectVO implements Serializable {
    private static final long serialVersionUID = 5632296873263034840L;

    private Integer projectId;
    private String projectName;
    private String headerPicturePath;
    private Integer money;
    private String deployDate;
    private Integer percentage;
    private Integer supporter;
}
