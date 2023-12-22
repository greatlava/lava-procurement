package com.hh.pms.cm.service;

import java.util.List;

import com.hh.pms.cm.domain.BidTender;
import com.hh.pms.cm.domain.BsContract;

/**
 * 合同Service接口
 *
 * @author ruoyi
 * @date 2023-11-19
 */
public interface IBsContractService {
    /**
     * 查询合同
     *
     * @param eid 合同主键
     * @return 合同
     */
    public BsContract selectBsContractByEid(Long eid);

    /**
     * 查询合同列表
     *
     * @param bsContract 合同
     * @return 合同集合
     */
    public List<BsContract> selectBsContractList(BsContract bsContract);

    /**
     * 新增合同
     *
     * @param bsContract 合同
     * @return 结果
     */
    public int insertBsContract(BsContract bsContract);

    /**
     * 修改合同
     *
     * @param bsContract 合同
     * @return 结果
     */
    public int updateBsContract(BsContract bsContract);

    /**
     * 批量删除合同
     *
     * @param eids 需要删除的合同主键集合
     * @return 结果
     */
    public int deleteBsContractByEids(Long[] eids);

    /**
     * 删除合同信息
     *
     * @param eid 合同主键
     * @return 结果
     */
    public int deleteBsContractByEid(Long eid);

     //修改招标项目eid
    public int updateBidTenderEid(BidTender bidTender);

    //查询招标项目ID
    public BsContract selectBidTenderSid(Long eid);
}
