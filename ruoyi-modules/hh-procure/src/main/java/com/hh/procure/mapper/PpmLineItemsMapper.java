package com.hh.procure.mapper;

import com.hh.procure.domain.PpmLineItems;

import java.util.List;

/**
 * 行项目Mapper接口
 * 
 * @author ruoyi
 * @date 2023-11-28
 */
public interface PpmLineItemsMapper 
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

    public int deletePpmLineItemsByAid(Integer aid);

    public int deletePpmLineItemByJhId(Integer jhId);

    //查询框架计划内的设备信息
    public List<PpmLineItems> selectItemsDevice(Long jhId);
}
