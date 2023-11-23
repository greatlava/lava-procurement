package com.hh.pms.service;

import com.hh.pms.domain.PpmFrameManagement;

import java.util.List;

/**
 * 框架协议管理Service接口
 * 
 * @author ruoyi
 * @date 2023-11-19
 */
public interface IPpmFrameManagementService 
{
    /**
     * 查询框架协议管理
     * 
     * @param oid 框架协议管理主键
     * @return 框架协议管理
     */
    public PpmFrameManagement selectPpmFrameManagementByOid(Long oid);

    /**
     * 查询框架协议管理列表
     * 
     * @param ppmFrameManagement 框架协议管理
     * @return 框架协议管理集合
     */
    public List<PpmFrameManagement> selectPpmFrameManagementList(PpmFrameManagement ppmFrameManagement);

    /**
     * 新增框架协议管理
     * 
     * @param ppmFrameManagement 框架协议管理
     * @return 结果
     */
    public int insertPpmFrameManagement(PpmFrameManagement ppmFrameManagement);

    /**
     * 修改框架协议管理
     * 
     * @param ppmFrameManagement 框架协议管理
     * @return 结果
     */
    public int updatePpmFrameManagement(PpmFrameManagement ppmFrameManagement);

    /**
     * 批量删除框架协议管理
     * 
     * @param oids 需要删除的框架协议管理主键集合
     * @return 结果
     */
    public int deletePpmFrameManagementByOids(Long[] oids);

    /**
     * 删除框架协议管理信息
     * 
     * @param oid 框架协议管理主键
     * @return 结果
     */
    public int deletePpmFrameManagementByOid(Long oid);
}
