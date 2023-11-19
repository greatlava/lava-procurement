package com.hh.pms.service.imp;

import java.util.List;

import com.hh.pms.domain.PpmFrameManagement;
import com.hh.pms.mapper.PpmFrameManagementMapper;
import com.hh.pms.service.IPpmFrameManagementService;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 框架协议管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
@Service
public class PpmFrameManagementServiceImpl implements IPpmFrameManagementService
{
    @Autowired
    private PpmFrameManagementMapper ppmFrameManagementMapper;

    /**
     * 查询框架协议管理
     * 
     * @param oid 框架协议管理主键
     * @return 框架协议管理
     */
    @Override
    public PpmFrameManagement selectPpmFrameManagementByOid(Long oid)
    {
        return ppmFrameManagementMapper.selectPpmFrameManagementByOid(oid);
    }

    /**
     * 查询框架协议管理列表
     * 
     * @param ppmFrameManagement 框架协议管理
     * @return 框架协议管理
     */
    @Override
    public List<PpmFrameManagement> selectPpmFrameManagementList(PpmFrameManagement ppmFrameManagement)
    {
        return ppmFrameManagementMapper.selectPpmFrameManagementList(ppmFrameManagement);
    }

    /**
     * 新增框架协议管理
     * 
     * @param ppmFrameManagement 框架协议管理
     * @return 结果
     */
    @Override
    public int insertPpmFrameManagement(PpmFrameManagement ppmFrameManagement)
    {
        ppmFrameManagement.setCreateTime(DateUtils.getNowDate());
        return ppmFrameManagementMapper.insertPpmFrameManagement(ppmFrameManagement);
    }

    /**
     * 修改框架协议管理
     * 
     * @param ppmFrameManagement 框架协议管理
     * @return 结果
     */
    @Override
    public int updatePpmFrameManagement(PpmFrameManagement ppmFrameManagement)
    {
        return ppmFrameManagementMapper.updatePpmFrameManagement(ppmFrameManagement);
    }

    /**
     * 批量删除框架协议管理
     * 
     * @param oids 需要删除的框架协议管理主键
     * @return 结果
     */
    @Override
    public int deletePpmFrameManagementByOids(Long[] oids)
    {
        return ppmFrameManagementMapper.deletePpmFrameManagementByOids(oids);
    }

    /**
     * 删除框架协议管理信息
     * 
     * @param oid 框架协议管理主键
     * @return 结果
     */
    @Override
    public int deletePpmFrameManagementByOid(Long oid)
    {
        return ppmFrameManagementMapper.deletePpmFrameManagementByOid(oid);
    }
}
