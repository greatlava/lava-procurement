package com.hh.pms.cm.mapper;

import java.util.List;

import com.hh.pms.cm.domain.BidTender;
import com.hh.pms.cm.domain.BsContract;
import com.hh.pms.cm.domain.ComPubAttachments;
import com.hh.pms.sae.domain.NobidNonPro;

/**
 * 合同Mapper接口
 *
 * @author ruoyi
 * @date 2023-11-19
 */
public interface BsContractMapper {
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
     * 删除合同
     *
     * @param eid 合同主键
     * @return 结果
     */
    public int deleteBsContractByEid(Long eid);

    /**
     * 批量删除合同
     *
     * @param eids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBsContractByEids(Long[] eids);

    //修改招标项目eid
    public int updateBidTenderEid(BidTender bidTender);

    //查询招标项目ID
    public BsContract selectBidTenderBySid(Long eid);

    //新增公共附件
    public int insertComPubAttachments(ComPubAttachments comPubAttachments);

    //修改公共附件
    public int updateComPubAttachments(ComPubAttachments comPubAttachments);

    //删除公共附件
    public int deleteComPubAttamentsByEid(Integer eid);

    //查询公共附件
    public ComPubAttachments selectComPubAttachmentsByEid(Integer eid);

    //修改合同管理状态
    public int updateoHstatus(BsContract bsContract);

    //修改招标Eid为空
    public int updateBidTender(BidTender bidTender);

    //合同作废
    public int updateHtCancel(Long eid);

    //查询生成合同的项目
    public Long selectSid(Long eid);

    //修改非招标项目eid
    public int updateNobidNonProSid(NobidNonPro nobidNonPro);

    //查询非招标签定中项目
    public List<BsContract> selectBsContractList1(BsContract bsContract);

    //查询非招标签定中项目
    public List<BsContract> selectBsContractList2(BsContract bsContract);

    //修改非招标Eid为空
    public int updateNobidNonPro(NobidNonPro nobidNonPro);


}
