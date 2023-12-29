package com.hh.bidding.service;

import java.util.List;
import com.hh.bidding.domain.BidWinningResults;

/**
 * 中标结果公示Service接口
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
public interface IBidWinningResultsService 
{
    /**
     * 查询中标结果公示
     * 
     * @param gsId 中标结果公示主键
     * @return 中标结果公示
     */
    public BidWinningResults selectBidWinningResultsByGsId(Long gsId);

    /**
     * 查询中标结果公示列表
     * 
     * @param bidWinningResults 中标结果公示
     * @return 中标结果公示集合
     */
    public List<BidWinningResults> selectBidWinningResultsList(BidWinningResults bidWinningResults);

    /**
     * 新增中标结果公示
     * 
     * @param bidWinningResults 中标结果公示
     * @return 结果
     */
    public int insertBidWinningResults(BidWinningResults bidWinningResults);

    /**
     * 修改中标结果公示
     * 
     * @param bidWinningResults 中标结果公示
     * @return 结果
     */
    public int updateBidWinningResults(BidWinningResults bidWinningResults);

    /**
     * 批量删除中标结果公示
     * 
     * @param gsIds 需要删除的中标结果公示主键集合
     * @return 结果
     */
    public int deleteBidWinningResultsByGsIds(Long[] gsIds);

    /**
     * 删除中标结果公示信息
     * 
     * @param gsId 中标结果公示主键
     * @return 结果
     */
    public int deleteBidWinningResultsByGsId(Long gsId);

    public List<BidWinningResults> selectResultSupp(Long sid);

    public BidWinningResults selectResultAndCandidate(Long sid);
}
