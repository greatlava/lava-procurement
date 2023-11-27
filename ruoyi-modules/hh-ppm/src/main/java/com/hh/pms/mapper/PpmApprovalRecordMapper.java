package com.hh.pms.mapper;

import com.hh.pms.domain.PpmApprovalRecord;

import java.util.List;

/**
 * 审批记录Mapper接口
 * 
 * @author ruoyi
 * @date 2023-11-24
 */
public interface PpmApprovalRecordMapper 
{
    /**
     * 查询审批记录
     * 
     * @param rid 审批记录主键
     * @return 审批记录
     */
    public PpmApprovalRecord selectPpmApprovalRecordByRid(Integer rid);

    /**
     * 查询审批记录列表
     * 
     * @param ppmApprovalRecord 审批记录
     * @return 审批记录集合
     */
    public List<PpmApprovalRecord> selectPpmApprovalRecordList(PpmApprovalRecord ppmApprovalRecord);

    /**
     * 新增审批记录
     * 
     * @param ppmApprovalRecord 审批记录
     * @return 结果
     */
    public int insertPpmApprovalRecord(PpmApprovalRecord ppmApprovalRecord);

    /**
     * 修改审批记录
     * 
     * @param ppmApprovalRecord 审批记录
     * @return 结果
     */
    public int updatePpmApprovalRecord(PpmApprovalRecord ppmApprovalRecord);

    /**
     * 删除审批记录
     * 
     * @param rid 审批记录主键
     * @return 结果
     */
    public int deletePpmApprovalRecordByRid(Integer rid);

    /**
     * 批量删除审批记录
     * 
     * @param rids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePpmApprovalRecordByRids(Integer[] rids);
}
