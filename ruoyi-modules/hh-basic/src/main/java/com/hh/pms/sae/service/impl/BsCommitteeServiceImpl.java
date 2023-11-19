package com.hh.pms.sae.service.impl;

import java.util.List;

import com.hh.pms.sae.domain.BsCommittee;
import org.springframework.stereotype.Service;
import com.hh.pms.sae.mapper.BsCommitteeMapper;
import com.hh.pms.sae.service.IBsCommitteeService;

import javax.annotation.Resource;

/**
 * 评标委员会Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@Service
public class BsCommitteeServiceImpl implements IBsCommitteeService {
    @Resource
    private BsCommitteeMapper bsCommitteeMapper;

    /**
     * 查询评标委员会
     *
     * @param pbId 评标委员会主键
     * @return 评标委员会
     */
    @Override
    public BsCommittee selectBsCommitteeByPbId(Long pbId) {
        return bsCommitteeMapper.selectBsCommitteeByPbId(pbId);
    }

    /**
     * 查询评标委员会列表
     *
     * @param bsCommittee 评标委员会
     * @return 评标委员会
     */
    @Override
    public List<BsCommittee> selectBsCommitteeList(BsCommittee bsCommittee) {
        return bsCommitteeMapper.selectBsCommitteeList(bsCommittee);
    }

    /**
     * 新增评标委员会
     *
     * @param bsCommittee 评标委员会
     * @return 结果
     */
    @Override
    public int insertBsCommittee(BsCommittee bsCommittee) {
        return bsCommitteeMapper.insertBsCommittee(bsCommittee);
    }

    /**
     * 修改评标委员会
     *
     * @param bsCommittee 评标委员会
     * @return 结果
     */
    @Override
    public int updateBsCommittee(BsCommittee bsCommittee) {
        return bsCommitteeMapper.updateBsCommittee(bsCommittee);
    }

    /**
     * 批量删除评标委员会
     *
     * @param pbIds 需要删除的评标委员会主键
     * @return 结果
     */
    @Override
    public int deleteBsCommitteeByPbIds(Long[] pbIds) {
        return bsCommitteeMapper.deleteBsCommitteeByPbIds(pbIds);
    }

    /**
     * 删除评标委员会信息
     *
     * @param pbId 评标委员会主键
     * @return 结果
     */
    @Override
    public int deleteBsCommitteeByPbId(Long pbId) {
        return bsCommitteeMapper.deleteBsCommitteeByPbId(pbId);
    }
}
