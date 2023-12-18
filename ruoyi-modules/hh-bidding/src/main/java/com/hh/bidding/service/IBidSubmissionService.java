package com.hh.bidding.service;

import java.util.List;
import com.hh.bidding.domain.BidSubmission;

/**
 * 投递标书Service接口
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
public interface IBidSubmissionService 
{
    /**
     * 查询投递标书
     * 
     * @param tdId 投递标书主键
     * @return 投递标书
     */
    public BidSubmission selectBidSubmissionByTdId(Long tdId);

    /**
     * 查询投递标书列表
     * 
     * @param bidSubmission 投递标书
     * @return 投递标书集合
     */
    public List<BidSubmission> selectBidSubmissionList(BidSubmission bidSubmission);

    /**
     * 新增投递标书
     * 
     * @param bidSubmission 投递标书
     * @return 结果
     */
    public int insertBidSubmission(BidSubmission bidSubmission);

    /**
     * 修改投递标书
     * 
     * @param bidSubmission 投递标书
     * @return 结果
     */
    public int updateBidSubmission(BidSubmission bidSubmission);

    /**
     * 批量删除投递标书
     * 
     * @param tdIds 需要删除的投递标书主键集合
     * @return 结果
     */
    public int deleteBidSubmissionByTdIds(Long[] tdIds);

    /**
     * 删除投递标书信息
     * 
     * @param tdId 投递标书主键
     * @return 结果
     */
    public int deleteBidSubmissionByTdId(Long tdId);

    public List<BidSubmission> findSubmission(Long sid);
}
