package com.hh.procure.mapper;

import com.hh.procure.domain.PpmFrameManagement;

import java.util.List;

public interface PpmFrameManagementMapper
{
    public PpmFrameManagement selectPpmFrameManagementByOid(Long oid);

    public List<PpmFrameManagement> selectPpmFrameManagementList(PpmFrameManagement ppmFrameManagement);

    public int insertPpmFrameManagement(PpmFrameManagement ppmFrameManagement);

    public int updatePpmFrameManagement(PpmFrameManagement ppmFrameManagement);

    public int deletePpmFrameManagementByOid(Long oid);

    public int deletePpmFrameManagementByOids(Long[] oids);
}
