package com.hh.bidding.mapper;

import java.util.List;
import com.hh.bidding.domain.BidNotice;

/**
 * 招标公告Mapper接口
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
public interface BidNoticeMapper 
{
    /**
     * 查询招标公告
     * 
     * @param uid 招标公告主键
     * @return 招标公告
     */
    public BidNotice selectBidNoticeByUid(Long uid);

    /**
     * 查询招标公告列表
     * 
     * @param bidNotice 招标公告
     * @return 招标公告集合
     */
    public List<BidNotice> selectBidNoticeList(BidNotice bidNotice);

    /**
     * 新增招标公告
     * 
     * @param bidNotice 招标公告
     * @return 结果
     */
    public int insertBidNotice(BidNotice bidNotice);

    /**
     * 修改招标公告
     * 
     * @param bidNotice 招标公告
     * @return 结果
     */
    public int updateBidNotice(BidNotice bidNotice);

    /**
     * 删除招标公告
     * 
     * @param uid 招标公告主键
     * @return 结果
     */
    public int deleteBidNoticeByUid(Long uid);

    public int deleteYfb(Long sid);

    /**
     * 批量删除招标公告
     * 
     * @param uids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBidNoticeByUids(Long[] uids);

    public List<BidNotice> findAllByEndTime();

    public List<BidNotice> findStatus(Long sid);

    public BidNotice findTwoInfo(Long uid);

    public BidNotice selMax();

    public int updateStatus(Long sid);
}
