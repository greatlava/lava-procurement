package com.hh.pms.sae.mapper;

import java.util.List;

import com.hh.pms.sae.domain.BsPersonnel;

/**
 * 核心技术人员Mapper接口
 *
 * @author ruoyi
 * @date 2023-11-19
 */
public interface BsPersonnelMapper {
    /**
     * 查询核心技术人员
     *
     * @param jsId 核心技术人员主键
     * @return 核心技术人员
     */
    public BsPersonnel selectBsPersonnelByJsId(Long jsId);

    /**
     * 查询核心技术人员列表
     *
     * @param bsPersonnel 核心技术人员
     * @return 核心技术人员集合
     */
    public List<BsPersonnel> selectBsPersonnelList(BsPersonnel bsPersonnel);

    /**
     * 新增核心技术人员
     *
     * @param bsPersonnel 核心技术人员
     * @return 结果
     */
    public int insertBsPersonnel(BsPersonnel bsPersonnel);

    /**
     * 修改核心技术人员
     *
     * @param bsPersonnel 核心技术人员
     * @return 结果
     */
    public int updateBsPersonnel(BsPersonnel bsPersonnel);

    /**
     * 删除核心技术人员
     *
     * @param jsId 核心技术人员主键
     * @return 结果
     */
    public int deleteBsPersonnelByJsId(Long jsId);

    /**
     * 批量删除核心技术人员
     *
     * @param jsIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBsPersonnelByJsIds(Long[] jsIds);
}
