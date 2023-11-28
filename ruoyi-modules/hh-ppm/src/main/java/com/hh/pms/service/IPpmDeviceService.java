package com.hh.pms.service;

import com.hh.pms.domain.PpmDevice;

import java.util.List;

/**
 * 设备信息Service接口
 * 
 * @author ruoyi
 * @date 2023-11-28
 */
public interface IPpmDeviceService 
{
    /**
     * 查询设备信息
     * 
     * @param tid 设备信息主键
     * @return 设备信息
     */
    public PpmDevice selectPpmDeviceByTid(String tid);

    /**
     * 查询设备信息列表
     * 
     * @param ppmDevice 设备信息
     * @return 设备信息集合
     */
    public List<PpmDevice> selectPpmDeviceList(PpmDevice ppmDevice);

    /**
     * 新增设备信息
     * 
     * @param ppmDevice 设备信息
     * @return 结果
     */
    public int insertPpmDevice(PpmDevice ppmDevice);

    /**
     * 修改设备信息
     * 
     * @param ppmDevice 设备信息
     * @return 结果
     */
    public int updatePpmDevice(PpmDevice ppmDevice);

    /**
     * 批量删除设备信息
     * 
     * @param tids 需要删除的设备信息主键集合
     * @return 结果
     */
    public int deletePpmDeviceByTids(String[] tids);

    /**
     * 删除设备信息信息
     * 
     * @param tid 设备信息主键
     * @return 结果
     */
    public int deletePpmDeviceByTid(String tid);
}
