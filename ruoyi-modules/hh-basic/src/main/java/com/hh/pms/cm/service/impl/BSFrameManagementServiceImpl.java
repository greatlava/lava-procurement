package com.hh.pms.cm.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hh.pms.cm.mapper.BSFrameManagementMapper;
import com.hh.pms.cm.domain.BSFrameManagement;
import com.hh.pms.cm.service.IBSFrameManagementService;

import javax.annotation.Resource;

/**
 * 框架协议管理Service业务层处理
 *
 * @author ruoyi
 * @date 2023-12-18
 */
@Service
public class BSFrameManagementServiceImpl implements IBSFrameManagementService
{
    @Resource
    private BSFrameManagementMapper bSFrameManagementMapper;

    /**
     * 查询框架协议管理
     *
     * @param oid 框架协议管理主键
     * @return 框架协议管理
     */
    @Override
    public BSFrameManagement selectBSFrameManagementByOid(Long oid)
    {
        return bSFrameManagementMapper.selectBSFrameManagementByOid(oid);
    }

    /**
     * 查询框架协议管理列表
     *
     * @param bSFrameManagement 框架协议管理
     * @return 框架协议管理
     */
    @Override
    public List<BSFrameManagement> selectBSFrameManagementList(BSFrameManagement bSFrameManagement)
    {
        return bSFrameManagementMapper.selectBSFrameManagementList(bSFrameManagement);
    }

    /**
     * 新增框架协议管理
     *
     * @param bSFrameManagement 框架协议管理
     * @return 结果
     */
    @Override
    public int insertBSFrameManagement(BSFrameManagement bSFrameManagement)
    {
        bSFrameManagement.setCreateTime(DateUtils.getNowDate());
        return bSFrameManagementMapper.insertBSFrameManagement(bSFrameManagement);
    }

    /**
     * 修改框架协议管理
     *
     * @param bSFrameManagement 框架协议管理
     * @return 结果
     */
    @Override
    public int updateBSFrameManagement(BSFrameManagement bSFrameManagement)
    {
        return bSFrameManagementMapper.updateBSFrameManagement(bSFrameManagement);
    }

    /**
     * 批量删除框架协议管理
     *
     * @param oids 需要删除的框架协议管理主键
     * @return 结果
     */
    @Override
    public int deleteBSFrameManagementByOids(Long[] oids)
    {
        return bSFrameManagementMapper.deleteBSFrameManagementByOids(oids);
    }

    /**
     * 删除框架协议管理信息
     *
     * @param oid 框架协议管理主键
     * @return 结果
     */
    @Override
    public int deleteBSFrameManagementByOid(Long oid)
    {
        return bSFrameManagementMapper.deleteBSFrameManagementByOid(oid);
    }

    //协议作废
    @Override
    public int updateXyCancel(Long oid) {
        return bSFrameManagementMapper.updateXyCancel(oid);
    }

}
