package com.hh.pms.sae.service;

import java.util.List;

import com.hh.pms.sae.domain.ComQuotation;
import org.apache.ibatis.annotations.Param;

/**
 * 报价单Service接口
 *
 * @author ruoyi
 * @date 2023-12-20
 */
public interface IComQuotationService {
    /**
     * 查询报价单
     *
     * @param bjId 报价单主键
     * @return 报价单
     */
    public ComQuotation selectComQuotationByBjId(Long bjId);

    /**
     * 查询报价单列表
     *
     * @param comQuotation 报价单
     * @return 报价单集合
     */
    public List<ComQuotation> selectComQuotationList(ComQuotation comQuotation);

    /**
     * 新增报价单
     *
     * @param comQuotation 报价单
     * @return 结果
     */
    public int insertComQuotation(ComQuotation comQuotation);

    /**
     * 修改报价单
     *
     * @param comQuotation 报价单
     * @return 结果
     */
    public int updateComQuotation(ComQuotation comQuotation);

    /**
     * 批量删除报价单
     *
     * @param bjIds 需要删除的报价单主键集合
     * @return 结果
     */
    public int deleteComQuotationByBjIds(Long[] bjIds);

    /**
     * 删除报价单信息
     *
     * @param bjId 报价单主键
     * @return 结果
     */
    public int deleteComQuotationByBjId(Long bjId);

    public ComQuotation oneQuotation(String gfId, Long bjHid);

    //查询项目报价总数
    public int selectBjCount(String bjId);
}

