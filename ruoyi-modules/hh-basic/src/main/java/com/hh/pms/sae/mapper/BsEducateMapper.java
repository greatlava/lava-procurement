package com.hh.pms.sae.mapper;

import java.util.List;

import com.hh.pms.sae.domain.BsEducate;

/**
 * 教育Mapper接口
 *
 * @author ruoyi
 * @date 2023-11-19
 */
public interface BsEducateMapper {
    /**
     * 查询教育
     *
     * @param rid 教育主键
     * @return 教育
     */
    public BsEducate selectBsEducateByRid(Long rid);

    /**
     * 查询教育列表
     *
     * @param bsEducate 教育
     * @return 教育集合
     */
    public List<BsEducate> selectBsEducateList(BsEducate bsEducate);

    /**
     * 新增教育
     *
     * @param bsEducate 教育
     * @return 结果
     */
    public int insertBsEducate(BsEducate bsEducate);

    /**
     * 修改教育
     *
     * @param bsEducate 教育
     * @return 结果
     */
    public int updateBsEducate(BsEducate bsEducate);

    /**
     * 删除教育
     *
     * @param rid 教育主键
     * @return 结果
     */
    public int deleteBsEducateByRid(Long rid);

    /**
     * 批量删除教育
     *
     * @param rids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBsEducateByRids(Long[] rids);
}
