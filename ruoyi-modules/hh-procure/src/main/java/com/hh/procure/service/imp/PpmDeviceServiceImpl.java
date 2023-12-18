package com.hh.procure.service.imp;

import com.hh.procure.domain.PpmDevice;
import com.hh.procure.mapper.PpmDeviceMapper;
import com.hh.procure.service.IPpmDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 设备信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-11-28
 */
@Service
public class PpmDeviceServiceImpl implements IPpmDeviceService
{
    @Autowired
    private PpmDeviceMapper ppmDeviceMapper;

    /**
     * 查询设备信息
     * 
     * @param tid 设备信息主键
     * @return 设备信息
     */
    @Override
    public PpmDevice selectPpmDeviceByTid(String tid)
    {
        return ppmDeviceMapper.selectPpmDeviceByTid(tid);
    }

    /**
     * 查询设备信息列表
     * 
     * @param ppmDevice 设备信息
     * @return 设备信息
     */
    @Override
    public List<PpmDevice> selectPpmDeviceList(PpmDevice ppmDevice)
    {
        return ppmDeviceMapper.selectPpmDeviceList(ppmDevice);
    }

    /**
     * 新增设备信息
     * 
     * @param ppmDevice 设备信息
     * @return 结果
     */
    @Override
    public int insertPpmDevice(PpmDevice ppmDevice)
    {
        return ppmDeviceMapper.insertPpmDevice(ppmDevice);
    }

    /**
     * 修改设备信息
     * 
     * @param ppmDevice 设备信息
     * @return 结果
     */
    @Override
    public int updatePpmDevice(PpmDevice ppmDevice)
    {
        return ppmDeviceMapper.updatePpmDevice(ppmDevice);
    }

    /**
     * 批量删除设备信息
     * 
     * @param tids 需要删除的设备信息主键
     * @return 结果
     */
    @Override
    public int deletePpmDeviceByTids(String[] tids)
    {
        return ppmDeviceMapper.deletePpmDeviceByTids(tids);
    }

    /**
     * 删除设备信息信息
     * 
     * @param tid 设备信息主键
     * @return 结果
     */
    @Override
    public int deletePpmDeviceByTid(String tid)
    {
        return ppmDeviceMapper.deletePpmDeviceByTid(tid);
    }
}
