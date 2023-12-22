package com.hh.pms.sae.service;

import java.util.List;

import com.hh.pms.sae.domain.NobidSupNonPro;

/**
 * 供应商非招标项目Service接口
 *
 * @author ruoyi
 * @date 2023-12-20
 */
public interface INobidSupNonProService {
    /**
     * 查询供应商非招标项目
     *
     * @param gfId 供应商非招标项目主键
     * @return 供应商非招标项目
     */
    public NobidSupNonPro selectNobidSupNonProByGfId(String gfId);

    /**
     * 查询供应商非招标项目列表
     *
     * @param nobidSupNonPro 供应商非招标项目
     * @return 供应商非招标项目集合
     */
    public List<NobidSupNonPro> selectNobidSupNonProList(NobidSupNonPro nobidSupNonPro);

    /**
     * 新增供应商非招标项目
     *
     * @param nobidSupNonPro 供应商非招标项目
     * @return 结果
     */
    public int insertNobidSupNonPro(NobidSupNonPro nobidSupNonPro);

    /**
     * 修改供应商非招标项目
     *
     * @param nobidSupNonPro 供应商非招标项目
     * @return 结果
     */
    public int updateNobidSupNonPro(NobidSupNonPro nobidSupNonPro);

    /**
     * 批量删除供应商非招标项目
     *
     * @param gfIds 需要删除的供应商非招标项目主键集合
     * @return 结果
     */
    public int deleteNobidSupNonProByGfIds(String[] gfIds);

    /**
     * 删除供应商非招标项目信息
     *
     * @param gfId 供应商非招标项目主键
     * @return 结果
     */
    public int deleteNobidSupNonProByGfId(String gfId);

    public NobidSupNonPro checkIsJoin(Long hid, String gf_id);
}

