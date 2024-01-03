package com.hh.bidding.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.hh.bidding.mapper.BidNoticeMapper;
import com.hh.bidding.domain.BidNotice;
import com.hh.bidding.service.IBidNoticeService;

import javax.annotation.Resource;

/**
 * 招标公告Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
@Service
public class BidNoticeServiceImpl implements IBidNoticeService
{
    @Resource
    private BidNoticeMapper bidNoticeMapper;

    /**
     * 查询招标公告
     * 
     * @param uid 招标公告主键
     * @return 招标公告
     */
    @Override
    public BidNotice selectBidNoticeByUid(Long uid)
    {
        return bidNoticeMapper.selectBidNoticeByUid(uid);
    }

    /**
     * 查询招标公告列表
     * 
     * @param bidNotice 招标公告
     * @return 招标公告
     */
    @Override
    public List<BidNotice> selectBidNoticeList(BidNotice bidNotice)
    {
        return bidNoticeMapper.selectBidNoticeList(bidNotice);
    }

    /**
     * 新增招标公告
     * 
     * @param bidNotice 招标公告
     * @return 结果
     */
    @Override
    public int insertBidNotice(BidNotice bidNotice)
    {
        return bidNoticeMapper.insertBidNotice(bidNotice);
    }

    /**
     * 修改招标公告
     * 
     * @param bidNotice 招标公告
     * @return 结果
     */
    @Override
    public int updateBidNotice(BidNotice bidNotice)
    {
        return bidNoticeMapper.updateBidNotice(bidNotice);
    }

    /**
     * 批量删除招标公告
     * 
     * @param uids 需要删除的招标公告主键
     * @return 结果
     */
    @Override
    public int deleteBidNoticeByUids(Long[] uids)
    {
        return bidNoticeMapper.deleteBidNoticeByUids(uids);
    }



    /**
     * 删除招标公告信息
     * 
     * @param uid 招标公告主键
     * @return 结果
     */
    @Override
    public int deleteBidNoticeByUid(Long uid)
    {
        return bidNoticeMapper.deleteBidNoticeByUid(uid);
    }

    @Override
    public List<BidNotice> findAllByEndTime() {
        return bidNoticeMapper.findAllByEndTime();
    }

    @Override
    public List<BidNotice> findStatus(Long sid) {
        return bidNoticeMapper.findStatus(sid);
    }

    @Override
    public int deleteYfb(Long sid) {
        return bidNoticeMapper.deleteYfb(sid);
    }

    @Override
    public BidNotice findTwoInfo(Long uid) {
        return bidNoticeMapper.findTwoInfo(uid);
    }

    @Override
    public BidNotice selMax() {
        return bidNoticeMapper.selMax();
    }

    @Override
    public int updateStatus(Long sid) {
        return bidNoticeMapper.updateStatus(sid);
    }
}
