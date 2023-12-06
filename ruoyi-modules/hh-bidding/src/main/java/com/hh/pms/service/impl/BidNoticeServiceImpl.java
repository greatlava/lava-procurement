package com.hh.pms.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hh.pms.mapper.BidNoticeMapper;
import com.hh.pms.domain.BidNotice;
import com.hh.pms.service.IBidNoticeService;

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
}
