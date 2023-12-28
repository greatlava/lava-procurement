package com.hh.bidding.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.hh.bidding.mapper.BidWinningResultsMapper;
import com.hh.bidding.domain.BidWinningResults;
import com.hh.bidding.service.IBidWinningResultsService;

import javax.annotation.Resource;

/**
 * 中标结果公示Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
@Service
public class BidWinningResultsServiceImpl implements IBidWinningResultsService 
{
    @Resource
    private BidWinningResultsMapper bidWinningResultsMapper;

    /**
     * 查询中标结果公示
     * 
     * @param gsId 中标结果公示主键
     * @return 中标结果公示
     */
    @Override
    public BidWinningResults selectBidWinningResultsByGsId(Long gsId)
    {
        return bidWinningResultsMapper.selectBidWinningResultsByGsId(gsId);
    }

    /**
     * 查询中标结果公示列表
     * 
     * @param bidWinningResults 中标结果公示
     * @return 中标结果公示
     */
    @Override
    public List<BidWinningResults> selectBidWinningResultsList(BidWinningResults bidWinningResults)
    {
        return bidWinningResultsMapper.selectBidWinningResultsList(bidWinningResults);
    }

    /**
     * 新增中标结果公示
     * 
     * @param bidWinningResults 中标结果公示
     * @return 结果
     */
    @Override
    public int insertBidWinningResults(BidWinningResults bidWinningResults)
    {
        return bidWinningResultsMapper.insertBidWinningResults(bidWinningResults);
    }

    /**
     * 修改中标结果公示
     * 
     * @param bidWinningResults 中标结果公示
     * @return 结果
     */
    @Override
    public int updateBidWinningResults(BidWinningResults bidWinningResults)
    {
        return bidWinningResultsMapper.updateBidWinningResults(bidWinningResults);
    }

    /**
     * 批量删除中标结果公示
     * 
     * @param gsIds 需要删除的中标结果公示主键
     * @return 结果
     */
    @Override
    public int deleteBidWinningResultsByGsIds(Long[] gsIds)
    {
        return bidWinningResultsMapper.deleteBidWinningResultsByGsIds(gsIds);
    }

    /**
     * 删除中标结果公示信息
     * 
     * @param gsId 中标结果公示主键
     * @return 结果
     */
    @Override
    public int deleteBidWinningResultsByGsId(Long gsId)
    {
        return bidWinningResultsMapper.deleteBidWinningResultsByGsId(gsId);
    }

    @Override
    public List<BidWinningResults> selectResultSupp(Long sid) {
        return bidWinningResultsMapper.selectResultSupp(sid);
    }

    @Override
    public BidWinningResults selectResultAndCandidate(Long sid) {
        return bidWinningResultsMapper.selectResultAndCandidate(sid);
    }
}
