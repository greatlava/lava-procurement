package com.hh.bidding.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hh.bidding.mapper.BidApplicationsMapper;
import com.hh.bidding.domain.BidApplications;
import com.hh.bidding.service.IBidApplicationsService;

/**
 * 抽取申请Service业务层处理
 *
 * @author ruoyi
 * @date 2023-12-18
 */
@Service
public class BidApplicationsServiceImpl implements IBidApplicationsService
{
    @Autowired
    private BidApplicationsMapper bidApplicationsMapper;

    /**
     * 查询抽取申请
     *
     * @param xid 抽取申请主键
     * @return 抽取申请
     */
    @Override
    public BidApplications selectBidApplicationsByXid(Long xid)
    {
        return bidApplicationsMapper.selectBidApplicationsByXid(xid);
    }

    /**
     * 查询抽取申请列表
     *
     * @param bidApplications 抽取申请
     * @return 抽取申请
     */
    @Override
    public List<BidApplications> selectBidApplicationsList(BidApplications bidApplications)
    {
        return bidApplicationsMapper.selectBidApplicationsList(bidApplications);
    }

    /**
     * 新增抽取申请
     *
     * @param bidApplications 抽取申请
     * @return 结果
     */
    @Override
    public int insertBidApplications(BidApplications bidApplications)
    {
        return bidApplicationsMapper.insertBidApplications(bidApplications);
    }

    /**
     * 修改抽取申请
     *
     * @param bidApplications 抽取申请
     * @return 结果
     */
    @Override
    public int updateBidApplications(BidApplications bidApplications)
    {
        return bidApplicationsMapper.updateBidApplications(bidApplications);
    }

    /**
     * 批量删除抽取申请
     *
     * @param xids 需要删除的抽取申请主键
     * @return 结果
     */
    @Override
    public int deleteBidApplicationsByXids(Long[] xids)
    {
        return bidApplicationsMapper.deleteBidApplicationsByXids(xids);
    }

    /**
     * 删除抽取申请信息
     *
     * @param xid 抽取申请主键
     * @return 结果
     */
    @Override
    public int deleteBidApplicationsByXid(Long xid)
    {
        return bidApplicationsMapper.deleteBidApplicationsByXid(xid);
    }

    @Override
    public BidApplications selectMaxApplications(Long sid) {
        return bidApplicationsMapper.selectMaxApplications(sid);
    }
}
