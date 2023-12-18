package com.hh.bidding.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.hh.bidding.mapper.BidGetTenderMapper;
import com.hh.bidding.domain.BidGetTender;
import com.hh.bidding.service.IBidGetTenderService;

import javax.annotation.Resource;

/**
 * 获取标书Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
@Service
public class BidGetTenderServiceImpl implements IBidGetTenderService
{
    @Resource
    private BidGetTenderMapper bidGetTenderMapper;

    /**
     * 查询获取标书
     * 
     * @param yid 获取标书主键
     * @return 获取标书
     */
    @Override
    public BidGetTender selectBidGetTenderByYid(Long yid)
    {
        return bidGetTenderMapper.selectBidGetTenderByYid(yid);
    }

    /**
     * 查询获取标书列表
     * 
     * @param bidGetTender 获取标书
     * @return 获取标书
     */
    @Override
    public List<BidGetTender> selectBidGetTenderList(BidGetTender bidGetTender)
    {
        return bidGetTenderMapper.selectBidGetTenderList(bidGetTender);
    }

    /**
     * 新增获取标书
     * 
     * @param bidGetTender 获取标书
     * @return 结果
     */
    @Override
    public int insertBidGetTender(BidGetTender bidGetTender)
    {
        return bidGetTenderMapper.insertBidGetTender(bidGetTender);
    }

    /**
     * 修改获取标书
     * 
     * @param bidGetTender 获取标书
     * @return 结果
     */
    @Override
    public int updateBidGetTender(BidGetTender bidGetTender)
    {
        return bidGetTenderMapper.updateBidGetTender(bidGetTender);
    }

    /**
     * 批量删除获取标书
     * 
     * @param yids 需要删除的获取标书主键
     * @return 结果
     */
    @Override
    public int deleteBidGetTenderByYids(Long[] yids)
    {
        return bidGetTenderMapper.deleteBidGetTenderByYids(yids);
    }

    /**
     * 删除获取标书信息
     * 
     * @param yid 获取标书主键
     * @return 结果
     */
    @Override
    public int deleteBidGetTenderByYid(Long yid)
    {
        return bidGetTenderMapper.deleteBidGetTenderByYid(yid);
    }

    @Override
    public List<BidGetTender> findOper(Long sid) {
        return bidGetTenderMapper.findOper(sid);
    }

}
