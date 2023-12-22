package com.hh.pms.cm.service.impl;

import java.util.List;

import com.hh.pms.cm.domain.BidTender;
import com.hh.pms.cm.domain.BsContract;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.stereotype.Service;
import com.hh.pms.cm.mapper.BsContractMapper;
import com.hh.pms.cm.service.IBsContractService;

import javax.annotation.Resource;

/**
 * 合同Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@Service
public class BsContractServiceImpl implements IBsContractService {
    @Resource
    private BsContractMapper bsContractMapper;

    /**
     * 查询合同
     *
     * @param eid 合同主键
     * @return 合同
     */
    @Override
    public BsContract selectBsContractByEid(Long eid) {
        return bsContractMapper.selectBsContractByEid(eid);
    }

    /**
     * 查询合同列表
     *
     * @param bsContract 合同
     * @return 合同
     */
    @Override
    public List<BsContract> selectBsContractList(BsContract bsContract) {
        return bsContractMapper.selectBsContractList(bsContract);
    }

    /**
     * 新增合同
     *
     * @param bsContract 合同
     * @return 结果
     */
    @Override
    public int insertBsContract(BsContract bsContract) {
        bsContract.setCreateTime(DateUtils.getNowDate());
        return bsContractMapper.insertBsContract(bsContract);
    }

    /**
     * 修改合同
     *
     * @param bsContract 合同
     * @return 结果
     */
    @Override
    public int updateBsContract(BsContract bsContract) {
        bsContract.setUpdateTime(DateUtils.getNowDate());
        return bsContractMapper.updateBsContract(bsContract);
    }

    /**
     * 批量删除合同
     *
     * @param eids 需要删除的合同主键
     * @return 结果
     */
    @Override
    public int deleteBsContractByEids(Long[] eids) {
        return bsContractMapper.deleteBsContractByEids(eids);
    }

    /**
     * 删除合同信息
     *
     * @param eid 合同主键
     * @return 结果
     */
    @Override
    public int deleteBsContractByEid(Long eid) {
        return bsContractMapper.deleteBsContractByEid(eid);
    }

    //修改招标项目eid
    @Override
    public int updateBidTenderEid(BidTender bidTender) {
        return bsContractMapper.updateBidTenderEid(bidTender);
    }

    //查询招标项目ID
    @Override
    public BsContract selectBidTenderSid(Long eid) {
        return bsContractMapper.selectBidTenderBySid(eid);
    }
}
