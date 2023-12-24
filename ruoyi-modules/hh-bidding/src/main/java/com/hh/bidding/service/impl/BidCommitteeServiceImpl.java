package com.hh.bidding.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hh.bidding.mapper.BidCommitteeMapper;
import com.hh.bidding.domain.BidCommittee;
import com.hh.bidding.service.IBidCommitteeService;

/**
 * 评标委员会Service业务层处理
 *
 * @author ruoyi
 * @date 2023-12-18
 */
@Service
public class BidCommitteeServiceImpl implements IBidCommitteeService
{
    @Autowired
    private BidCommitteeMapper bidCommitteeMapper;

    /**
     * 查询评标委员会
     *
     * @param pbId 评标委员会主键
     * @return 评标委员会
     */
    @Override
    public BidCommittee selectBidCommitteeByPbId(Long pbId)
    {
        return bidCommitteeMapper.selectBidCommitteeByPbId(pbId);
    }

    /**
     * 查询评标委员会列表
     *
     * @param bidCommittee 评标委员会
     * @return 评标委员会
     */
    @Override
    public List<BidCommittee> selectBidCommitteeList(BidCommittee bidCommittee)
    {
        return bidCommitteeMapper.selectBidCommitteeList(bidCommittee);
    }

    /**
     * 新增评标委员会
     *
     * @param bidCommittee 评标委员会
     * @return 结果
     */
    @Override
    public int insertBidCommittee(BidCommittee bidCommittee)
    {
        return bidCommitteeMapper.insertBidCommittee(bidCommittee);
    }

    /**
     * 修改评标委员会
     *
     * @param bidCommittee 评标委员会
     * @return 结果
     */
    @Override
    public int updateBidCommittee(BidCommittee bidCommittee)
    {
        return bidCommitteeMapper.updateBidCommittee(bidCommittee);
    }

    /**
     * 批量删除评标委员会
     *
     * @param pbIds 需要删除的评标委员会主键
     * @return 结果
     */
    @Override
    public int deleteBidCommitteeByPbIds(Long[] pbIds)
    {
        return bidCommitteeMapper.deleteBidCommitteeByPbIds(pbIds);
    }

    /**
     * 删除评标委员会信息
     *
     * @param pbId 评标委员会主键
     * @return 结果
     */
    @Override
    public int deleteBidCommitteeByPbId(Long pbId)
    {
        return bidCommitteeMapper.deleteBidCommitteeByPbId(pbId);
    }

    @Override
    public int delComBySid(Long sid) {
        return bidCommitteeMapper.delComBySid(sid);
    }

    @Override
    public List<BidCommittee> findCommitteeAndExpert(Long sid) {
        return bidCommitteeMapper.findCommitteeAndExpert(sid);
    }
}
