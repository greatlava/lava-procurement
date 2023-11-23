package com.hh.pms.sae.mapper;

import java.util.List;

import com.hh.pms.sae.domain.BsCommittee;

/**
 * 评标委员会Mapper接口
 *
 * @author ruoyi
 * @date 2023-11-19
 */
public interface BsCommitteeMapper {
    /**
     * 查询评标委员会
     *
     * @param pbId 评标委员会主键
     * @return 评标委员会
     */
    public BsCommittee selectBsCommitteeByPbId(Long pbId);

    /**
     * 查询评标委员会列表
     *
     * @param bsCommittee 评标委员会
     * @return 评标委员会集合
     */
    public List<BsCommittee> selectBsCommitteeList(BsCommittee bsCommittee);

    /**
     * 新增评标委员会
     *
     * @param bsCommittee 评标委员会
     * @return 结果
     */
    public int insertBsCommittee(BsCommittee bsCommittee);

    /**
     * 修改评标委员会
     *
     * @param bsCommittee 评标委员会
     * @return 结果
     */
    public int updateBsCommittee(BsCommittee bsCommittee);

    /**
     * 删除评标委员会
     *
     * @param pbId 评标委员会主键
     * @return 结果
     */
    public int deleteBsCommitteeByPbId(Long pbId);

    /**
     * 批量删除评标委员会
     *
     * @param pbIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBsCommitteeByPbIds(Long[] pbIds);
}
