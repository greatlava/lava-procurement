package com.hh.bidding.service;

import java.util.List;
import com.hh.bidding.domain.BidDocuments;

/**
 * 招标文件Service接口
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
public interface IBidDocumentsService 
{
    /**
     * 查询招标文件
     * 
     * @param wid 招标文件主键
     * @return 招标文件
     */
    public BidDocuments selectBidDocumentsByWid(Long wid);

    /**
     * 查询招标文件列表
     * 
     * @param bidDocuments 招标文件
     * @return 招标文件集合
     */
    public List<BidDocuments> selectBidDocumentsList(BidDocuments bidDocuments);

    /**
     * 新增招标文件
     * 
     * @param bidDocuments 招标文件
     * @return 结果
     */
    public int insertBidDocuments(BidDocuments bidDocuments);

    /**
     * 修改招标文件
     * 
     * @param bidDocuments 招标文件
     * @return 结果
     */
    public int updateBidDocuments(BidDocuments bidDocuments);

    /**
     * 批量删除招标文件
     * 
     * @param wids 需要删除的招标文件主键集合
     * @return 结果
     */
    public int deleteBidDocumentsByWids(Long[] wids);

    /**
     * 删除招标文件信息
     * 
     * @param wid 招标文件主键
     * @return 结果
     */
    public int deleteBidDocumentsByWid(Long wid);

//    public List<BidDocuments> findTwoDocInfo(BidNotice bidNotice);
}
