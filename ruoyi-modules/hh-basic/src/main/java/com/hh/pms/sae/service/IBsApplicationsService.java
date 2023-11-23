package com.hh.pms.sae.service;

import java.util.List;

import com.hh.pms.sae.domain.BsApplications;

/**
 * 抽取申请Service接口
 *
 * @author ruoyi
 * @date 2023-11-19
 */
public interface IBsApplicationsService {
    /**
     * 查询抽取申请
     *
     * @param xid 抽取申请主键
     * @return 抽取申请
     */
    public BsApplications selectBsApplicationsByXid(Long xid);

    /**
     * 查询抽取申请列表
     *
     * @param bsApplications 抽取申请
     * @return 抽取申请集合
     */
    public List<BsApplications> selectBsApplicationsList(BsApplications bsApplications);

    /**
     * 新增抽取申请
     *
     * @param bsApplications 抽取申请
     * @return 结果
     */
    public int insertBsApplications(BsApplications bsApplications);

    /**
     * 修改抽取申请
     *
     * @param bsApplications 抽取申请
     * @return 结果
     */
    public int updateBsApplications(BsApplications bsApplications);

    /**
     * 批量删除抽取申请
     *
     * @param xids 需要删除的抽取申请主键集合
     * @return 结果
     */
    public int deleteBsApplicationsByXids(Long[] xids);

    /**
     * 删除抽取申请信息
     *
     * @param xid 抽取申请主键
     * @return 结果
     */
    public int deleteBsApplicationsByXid(Long xid);
}
