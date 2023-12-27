package com.hh.nobidding.mapper;

import java.util.List;

import com.hh.nobidding.domain.PpmLineItems;

/**
 * 行项目Mapper接口
 *
 * @author ruoyi
 * @date 2023-12-26
 */
public interface LineItemsMapper
{
    /**
     * 查询行项目
     *
     * @param vid 行项目主键
     * @return 行项目
     */
    public PpmLineItems selectPpmLineItemsByVid(Integer vid);

    /**
     * 查询行项目列表
     *
     * @param ppmLineItems 行项目
     * @return 行项目集合
     */
    public List<PpmLineItems> selectPpmLineItemsList(PpmLineItems ppmLineItems);

    /**
     * 新增行项目
     *
     * @param ppmLineItems 行项目
     * @return 结果
     */
    public int insertPpmLineItems(PpmLineItems ppmLineItems);

    /**
     * 修改行项目
     *
     * @param ppmLineItems 行项目
     * @return 结果
     */
    public int updatePpmLineItems(PpmLineItems ppmLineItems);

    /**
     * 删除行项目
     *
     * @param vid 行项目主键
     * @return 结果
     */
    public int deletePpmLineItemsByVid(Integer vid);

    /**
     * 批量删除行项目
     *
     * @param vids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePpmLineItemsByVids(Integer[] vids);

    //查询非招标对应的产品信息
    public List<PpmLineItems> selectItemsDevice(Long aid);
}
