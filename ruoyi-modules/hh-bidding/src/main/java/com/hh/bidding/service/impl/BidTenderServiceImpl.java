package com.hh.bidding.service.impl;

import java.util.List;

import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.stereotype.Service;
import com.hh.bidding.mapper.BidTenderMapper;
import com.hh.bidding.domain.BidTender;
import com.hh.bidding.service.IBidTenderService;

import javax.annotation.Resource;

/**
 * 招标项目Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@Service
public class BidTenderServiceImpl implements IBidTenderService {
    @Resource
    private BidTenderMapper bidTenderMapper;

    /**
     * 查询招标项目
     *
     * @param sid 招标项目主键
     * @return 招标项目
     */
    @Override
    public BidTender selectBidTenderBySid(Long sid) {
        return bidTenderMapper.selectBidTenderBySid(sid);
    }

    /**
     * 查询招标项目列表
     *
     * @param bidTender 招标项目
     * @return 招标项目
     */
    @Override
    public List<BidTender> selectBidTenderList(BidTender bidTender) {
        return bidTenderMapper.selectBidTenderList(bidTender);
    }

    /**
     * 新增招标项目
     *
     * @param bidTender 招标项目
     * @return 结果
     */
    @Override
    public int insertBidTender(BidTender bidTender) {
        bidTender.setCreateTime(DateUtils.getNowDate());
        return bidTenderMapper.insertBidTender(bidTender);
    }

    /**
     * 修改招标项目
     *
     * @param bidTender 招标项目
     * @return 结果
     */
    @Override
    public int updateBidTender(BidTender bidTender) {
        bidTender.setUpdateTime(DateUtils.getNowDate());
        return bidTenderMapper.updateBidTender(bidTender);
    }

    /**
     * 批量删除招标项目
     *
     * @param sids 需要删除的招标项目主键
     * @return 结果
     */
    @Override
    public int deleteBidTenderBySids(Long[] sids) {
        return bidTenderMapper.deleteBidTenderBySids(sids);
    }

    /**
     * 删除招标项目信息
     *
     * @param sid 招标项目主键
     * @return 结果
     */
    @Override
    public int deleteBidTenderBySid(Long sid) {
        return bidTenderMapper.deleteBidTenderBySid(sid);
    }

    @Override
    public List<BidTender> selectNoEidTenderList(BidTender bidTender) {
        return bidTenderMapper.selectNoEidTenderList(bidTender);
    }

    @Override
    public BidTender findTenderAndNotice(Long sid) {
        return bidTenderMapper.findTenderAndNotice(sid);
    }
}
