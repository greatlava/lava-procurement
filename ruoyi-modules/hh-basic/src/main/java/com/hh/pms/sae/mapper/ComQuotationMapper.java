package com.hh.pms.sae.mapper;

import java.util.List;

import com.hh.pms.sae.domain.ComQuotation;
import org.apache.ibatis.annotations.Param;

/**
 * 报价单Mapper接口
 *
 * @author ruoyi
 * @date 2023-12-20
 */
public interface ComQuotationMapper {
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
     * 删除报价单
     *
     * @param bjId 报价单主键
     * @return 结果
     */
    public int deleteComQuotationByBjId(Long bjId);

    /**
     * 批量删除报价单
     *
     * @param bjIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteComQuotationByBjIds(Long[] bjIds);

    public ComQuotation oneQuotation(@Param("gfId") String gfId, @Param("bjHid") Long bjHid);

    //查询项目报价总数
    public int selectBjCount(String bjId);
}
