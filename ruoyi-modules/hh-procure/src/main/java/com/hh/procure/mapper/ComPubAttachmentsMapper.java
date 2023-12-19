package com.hh.procure.mapper;

import com.hh.procure.domain.ComPubAttachments;

import java.util.List;

/**
 * 公共附件Mapper接口
 *
 * @author ruoyi
 * @date 2023-12-13
 */
public interface ComPubAttachmentsMapper
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
     * 删除公共附件
     *
     * @param anId 公共附件主键
     * @return 结果
     */
    public int deleteComPubAttachmentsByAnId(Integer anId);

    /**
     * 批量删除公共附件
     *
     * @param anIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteComPubAttachmentsByAnIds(Integer[] anIds);

    public ComPubAttachments selectedComPubAttamentsByAid(ComPubAttachments comPubAttachments);

    public int deleteComPubAttamentsByAid(Integer aid);

    public int updateComPubAttamentsByAid(ComPubAttachments comPubAttachments);


}
