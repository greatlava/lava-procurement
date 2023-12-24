package com.hh.bidding.mapper;

import java.util.List;
import com.hh.bidding.domain.BidCommittee;

/**
 * 评标委员会Mapper接口
 *
 * @author ruoyi
 * @date 2023-12-18
 */
public interface BidCommitteeMapper
{
    /**
     * 查询评标委员会
     *
     * @param pbId 评标委员会主键
     * @return 评标委员会
     */
    public BidCommittee selectBidCommitteeByPbId(Long pbId);

    /**
     * 查询评标委员会列表
     *
     * @param bidCommittee 评标委员会
     * @return 评标委员会集合
     */
    public List<BidCommittee> selectBidCommitteeList(BidCommittee bidCommittee);

    /**
     * 新增评标委员会
     *
     * @param bidCommittee 评标委员会
     * @return 结果
     */
    public int insertBidCommittee(BidCommittee bidCommittee);

    /**
     * 修改评标委员会
     *
     * @param bidCommittee 评标委员会
     * @return 结果
     */
    public int updateBidCommittee(BidCommittee bidCommittee);

    /**
     * 删除评标委员会
     *
     * @param pbId 评标委员会主键
     * @return 结果
     */
    public int deleteBidCommitteeByPbId(Long pbId);

    /**
     * 批量删除评标委员会
     *
     * @param pbIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBidCommitteeByPbIds(Long[] pbIds);

    public int delComBySid(Long sid);

    public List<BidCommittee> findCommitteeAndExpert(Long sid);
}
