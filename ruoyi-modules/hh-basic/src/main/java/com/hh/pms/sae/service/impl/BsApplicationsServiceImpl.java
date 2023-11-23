package com.hh.pms.sae.service.impl;

import java.util.List;

import com.hh.pms.sae.domain.BsApplications;
import org.springframework.stereotype.Service;
import com.hh.pms.sae.mapper.BsApplicationsMapper;
import com.hh.pms.sae.service.IBsApplicationsService;

import javax.annotation.Resource;

/**
 * 抽取申请Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@Service
public class BsApplicationsServiceImpl implements IBsApplicationsService {
    @Resource
    private BsApplicationsMapper bsApplicationsMapper;

    /**
     * 查询抽取申请
     *
     * @param xid 抽取申请主键
     * @return 抽取申请
     */
    @Override
    public BsApplications selectBsApplicationsByXid(Long xid) {
        return bsApplicationsMapper.selectBsApplicationsByXid(xid);
    }

    /**
     * 查询抽取申请列表
     *
     * @param bsApplications 抽取申请
     * @return 抽取申请
     */
    @Override
    public List<BsApplications> selectBsApplicationsList(BsApplications bsApplications) {
        return bsApplicationsMapper.selectBsApplicationsList(bsApplications);
    }

    /**
     * 新增抽取申请
     *
     * @param bsApplications 抽取申请
     * @return 结果
     */
    @Override
    public int insertBsApplications(BsApplications bsApplications) {
        return bsApplicationsMapper.insertBsApplications(bsApplications);
    }

    /**
     * 修改抽取申请
     *
     * @param bsApplications 抽取申请
     * @return 结果
     */
    @Override
    public int updateBsApplications(BsApplications bsApplications) {
        return bsApplicationsMapper.updateBsApplications(bsApplications);
    }

    /**
     * 批量删除抽取申请
     *
     * @param xids 需要删除的抽取申请主键
     * @return 结果
     */
    @Override
    public int deleteBsApplicationsByXids(Long[] xids) {
        return bsApplicationsMapper.deleteBsApplicationsByXids(xids);
    }

    /**
     * 删除抽取申请信息
     *
     * @param xid 抽取申请主键
     * @return 结果
     */
    @Override
    public int deleteBsApplicationsByXid(Long xid) {
        return bsApplicationsMapper.deleteBsApplicationsByXid(xid);
    }
}
