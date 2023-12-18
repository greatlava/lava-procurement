package com.hh.bidding.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.hh.bidding.mapper.BidSubmissionMapper;
import com.hh.bidding.domain.BidSubmission;
import com.hh.bidding.service.IBidSubmissionService;

import javax.annotation.Resource;

/**
 * 投递标书Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
@Service
public class BidSubmissionServiceImpl implements IBidSubmissionService
{
    @Resource
    private BidSubmissionMapper bidSubmissionMapper;

    /**
     * 查询投递标书
     * 
     * @param tdId 投递标书主键
     * @return 投递标书
     */
    @Override
    public BidSubmission selectBidSubmissionByTdId(Long tdId)
    {
        return bidSubmissionMapper.selectBidSubmissionByTdId(tdId);
    }

    /**
     * 查询投递标书列表
     * 
     * @param bidSubmission 投递标书
     * @return 投递标书
     */
    @Override
    public List<BidSubmission> selectBidSubmissionList(BidSubmission bidSubmission)
    {
        return bidSubmissionMapper.selectBidSubmissionList(bidSubmission);
    }

    /**
     * 新增投递标书
     * 
     * @param bidSubmission 投递标书
     * @return 结果
     */
    @Override
    public int insertBidSubmission(BidSubmission bidSubmission)
    {
        return bidSubmissionMapper.insertBidSubmission(bidSubmission);
    }

    /**
     * 修改投递标书
     * 
     * @param bidSubmission 投递标书
     * @return 结果
     */
    @Override
    public int updateBidSubmission(BidSubmission bidSubmission)
    {
        return bidSubmissionMapper.updateBidSubmission(bidSubmission);
    }

    /**
     * 批量删除投递标书
     * 
     * @param tdIds 需要删除的投递标书主键
     * @return 结果
     */
    @Override
    public int deleteBidSubmissionByTdIds(Long[] tdIds)
    {
        return bidSubmissionMapper.deleteBidSubmissionByTdIds(tdIds);
    }

    /**
     * 删除投递标书信息
     * 
     * @param tdId 投递标书主键
     * @return 结果
     */
    @Override
    public int deleteBidSubmissionByTdId(Long tdId)
    {
        return bidSubmissionMapper.deleteBidSubmissionByTdId(tdId);
    }

    @Override
    public List<BidSubmission> findSubmission(Long sid) {
        return bidSubmissionMapper.findSubmission(sid);
    }
}
