package com.hh.pms.sae.service.impl;

import java.util.List;

import com.hh.pms.sae.domain.BsExpert;
import com.hh.pms.sae.service.IBsExpertService;
import org.springframework.stereotype.Service;
import com.hh.pms.sae.mapper.BsExpertMapper;

import javax.annotation.Resource;

/**
 * 专家Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@Service
public class BsExpertServiceImpl implements IBsExpertService {
    @Resource
    private BsExpertMapper bsExpertMapper;

    /**
     * 查询专家
     *
     * @param jid 专家主键
     * @return 专家
     */
    @Override
    public BsExpert selectBsExpertByJid(Long jid) {
        return bsExpertMapper.selectBsExpertByJid(jid);
    }

    /**
     * 查询专家列表
     *
     * @param bsExpert 专家
     * @return 专家
     */
    @Override
    public List<BsExpert> selectBsExpertList(BsExpert bsExpert) {
        return bsExpertMapper.selectBsExpertList(bsExpert);
    }

    /**
     * 新增专家
     *
     * @param bsExpert 专家
     * @return 结果
     */
    @Override
    public int insertBsExpert(BsExpert bsExpert) {
        return bsExpertMapper.insertBsExpert(bsExpert);
    }

    /**
     * 修改专家
     *
     * @param bsExpert 专家
     * @return 结果
     */
    @Override
    public int updateBsExpert(BsExpert bsExpert) {
        return bsExpertMapper.updateBsExpert(bsExpert);
    }

    /**
     * 批量删除专家
     *
     * @param jids 需要删除的专家主键
     * @return 结果
     */
    @Override
    public int deleteBsExpertByJids(Long[] jids) {
        return bsExpertMapper.deleteBsExpertByJids(jids);
    }

    /**
     * 删除专家信息
     *
     * @param jid 专家主键
     * @return 结果
     */
    @Override
    public int deleteBsExpertByJid(Long jid) {
        return bsExpertMapper.deleteBsExpertByJid(jid);
    }
}
