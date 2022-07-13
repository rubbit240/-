package org.fall.handler;

import org.fall.constant.CrowdConstant;
import org.fall.entity.po.MemberPO;
import org.fall.entity.vo.DetailProjectVO;
import org.fall.entity.vo.OrderProjectVO;
import org.fall.service.api.MemberService;
import org.fall.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberProviderHandler {

    @Autowired
    MemberService memberService;

    @RequestMapping("/get/member/by/login/acct/remote")
    public ResultEntity<MemberPO> getMemberPOByLoginAcctRemote(@RequestParam("loginacct") String loginacct) {
        try {
            MemberPO memberPO = memberService.getMemberPOByLoginAcct(loginacct);
            return ResultEntity.successWithData(memberPO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }

    @RequestMapping("/save/member/remote")
    public ResultEntity<String> saveMemberRemote(@RequestBody MemberPO memberPO) {
        try {
            memberService.saveMember(memberPO);
            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                return ResultEntity.failed(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
            }
            return ResultEntity.failed(e.getMessage());
        }
    }

    @RequestMapping("/get/member-project/by/member/id/remote")
    public ResultEntity<List<DetailProjectVO>> getMemberProjectByMemberIdRemote(@RequestParam("memberId") Integer memberId) {
        try {
            List<DetailProjectVO> memberProjectList = memberService.getMemberProjectByMemberId(memberId);
            return ResultEntity.successWithData(memberProjectList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }

    @RequestMapping("/get/pay-project/by/member/id/remote")
    public ResultEntity<List<OrderProjectVO>> getPayProjectByMemberIdRemote(@RequestParam("memberId") Integer memberId) {
        try {
            List<OrderProjectVO> payProjectList = memberService.getPayProjectByMemberId(memberId);
            return ResultEntity.successWithData(payProjectList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }

}
