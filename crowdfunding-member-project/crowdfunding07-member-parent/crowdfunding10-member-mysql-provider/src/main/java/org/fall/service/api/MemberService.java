package org.fall.service.api;

import org.fall.entity.po.MemberPO;
import org.fall.entity.vo.DetailProjectVO;
import org.fall.entity.vo.OrderProjectVO;

import java.text.ParseException;
import java.util.List;


public interface MemberService {

    /**
     * 根据账号获取会员信息
     *
     * @param loginAcct 账号
     * @return 会员信息po
     */
    MemberPO getMemberPOByLoginAcct(String loginAcct);

    void saveMember(MemberPO memberPO);

    /**
     * 根据会员id获取发起项目信息
     *
     * @param memberId 会员id
     * @return 项目信息
     */
    List<DetailProjectVO> getMemberProjectByMemberId(Integer memberId) throws ParseException;

    /**
     * 根据会员id获取支持项目信息
     *
     * @param memberId 会员id
     * @return 项目信息
     */
    List<OrderProjectVO> getPayProjectByMemberId(Integer memberId) throws ParseException;
}
