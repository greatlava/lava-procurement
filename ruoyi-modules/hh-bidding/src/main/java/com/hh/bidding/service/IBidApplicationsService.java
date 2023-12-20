package com.hh.bidding.service;

import java.util.List;
import com.hh.bidding.domain.BidApplications;

/**
 * 抽取申请Service接口
 *
 * @author ruoyi
 * @date 2023-12-18
 */
public interface IBidApplicationsService
{
    /**
     * 查询抽取申请
     *
     * @param xid 抽取申请主键
     * @return 抽取申请
     */
    public BidApplications selectBidApplicationsByXid(Long xid);

    /**
     * 查询抽取申请列表
     *
     * @param bidApplications 抽取申请
     * @return 抽取申请集合
     */
    public List<BidApplications> selectBidApplicationsList(BidApplications bidApplications);

    /**
     * 新增抽取申请
     *
     * @param bidApplications 抽取申请
     * @return 结果
     */
    public int insertBidApplications(BidApplications bidApplications);

    /**
     * 修改抽取申请
     *
     * @param bidApplications 抽取申请
     * @return 结果
     */
    public int updateBidApplications(BidApplications bidApplications);

    /**
     * 批量删除抽取申请
     *
     * @param xids 需要删除的抽取申请主键集合
     * @return 结果
     */
    public int deleteBidApplicationsByXids(Long[] xids);

    /**
     * 删除抽取申请信息
     *
     * @param xid 抽取申请主键
     * @return 结果
     */
    public int deleteBidApplicationsByXid(Long xid);

    public BidApplications selectMaxApplications(Long sid);
}
