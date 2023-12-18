package com.hh.bidding.service;

import java.util.List;

import com.hh.bidding.domain.BidGetTender;

/**
 * 获取标书Service接口
 *
 * @author ruoyi
 * @date 2023-11-19
 */
public interface IBidGetTenderService {
    /**
     * 查询获取标书
     *
     * @param yid 获取标书主键
     * @return 获取标书
     */
    public BidGetTender selectBidGetTenderByYid(Long yid);

    /**
     * 查询获取标书列表
     *
     * @param bidGetTender 获取标书
     * @return 获取标书集合
     */
    public List<BidGetTender> selectBidGetTenderList(BidGetTender bidGetTender);

    /**
     * 新增获取标书
     *
     * @param bidGetTender 获取标书
     * @return 结果
     */
    public int insertBidGetTender(BidGetTender bidGetTender);

    /**
     * 修改获取标书
     *
     * @param bidGetTender 获取标书
     * @return 结果
     */
    public int updateBidGetTender(BidGetTender bidGetTender);

    /**
     * 批量删除获取标书
     *
     * @param yids 需要删除的获取标书主键集合
     * @return 结果
     */
    public int deleteBidGetTenderByYids(Long[] yids);

    /**
     * 删除获取标书信息
     *
     * @param yid 获取标书主键
     * @return 结果
     */
    public int deleteBidGetTenderByYid(Long yid);


    public List<BidGetTender> findOper(Long sid);

}
