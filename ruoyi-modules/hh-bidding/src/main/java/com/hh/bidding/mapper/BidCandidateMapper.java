package com.hh.bidding.mapper;

import java.util.List;

import com.hh.bidding.domain.BidCandidate;

/**
 * 中标候选人Mapper接口
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
public interface BidCandidateMapper 
{
    /**
     * 查询中标候选人
     * 
     * @param zid 中标候选人主键
     * @return 中标候选人
     */
    public BidCandidate selectBidCandidateByZid(Long zid);

    /**
     * 查询中标候选人列表
     * 
     * @param bidCandidate 中标候选人
     * @return 中标候选人集合
     */
    public List<BidCandidate> selectBidCandidateList(BidCandidate bidCandidate);

    /**
     * 新增中标候选人
     * 
     * @param bidCandidate 中标候选人
     * @return 结果
     */
    public int insertBidCandidate(BidCandidate bidCandidate);

    /**
     * 修改中标候选人
     * 
     * @param bidCandidate 中标候选人
     * @return 结果
     */
    public int updateBidCandidate(BidCandidate bidCandidate);

    /**
     * 删除中标候选人
     * 
     * @param zid 中标候选人主键
     * @return 结果
     */
    public int deleteBidCandidateByZid(Long zid);

    /**
     * 批量删除中标候选人
     * 
     * @param zids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBidCandidateByZids(Long[] zids);

    public List<BidCandidate> selectSuppCand(Long sid);

    //查询中标供应商
    public BidCandidate selectBidCandidateBySid(Long sid);
}
