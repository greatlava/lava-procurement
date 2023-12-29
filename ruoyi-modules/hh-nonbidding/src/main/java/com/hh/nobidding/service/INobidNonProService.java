package com.hh.nobidding.service;

import com.hh.nobidding.domain.ComPubAttachments;
import com.hh.nobidding.domain.ComQuotation;
import com.hh.nobidding.domain.NobidNonPro;

import java.util.List;

/**
 * 非招标项目Service接口
 *
 * @author ruoyi
 * @date 2023-12-11
 */
public interface INobidNonProService
{
    /**
     * 查询非招标项目
     *
     * @param gid 非招标项目主键
     * @return 非招标项目
     */
    public NobidNonPro selectNobidNonProByGid(Long gid);

    /**
     * 查询非招标项目列表
     *
     * @param nobidNonPro 非招标项目
     * @return 非招标项目集合
     */
    public List<NobidNonPro> selectNobidNonProList(NobidNonPro nobidNonPro);

    /**
     * 新增非招标项目
     *
     * @param nobidNonPro 非招标项目
     * @return 结果
     */
    public int insertNobidNonPro(NobidNonPro nobidNonPro);

    /**
     * 修改非招标项目
     *
     * @param nobidNonPro 非招标项目
     * @return 结果
     */
    public int updateNobidNonPro(NobidNonPro nobidNonPro);

    /**
     * 批量删除非招标项目
     *
     * @param gids 需要删除的非招标项目主键集合
     * @return 结果
     */
    public int deleteNobidNonProByGids(Long[] gids);

    /**
     * 删除非招标项目信息
     *
     * @param gid 非招标项目主键
     * @return 结果
     */
    public int deleteNobidNonProByGid(Long gid);

    //查询项目报价
    public List<ComQuotation> selectQuotation(String gfId);

    //查询公共附件
    public ComPubAttachments selectComPubAttachmentsByAid(Integer aid);

    //修改公共附件
    public int updateComPubAttachments(ComPubAttachments comPubAttachments);

    //查询可创建合同的非招标项目表
    public List<NobidNonPro> selectNobidNonProList1(NobidNonPro nobidNonPro);

    //新增公共附件
    public int insertComPubAttachments(ComPubAttachments comPubAttachments);

    //新增报价单
    public int insertComQuotation(ComQuotation comQuotation);

    //删除报价单
    public int deleteComQuotationByGfIds(String gfId);

    //查询是否存在
    public List<ComQuotation> selectComPubAttachmentsByGfId(String gfId);

    //修改非招标项目eid
    public int updateNobidNonProSid(NobidNonPro nobidNonPro);
}