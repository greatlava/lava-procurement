package com.hh.pms.sae.service.impl;

import java.util.List;

import com.hh.pms.sae.domain.BsPersonnel;
import com.hh.pms.sae.service.IBsPersonnelService;
import org.springframework.stereotype.Service;
import com.hh.pms.sae.mapper.BsPersonnelMapper;

import javax.annotation.Resource;

/**
 * 核心技术人员Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@Service
public class BsPersonnelServiceImpl implements IBsPersonnelService {
    @Resource
    private BsPersonnelMapper bsPersonnelMapper;

    /**
     * 查询核心技术人员
     *
     * @param jsId 核心技术人员主键
     * @return 核心技术人员
     */
    @Override
    public BsPersonnel selectBsPersonnelByJsId(Long jsId) {
        return bsPersonnelMapper.selectBsPersonnelByJsId(jsId);
    }

    /**
     * 查询核心技术人员列表
     *
     * @param bsPersonnel 核心技术人员
     * @return 核心技术人员
     */
    @Override
    public List<BsPersonnel> selectBsPersonnelList(BsPersonnel bsPersonnel) {
        return bsPersonnelMapper.selectBsPersonnelList(bsPersonnel);
    }

    /**
     * 新增核心技术人员
     *
     * @param bsPersonnel 核心技术人员
     * @return 结果
     */
    @Override
    public int insertBsPersonnel(BsPersonnel bsPersonnel) {
        return bsPersonnelMapper.insertBsPersonnel(bsPersonnel);
    }

    /**
     * 修改核心技术人员
     *
     * @param bsPersonnel 核心技术人员
     * @return 结果
     */
    @Override
    public int updateBsPersonnel(BsPersonnel bsPersonnel) {
        return bsPersonnelMapper.updateBsPersonnel(bsPersonnel);
    }

    /**
     * 批量删除核心技术人员
     *
     * @param jsIds 需要删除的核心技术人员主键
     * @return 结果
     */
    @Override
    public int deleteBsPersonnelByJsIds(Long[] jsIds) {
        return bsPersonnelMapper.deleteBsPersonnelByJsIds(jsIds);
    }

    /**
     * 删除核心技术人员信息
     *
     * @param jsId 核心技术人员主键
     * @return 结果
     */
    @Override
    public int deleteBsPersonnelByJsId(Long jsId) {
        return bsPersonnelMapper.deleteBsPersonnelByJsId(jsId);
    }
}
