package com.hh.bidding.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.hh.bidding.mapper.BidCandidateMapper;
import com.hh.bidding.domain.BidCandidate;
import com.hh.bidding.service.IBidCandidateService;

import javax.annotation.Resource;

/**
 * 中标候选人Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
@Service
public class BidCandidateServiceImpl implements IBidCandidateService
{
    @Resource
    private BidCandidateMapper bidCandidateMapper;

    /**
     * 查询中标候选人
     * 
     * @param zid 中标候选人主键
     * @return 中标候选人
     */
    @Override
    public BidCandidate selectBidCandidateByZid(Long zid)
    {
        return bidCandidateMapper.selectBidCandidateByZid(zid);
    }

    /**
     * 查询中标候选人列表
     * 
     * @param bidCandidate 中标候选人
     * @return 中标候选人
     */
    @Override
    public List<BidCandidate> selectBidCandidateList(BidCandidate bidCandidate)
    {
        return bidCandidateMapper.selectBidCandidateList(bidCandidate);
    }

    /**
     * 新增中标候选人
     * 
     * @param bidCandidate 中标候选人
     * @return 结果
     */
    @Override
    public int insertBidCandidate(BidCandidate bidCandidate)
    {
        return bidCandidateMapper.insertBidCandidate(bidCandidate);
    }

    /**
     * 修改中标候选人
     * 
     * @param bidCandidate 中标候选人
     * @return 结果
     */
    @Override
    public int updateBidCandidate(BidCandidate bidCandidate)
    {
        return bidCandidateMapper.updateBidCandidate(bidCandidate);
    }

    /**
     * 批量删除中标候选人
     * 
     * @param zids 需要删除的中标候选人主键
     * @return 结果
     */
    @Override
    public int deleteBidCandidateByZids(Long[] zids)
    {
        return bidCandidateMapper.deleteBidCandidateByZids(zids);
    }

    /**
     * 删除中标候选人信息
     * 
     * @param zid 中标候选人主键
     * @return 结果
     */
    @Override
    public int deleteBidCandidateByZid(Long zid)
    {
        return bidCandidateMapper.deleteBidCandidateByZid(zid);
    }

    //查询中标供应商
    @Override
    public BidCandidate selectBidCandidateBySid(Long sid) {
        return bidCandidateMapper.selectBidCandidateBySid(sid);
    }

    @Override
    public List<BidCandidate> selectSuppCand(Long sid) {
        return bidCandidateMapper.selectSuppCand(sid);
    }
}
