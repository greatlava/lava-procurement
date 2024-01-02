package com.hh.pms.cm.service.impl;

import java.util.List;

import com.hh.pms.cm.domain.BidTender;
import com.hh.pms.cm.domain.BsContract;
import com.hh.pms.cm.domain.ComPubAttachments;
import com.hh.pms.sae.domain.NobidNonPro;
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
    public BsContract selectBidTenderBySid(Long eid) {
        return bsContractMapper.selectBidTenderBySid(eid);
    }

    //新增公共附件
    @Override
    public int insertComPubAttachments(ComPubAttachments comPubAttachments) {
        return bsContractMapper.insertComPubAttachments(comPubAttachments);
    }

    //修改公共附件
    @Override
    public int updateComPubAttachments(ComPubAttachments comPubAttachments) {
        return bsContractMapper.updateComPubAttachments(comPubAttachments);
    }

    //删除公共附件
    @Override
    public int deleteComPubAttamentsByEid(Integer eid) {
        return bsContractMapper.deleteComPubAttamentsByEid(eid);
    }

    //查询公共附件
    @Override
    public ComPubAttachments selectComPubAttachmentsByEid(Integer eid) {
        return bsContractMapper.selectComPubAttachmentsByEid(eid);
    }

    //修改合同管理状态
    @Override
    public int updateoHstatus(BsContract bsContract) {
        return bsContractMapper.updateoHstatus(bsContract);
    }

    //修改合同管理状态
    @Override
    public int updateBidTender(BidTender bidTender) {
        return bsContractMapper.updateBidTender(bidTender);
    }

    //合同作废
    @Override
    public int updateHtCancel(Long eid) {
        return bsContractMapper.updateHtCancel(eid);
    }

    //查询生成合同的项目
    @Override
    public Long selectSid(Long eid) {
        return bsContractMapper.selectSid(eid);
    }

    //修改非招标项目eid
    @Override
    public int updateNobidNonProSid(NobidNonPro nobidNonPro) {
        return bsContractMapper.updateNobidNonProSid(nobidNonPro);
    }

    //查询非招标签定中项目
    @Override
    public List<BsContract> selectBsContractList1(BsContract bsContract) {
        return bsContractMapper.selectBsContractList1(bsContract);
    }

    //查询非招标签定中项目
    @Override
    public List<BsContract> selectBsContractList2(BsContract bsContract) {
        return bsContractMapper.selectBsContractList2(bsContract);
    }

    //修改非招标Eid为空
    @Override
    public int updateNobidNonPro(NobidNonPro nobidNonPro) {
        return bsContractMapper.updateNobidNonPro(nobidNonPro);
    }
}
