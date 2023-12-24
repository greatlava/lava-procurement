package com.hh.pms.cm.service.impl;

import com.hh.pms.cm.domain.BsSign;
import com.hh.pms.cm.mapper.BsSignMapper;
import com.hh.pms.cm.service.IBsSignService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 签署执行状态Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-30
 */
@Service
public class BsSignServiceImpl implements IBsSignService
{
    @Resource
    private BsSignMapper bsSignMapper;

    /**
     * 查询签署执行状态
     *
     * @param gnId 签署执行状态主键
     * @return 签署执行状态
     */
    @Override
    public BsSign selectBsSignByGnId(Long gnId)
    {
        return bsSignMapper.selectBsSignByGnId(gnId);
    }

    //根据合同查看签署执行状态信息
    @Override
    public BsSign selectBsSignByEid(Long eid) {
        return bsSignMapper.selectBsSignByEid(eid);
    }

    /**
     * 查询签署执行状态列表
     *
     * @param bsSign 签署执行状态
     * @return 签署执行状态
     */
    @Override
    public List<BsSign> selectBsSignList(BsSign bsSign)
    {
        return bsSignMapper.selectBsSignList(bsSign);
    }

    /**
     * 新增签署执行状态
     *
     * @param bsSign 签署执行状态
     * @return 结果
     */
    @Override
    public int insertBsSign(BsSign bsSign)
    {
        return bsSignMapper.insertBsSign(bsSign);
    }

    /**
     * 修改签署执行状态
     *
     * @param bsSign 签署执行状态
     * @return 结果
     */
    @Override
    public int updateBsSign(BsSign bsSign)
    {
        return bsSignMapper.updateBsSign(bsSign);
    }

    /**
     * 批量删除签署执行状态
     *
     * @param gnIds 需要删除的签署执行状态主键
     * @return 结果
     */
    @Override
    public int deleteBsSignByGnIds(Long[] gnIds)
    {
        return bsSignMapper.deleteBsSignByGnIds(gnIds);
    }

    /**
     * 删除签署执行状态信息
     *
     * @param gnId 签署执行状态主键
     * @return 结果
     */
    @Override
    public int deleteBsSignByGnId(Long gnId)
    {
        return bsSignMapper.deleteBsSignByGnId(gnId);
    }

    @Override
    public int deleteBsSignByEid(Long eid) {
        return bsSignMapper.deleteBsSignByEid(eid);
    }
}