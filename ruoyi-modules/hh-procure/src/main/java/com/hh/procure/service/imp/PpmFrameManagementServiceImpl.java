package com.hh.procure.service.imp;

import java.util.List;

import com.hh.procure.domain.PpmFrameManagement;
import com.hh.procure.mapper.PpmFrameManagementMapper;
import com.hh.procure.service.IPpmFrameManagementService;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PpmFrameManagementServiceImpl implements IPpmFrameManagementService
{
    @Autowired
    private PpmFrameManagementMapper ppmFrameManagementMapper;

    @Override
    public PpmFrameManagement selectPpmFrameManagementByOid(Long oid)
    {
        return ppmFrameManagementMapper.selectPpmFrameManagementByOid(oid);
    }

    @Override
    public List<PpmFrameManagement> selectPpmFrameManagementList(PpmFrameManagement ppmFrameManagement)
    {
        return ppmFrameManagementMapper.selectPpmFrameManagementList(ppmFrameManagement);
    }

    @Override
    public int insertPpmFrameManagement(PpmFrameManagement ppmFrameManagement)
    {
        ppmFrameManagement.setCreateTime(DateUtils.getNowDate());
        return ppmFrameManagementMapper.insertPpmFrameManagement(ppmFrameManagement);
    }

    @Override
    public int updatePpmFrameManagement(PpmFrameManagement ppmFrameManagement)
    {
        return ppmFrameManagementMapper.updatePpmFrameManagement(ppmFrameManagement);
    }

    @Override
    public int deletePpmFrameManagementByOids(Long[] oids)
    {
        return ppmFrameManagementMapper.deletePpmFrameManagementByOids(oids);
    }

    @Override
    public int deletePpmFrameManagementByOid(Long oid)
    {
        return ppmFrameManagementMapper.deletePpmFrameManagementByOid(oid);
    }
}
