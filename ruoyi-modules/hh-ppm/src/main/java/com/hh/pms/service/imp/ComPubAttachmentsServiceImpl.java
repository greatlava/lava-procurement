package com.hh.pms.service.imp;

import java.util.List;

import com.hh.pms.domain.PpmProcurementPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hh.pms.mapper.ComPubAttachmentsMapper;
import com.hh.pms.domain.ComPubAttachments;
import com.hh.pms.service.IComPubAttachmentsService;

/**
 * 公共附件Service业务层处理
 *
 * @author ruoyi
 * @date 2023-12-13
 */
@Service
public class ComPubAttachmentsServiceImpl implements IComPubAttachmentsService {
    @Autowired
    private ComPubAttachmentsMapper comPubAttachmentsMapper;

    /**
     * 查询公共附件
     *
     * @param anId 公共附件主键
     * @return 公共附件
     */
    @Override
    public ComPubAttachments selectComPubAttachmentsByAnId(Integer anId) {
        return comPubAttachmentsMapper.selectComPubAttachmentsByAnId(anId);
    }

    /**
     * 查询公共附件列表
     *
     * @param comPubAttachments 公共附件
     * @return 公共附件
     */
    @Override
    public List<ComPubAttachments> selectComPubAttachmentsList(ComPubAttachments comPubAttachments) {
        return comPubAttachmentsMapper.selectComPubAttachmentsList(comPubAttachments);
    }

    /**
     * 新增公共附件
     *
     * @param comPubAttachments 公共附件
     * @return 结果
     */
    @Override
    public int insertComPubAttachments(ComPubAttachments comPubAttachments) {
        return comPubAttachmentsMapper.insertComPubAttachments(comPubAttachments);
    }

    /**
     * 修改公共附件
     *
     * @param comPubAttachments 公共附件
     * @return 结果
     */
    @Override
    public int updateComPubAttachments(ComPubAttachments comPubAttachments) {
        return comPubAttachmentsMapper.updateComPubAttachments(comPubAttachments);
    }

    /**
     * 批量删除公共附件
     *
     * @param anIds 需要删除的公共附件主键
     * @return 结果
     */
    @Override
    public int deleteComPubAttachmentsByAnIds(Integer[] anIds) {
        return comPubAttachmentsMapper.deleteComPubAttachmentsByAnIds(anIds);
    }

    /**
     * 删除公共附件信息
     *
     * @param anId 公共附件主键
     * @return 结果
     */
    @Override
    public int deleteComPubAttachmentsByAnId(Integer anId) {
        return comPubAttachmentsMapper.deleteComPubAttachmentsByAnId(anId);
    }

    @Override
    public ComPubAttachments selectedComPubAttamentsByAid(Integer aid) {
        return comPubAttachmentsMapper.selectedComPubAttamentsByAid(aid);
    }

    @Override
    public int updateComPubAttamentsByAid(ComPubAttachments comPubAttachments) {
        return comPubAttachmentsMapper.updateComPubAttamentsByAid(comPubAttachments);
    }

    @Override
    public int deleteComPubAttamentsByAid(Integer aid) {
        return comPubAttachmentsMapper.deleteComPubAttamentsByAid(aid);
    }


}
