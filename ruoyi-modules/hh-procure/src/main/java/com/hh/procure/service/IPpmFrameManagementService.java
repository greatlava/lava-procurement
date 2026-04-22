package com.hh.procure.service;

import com.hh.procure.domain.PpmFrameManagement;

import java.util.List;

public interface IPpmFrameManagementService
{
    public PpmFrameManagement selectPpmFrameManagementByOid(Long oid);

    public List<PpmFrameManagement> selectPpmFrameManagementList(PpmFrameManagement ppmFrameManagement);

    public int insertPpmFrameManagement(PpmFrameManagement ppmFrameManagement);

    public int updatePpmFrameManagement(PpmFrameManagement ppmFrameManagement);

    public int deletePpmFrameManagementByOids(Long[] oids);

    public int deletePpmFrameManagementByOid(Long oid);
}
