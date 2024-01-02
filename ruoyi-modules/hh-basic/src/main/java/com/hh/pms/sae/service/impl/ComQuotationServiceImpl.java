package com.hh.pms.sae.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hh.pms.sae.mapper.ComQuotationMapper;
import com.hh.pms.sae.domain.ComQuotation;
import com.hh.pms.sae.service.IComQuotationService;

/**
 * 报价单Service业务层处理
 *
 * @author ruoyi
 * @date 2023-12-20
 */
@Service
public class ComQuotationServiceImpl implements IComQuotationService {
    @Autowired
    private ComQuotationMapper comQuotationMapper;

    /**
     * 查询报价单
     *
     * @param bjId 报价单主键
     * @return 报价单
     */
    @Override
    public ComQuotation selectComQuotationByBjId(Long bjId) {
        return comQuotationMapper.selectComQuotationByBjId(bjId);
    }

    /**
     * 查询报价单列表
     *
     * @param comQuotation 报价单
     * @return 报价单
     */
    @Override
    public List<ComQuotation> selectComQuotationList(ComQuotation comQuotation) {
        return comQuotationMapper.selectComQuotationList(comQuotation);
    }

    /**
     * 新增报价单
     *
     * @param comQuotation 报价单
     * @return 结果
     */
    @Override
    public int insertComQuotation(ComQuotation comQuotation) {
        return comQuotationMapper.insertComQuotation(comQuotation);
    }

    /**
     * 修改报价单
     *
     * @param comQuotation 报价单
     * @return 结果
     */
    @Override
    public int updateComQuotation(ComQuotation comQuotation) {
        return comQuotationMapper.updateComQuotation(comQuotation);
    }

    /**
     * 批量删除报价单
     *
     * @param bjIds 需要删除的报价单主键
     * @return 结果
     */
    @Override
    public int deleteComQuotationByBjIds(Long[] bjIds) {
        return comQuotationMapper.deleteComQuotationByBjIds(bjIds);
    }

    /**
     * 删除报价单信息
     *
     * @param bjId 报价单主键
     * @return 结果
     */
    @Override
    public int deleteComQuotationByBjId(Long bjId) {
        return comQuotationMapper.deleteComQuotationByBjId(bjId);
    }

    @Override
    public ComQuotation oneQuotation(String gfId, Long bjHid) {
        return comQuotationMapper.oneQuotation(gfId, bjHid);
    }

    //查询项目报价总数
    @Override
    public int selectBjCount(String bjId) {
        return comQuotationMapper.selectBjCount(bjId);
    }
}
