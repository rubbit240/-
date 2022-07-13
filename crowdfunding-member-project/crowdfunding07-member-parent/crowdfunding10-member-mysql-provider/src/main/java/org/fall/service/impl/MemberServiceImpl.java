package org.fall.service.impl;

import org.apache.commons.lang.time.DateUtils;
import org.fall.entity.po.MemberPO;
import org.fall.entity.po.MemberPOExample;
import org.fall.entity.vo.DetailProjectVO;
import org.fall.entity.vo.DetailReturnVO;
import org.fall.entity.vo.OrderProjectVO;
import org.fall.mapper.MemberPOMapper;
import org.fall.mapper.ProjectPOMapper;
import org.fall.service.api.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional(readOnly = true)
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired(required = false)
    private MemberPOMapper memberPOMapper;

    @Autowired(required = false)
    private ProjectPOMapper projectPOMapper;

    // 通过loginAcct获取与数据库对应的MemberPO对象

    /**
     * @see MemberService#getMemberPOByLoginAcct(String)
     */
    @Override
    public MemberPO getMemberPOByLoginAcct(String loginAcct) {

        MemberPOExample example = new MemberPOExample();

        MemberPOExample.Criteria criteria = example.createCriteria();

        criteria.andLoginAcctEqualTo(loginAcct);

        List<MemberPO> memberPOS = memberPOMapper.selectByExample(example);

        // 判断得到的List是否为空，为空则返回null，防止后面调用的时候触发空指针异常
        if (memberPOS == null || memberPOS.size() == 0) {
            return null;
        }

        // List非空，则返回第一个（因为LoginAcct是唯一的）
        MemberPO memberPO = memberPOS.get(0);
        return memberPO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void saveMember(MemberPO memberPO) {
        memberPOMapper.insertSelective(memberPO);
    }

    @Override
    public List<DetailProjectVO> getMemberProjectByMemberId(Integer memberId) throws ParseException {
        List<DetailProjectVO> detailProjectVOList = projectPOMapper.selectDetailProjectVOByMemberId(memberId);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // 获取当前日期
        Date currentDay = new Date();
        // 获取当前日期的时间戳
        long currentDayTime = currentDay.getTime();

        for (DetailProjectVO projectVO : detailProjectVOList) {
            Integer status = projectVO.getStatus();
            switch (status) {
                case 0:
                    projectVO.setStatusText("即将开始");
                    break;
                case 1:
                    projectVO.setStatusText("众筹中");
                    break;
                case 2:
                    projectVO.setStatusText("众筹成功");
                    break;
                case 3:
                    projectVO.setStatusText("众筹失败");
                    break;
                default:
                    break;
            }

            // 根据deployDate计算lastDay
            String deployDate = projectVO.getDeployDate();

            // 把众筹的日期转换为Date类型
            Date deployDay = format.parse(deployDate);

            // 获取众筹日期的时间戳
            long deployDayTime = deployDay.getTime();

            // 通过当前日期时间戳-众筹日期时间戳，得到已经去过的时间
            Integer passDay = (int) (currentDayTime - deployDayTime) / 1000 / 60 / 60 / 24;

            // 用设置的众筹天数-过去的时间 得到剩余时间
            int lastDay = projectVO.getDay() - passDay;

            // 给当前的DetailProjectVO对象设置剩余时间
            projectVO.setLastDay(lastDay);

            // 为空处理
            if (null == projectVO.getSupportMoney()) {
                projectVO.setSupportMoney(0);
            }
            List<DetailReturnVO> returnVOList = projectVO.getDetailReturnVOList();
            if (!CollectionUtils.isEmpty(returnVOList)) {
                int sumFreight = returnVOList.stream()
                        .mapToInt(DetailReturnVO::getFreight)
                        .sum();
                projectVO.setFreight(sumFreight);
            }
        }

        // 最后返回完善后的detailProjectVO对象
        return detailProjectVOList;
    }

    @Override
    public List<OrderProjectVO> getPayProjectByMemberId(Integer memberId) throws ParseException {
        List<OrderProjectVO> orderProjectVOList = projectPOMapper.selectOrderProjectVOByMemberId(memberId);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // 获取当前日期
        Date currentDay = new Date();
        // 获取当前日期的时间戳
        long currentDayTime = currentDay.getTime();

        for (OrderProjectVO orderProjectVO : orderProjectVOList) {
            // 项目状态
            Integer projectStatus = orderProjectVO.getProjectStatus();
            switch (projectStatus) {
                case 0:
                    orderProjectVO.setProjectStatusText("即将开始");
                    break;
                case 1:
                    orderProjectVO.setProjectStatusText("众筹中");
                    break;
                case 2:
                    orderProjectVO.setProjectStatusText("众筹成功");
                    break;
                case 3:
                    orderProjectVO.setProjectStatusText("众筹失败");
                    break;
                default:
                    break;
            }

            // 订单状态
            orderProjectVO.setOrderStatusText(StringUtils.isEmpty(orderProjectVO.getPayOrderNum()) ? "未支付" : "已支付");

            // 根据deployDate计算lastDay
            String deployDate = orderProjectVO.getDeployDate();

            // 金额进度
            Integer money = orderProjectVO.getMoney();
            Integer projectSupportPrice = orderProjectVO.getProjectSupportPrice();
            BigDecimal divide = new BigDecimal(projectSupportPrice * 100).divide(new BigDecimal(money), 0, RoundingMode.HALF_UP);
            int percentage = divide.intValue();
            orderProjectVO.setPercentage(percentage);

            // 把众筹的日期转换为Date类型
            Date deployDay = format.parse(deployDate);
            int day = null == orderProjectVO.getDay() ? 0 : orderProjectVO.getDay();
            Date endDate = DateUtils.addDays(deployDay, day);
            orderProjectVO.setDeployEndDate(format.format(endDate));

            // 为空处理
            if (null == orderProjectVO.getSupportPrice()) {
                orderProjectVO.setSupportPrice(0);
            }
        }

        // 最后返回完善后的detailProjectVO对象
        return orderProjectVOList;
    }


}
