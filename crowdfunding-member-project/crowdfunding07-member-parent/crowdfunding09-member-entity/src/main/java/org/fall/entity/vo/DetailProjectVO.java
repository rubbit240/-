package org.fall.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailProjectVO implements Serializable {
    private static final long serialVersionUID = 6656083460394509149L;

    private Integer projectId;
    private Integer memberId;
    private String projectName;
    private String projectDesc;
    private Integer followerCount;
    private Integer day;
    private Integer status;
    private String statusText;
    private Integer money;
    private Integer supportMoney;
    private Integer percentage;
    private String deployDate;
    private Integer lastDay;
    private Integer supporterCount;
    private String headerPicturePath;
    private List<String> detailPicturePathList;
    private List<DetailReturnVO> detailReturnVOList;
    private Integer freight;

}
