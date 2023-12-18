package com.hh.pms.sae.service.impl;

import java.util.List;

import com.hh.pms.sae.domain.BsOccupation;
import org.springframework.stereotype.Service;
import com.hh.pms.sae.mapper.BsOccupationMapper;
import com.hh.pms.sae.service.IBsOccupationService;

import javax.annotation.Resource;

/**
 * 职业Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@Service
public class BsOccupationServiceImpl implements IBsOccupationService {
    @Resource
    private BsOccupationMapper bsOccupationMapper;

    /**
     * 查询职业
     *
     * @param nid 职业主键
     * @return 职业
     */
    @Override
    public BsOccupation selectBsOccupationByNid(Long nid) {
        return bsOccupationMapper.selectBsOccupationByNid(nid);
    }

    /**
     * 查询职业列表
     *
     * @param bsOccupation 职业
     * @return 职业
     */
    @Override
    public List<BsOccupation> selectBsOccupationList(BsOccupation bsOccupation) {
        return bsOccupationMapper.selectBsOccupationList(bsOccupation);
    }

    /**
     * 新增职业
     *
     * @param bsOccupation 职业
     * @return 结果
     */
    @Override
    public int insertBsOccupation(BsOccupation bsOccupation) {
        return bsOccupationMapper.insertBsOccupation(bsOccupation);
    }

    /**
     * 修改职业
     *
     * @param bsOccupation 职业
     * @return 结果
     */
    @Override
    public int updateBsOccupation(BsOccupation bsOccupation) {
        return bsOccupationMapper.updateBsOccupation(bsOccupation);
    }

    /**
     * 批量删除职业
     *
     * @param nids 需要删除的职业主键
     * @return 结果
     */
    @Override
    public int deleteBsOccupationByNids(Long[] nids) {
        return bsOccupationMapper.deleteBsOccupationByNids(nids);
    }

    /**
     * 删除职业信息
     *
     * @param nid 职业主键
     * @return 结果
     */
    @Override
    public int deleteBsOccupationByNid(Long nid) {
        return bsOccupationMapper.deleteBsOccupationByNid(nid);
    }
}
