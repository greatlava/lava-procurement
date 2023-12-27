package com.hh.nobidding.mapper;

import com.hh.nobidding.domain.ComQuotation;
import com.hh.nobidding.domain.NobidNonPro;

import java.util.List;

/**
 * 非招标项目Mapper接口
 *
 * @author ruoyi
 * @date 2023-12-11
 */
public interface NobidNonProMapper
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
     * 删除非招标项目
     *
     * @param gid 非招标项目主键
     * @return 结果
     */
    public int deleteNobidNonProByGid(Long gid);

    /**
     * 批量删除非招标项目
     *
     * @param gids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNobidNonProByGids(Long[] gids);

    //查询项目报价
    public List<ComQuotation> selectQuotation(String gfId);
}