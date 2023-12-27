package com.hh.pms.cm.mapper;

import java.util.List;
import com.hh.pms.cm.domain.BSFrameManagement;

/**
 * 框架协议管理Mapper接口
 *
 * @author ruoyi
 * @date 2023-12-18
 */
public interface BSFrameManagementMapper
{
    /**
     * 查询框架协议管理
     *
     * @param oid 框架协议管理主键
     * @return 框架协议管理
     */
    public BSFrameManagement selectBSFrameManagementByOid(Long oid);

    /**
     * 查询框架协议管理列表
     *
     * @param bSFrameManagement 框架协议管理
     * @return 框架协议管理集合
     */
    public List<BSFrameManagement> selectBSFrameManagementList(BSFrameManagement bSFrameManagement);

    /**
     * 新增框架协议管理
     *
     * @param bSFrameManagement 框架协议管理
     * @return 结果
     */
    public int insertBSFrameManagement(BSFrameManagement bSFrameManagement);

    /**
     * 修改框架协议管理
     *
     * @param bSFrameManagement 框架协议管理
     * @return 结果
     */
    public int updateBSFrameManagement(BSFrameManagement bSFrameManagement);

    /**
     * 删除框架协议管理
     *
     * @param oid 框架协议管理主键
     * @return 结果
     */
    public int deleteBSFrameManagementByOid(Long oid);

    /**
     * 批量删除框架协议管理
     *
     * @param oids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBSFrameManagementByOids(Long[] oids);

    //协议作废
    public int updateXyCancel(Long oid);
}

