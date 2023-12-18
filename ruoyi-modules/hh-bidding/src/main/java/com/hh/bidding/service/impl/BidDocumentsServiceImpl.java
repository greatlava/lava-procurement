package com.hh.bidding.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.hh.bidding.mapper.BidDocumentsMapper;
import com.hh.bidding.domain.BidDocuments;
import com.hh.bidding.service.IBidDocumentsService;

import javax.annotation.Resource;

/**
 * 招标文件Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
@Service
public class BidDocumentsServiceImpl implements IBidDocumentsService
{
    @Resource
    private BidDocumentsMapper bidDocumentsMapper;

    /**
     * 查询招标文件
     * 
     * @param wid 招标文件主键
     * @return 招标文件
     */
    @Override
    public BidDocuments selectBidDocumentsByWid(Long wid)
    {
        return bidDocumentsMapper.selectBidDocumentsByWid(wid);
    }

    /**
     * 查询招标文件列表
     * 
     * @param bidDocuments 招标文件
     * @return 招标文件
     */
    @Override
    public List<BidDocuments> selectBidDocumentsList(BidDocuments bidDocuments)
    {
        return bidDocumentsMapper.selectBidDocumentsList(bidDocuments);
    }

    /**
     * 新增招标文件
     * 
     * @param bidDocuments 招标文件
     * @return 结果
     */
    @Override
    public int insertBidDocuments(BidDocuments bidDocuments)
    {
        return bidDocumentsMapper.insertBidDocuments(bidDocuments);
    }

    /**
     * 修改招标文件
     * 
     * @param bidDocuments 招标文件
     * @return 结果
     */
    @Override
    public int updateBidDocuments(BidDocuments bidDocuments)
    {
        return bidDocumentsMapper.updateBidDocuments(bidDocuments);
    }

    /**
     * 批量删除招标文件
     * 
     * @param wids 需要删除的招标文件主键
     * @return 结果
     */
    @Override
    public int deleteBidDocumentsByWids(Long[] wids)
    {
        return bidDocumentsMapper.deleteBidDocumentsByWids(wids);
    }

    /**
     * 删除招标文件信息
     * 
     * @param wid 招标文件主键
     * @return 结果
     */
    @Override
    public int deleteBidDocumentsByWid(Long wid)
    {
        return bidDocumentsMapper.deleteBidDocumentsByWid(wid);
    }

//    @Override
//    public List<BidDocuments> findTwoDocInfo(BidNotice bidNotice) {
//        return bidDocumentsMapper.findTwoDocInfo(bidNotice);
//    }
}
