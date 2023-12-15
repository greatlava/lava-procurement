package com.hh.pms.service;

import com.hh.pms.domain.PpmLineItems;

import java.util.List;

/**
 * 行项目Service接口
 * 
 * @author ruoyi
 * @date 2023-11-28
 */
public interface IPpmLineItemsService 
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
     * 批量删除行项目
     * 
     * @param vids 需要删除的行项目主键集合
     * @return 结果
     */
    public int deletePpmLineItemsByVids(Integer[] vids);

    /**
     * 删除行项目信息
     * 
     * @param vid 行项目主键
     * @return 结果
     */
    public int deletePpmLineItemsByVid(Integer vid);

    public int deletePpmLineItemsByAid(Integer aid);

    public int deletePpmLineItemByJhId(Integer jhId);

    //查询框架计划内的设备信息
    public List<PpmLineItems> selectItemsDevice(Long jhId);
}
