package com.hh.bidding.mapper;

import java.util.List;
import com.hh.bidding.domain.BidTender;

/**
 * 招标项目Mapper接口
 *
 * @author ruoyi
 * @date 2023-11-19
 */
public interface BidTenderMapper
{
    /**
     * 查询招标项目
     *
     * @param sid 招标项目主键
     * @return 招标项目
     */
    public BidTender selectBidTenderBySid(Long sid);

    /**
     * 查询招标项目列表
     *
     * @param bidTender 招标项目
     * @return 招标项目集合
     */
    public List<BidTender> selectBidTenderList(BidTender bidTender);

    /**
     * 新增招标项目
     *
     * @param bidTender 招标项目
     * @return 结果
     */
    public int insertBidTender(BidTender bidTender);

    /**
     * 修改招标项目
     *
     * @param bidTender 招标项目
     * @return 结果
     */
    public int updateBidTender(BidTender bidTender);

    /**
     * 删除招标项目
     *
     * @param sid 招标项目主键
     * @return 结果
     */
    public int deleteBidTenderBySid(Long sid);

    /**
     * 批量删除招标项目
     *
     * @param sids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBidTenderBySids(Long[] sids);

    //获取未签订合同的招标项目
    public List<BidTender> selectNoEidTenderList(BidTender bidTender);


    //项目 公告   两表
    public BidTender findTenderAndNotice(Long sid);
}
