package com.hh.pms.service.imp;

import java.util.List;

import com.hh.pms.domain.PpmApprovalRecord;
import com.hh.pms.mapper.PpmApprovalRecordMapper;
import com.hh.pms.service.IPpmApprovalRecordService;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 审批记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-11-24
 */
@Service
public class PpmApprovalRecordServiceImpl implements IPpmApprovalRecordService
{
    @Autowired
    private PpmApprovalRecordMapper ppmApprovalRecordMapper;

    /**
     * 查询审批记录
     * 
     * @param rid 审批记录主键
     * @return 审批记录
     */
    @Override
    public PpmApprovalRecord selectPpmApprovalRecordByRid(Integer rid)
    {
        return ppmApprovalRecordMapper.selectPpmApprovalRecordByRid(rid);
    }

    /**
     * 查询审批记录列表
     * 
     * @param ppmApprovalRecord 审批记录
     * @return 审批记录
     */
    @Override
    public List<PpmApprovalRecord> selectPpmApprovalRecordList(PpmApprovalRecord ppmApprovalRecord)
    {
        return ppmApprovalRecordMapper.selectPpmApprovalRecordList(ppmApprovalRecord);
    }

    /**
     * 新增审批记录
     * 
     * @param ppmApprovalRecord 审批记录
     * @return 结果
     */
    @Override
    public int insertPpmApprovalRecord(PpmApprovalRecord ppmApprovalRecord)
    {
        return ppmApprovalRecordMapper.insertPpmApprovalRecord(ppmApprovalRecord);
    }

    /**
     * 修改审批记录
     * 
     * @param ppmApprovalRecord 审批记录
     * @return 结果
     */
    @Override
    public int updatePpmApprovalRecord(PpmApprovalRecord ppmApprovalRecord)
    {
        ppmApprovalRecord.setUpdateTime(DateUtils.getNowDate());
        return ppmApprovalRecordMapper.updatePpmApprovalRecord(ppmApprovalRecord);
    }

    /**
     * 批量删除审批记录
     * 
     * @param rids 需要删除的审批记录主键
     * @return 结果
     */
    @Override
    public int deletePpmApprovalRecordByRids(Integer[] rids)
    {
        return ppmApprovalRecordMapper.deletePpmApprovalRecordByRids(rids);
    }

    /**
     * 删除审批记录信息
     * 
     * @param rid 审批记录主键
     * @return 结果
     */
    @Override
    public int deletePpmApprovalRecordByRid(Integer rid)
    {
        return ppmApprovalRecordMapper.deletePpmApprovalRecordByRid(rid);
    }
}
