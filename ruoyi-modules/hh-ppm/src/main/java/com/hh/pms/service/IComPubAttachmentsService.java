package com.hh.pms.service;

import java.util.List;
import com.hh.pms.domain.ComPubAttachments;
import com.hh.pms.domain.PpmProcurementPlan;

/**
 * 公共附件Service接口
 *
 * @author ruoyi
 * @date 2023-12-13
 */
public interface IComPubAttachmentsService
{
    /**
     * 查询公共附件
     *
     * @param anId 公共附件主键
     * @return 公共附件
     */
    public ComPubAttachments selectComPubAttachmentsByAnId(Integer anId);

    /**
     * 查询公共附件列表
     *
     * @param comPubAttachments 公共附件
     * @return 公共附件集合
     */
    public List<ComPubAttachments> selectComPubAttachmentsList(ComPubAttachments comPubAttachments);

    /**
     * 新增公共附件
     *
     * @param comPubAttachments 公共附件
     * @return 结果
     */
    public int insertComPubAttachments(ComPubAttachments comPubAttachments);

    /**
     * 修改公共附件
     *
     * @param comPubAttachments 公共附件
     * @return 结果
     */
    public int updateComPubAttachments(ComPubAttachments comPubAttachments);

    /**
     * 批量删除公共附件
     *
     * @param anIds 需要删除的公共附件主键集合
     * @return 结果
     */
    public int deleteComPubAttachmentsByAnIds(Integer[] anIds);

    /**
     * 删除公共附件信息
     *
     * @param anId 公共附件主键
     * @return 结果
     */
    public int deleteComPubAttachmentsByAnId(Integer anId);


    public ComPubAttachments selectedComPubAttamentsByAid(Integer aid);

    public int updateComPubAttamentsByAid(ComPubAttachments comPubAttachments);

    public int deleteComPubAttamentsByAid(Integer aid);

}
