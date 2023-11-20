package com.hh.pms.sae.service;

import java.util.List;

import com.hh.pms.sae.domain.BsPersonnel;

/**
 * 核心技术人员Service接口
 *
 * @author ruoyi
 * @date 2023-11-19
 */
public interface IBsPersonnelService {
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
     * 批量删除核心技术人员
     *
     * @param jsIds 需要删除的核心技术人员主键集合
     * @return 结果
     */
    public int deleteBsPersonnelByJsIds(Long[] jsIds);

    /**
     * 删除核心技术人员信息
     *
     * @param jsId 核心技术人员主键
     * @return 结果
     */
    public int deleteBsPersonnelByJsId(Long jsId);
}
