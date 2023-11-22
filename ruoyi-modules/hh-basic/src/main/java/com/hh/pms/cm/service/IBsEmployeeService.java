package com.hh.pms.cm.service;

import java.util.List;

import com.hh.pms.cm.domain.BsEmployee;
import com.ruoyi.common.core.domain.R;

/**
 * 员工Service接口
 *
 * @author ruoyi
 * @date 2023-11-19
 */
public interface IBsEmployeeService {
    /**
     * 查询员工
     *
     * @param pid 员工主键
     * @return 员工
     */
    public BsEmployee selectBsEmployeeByPid(Long pid);

    /**
     * 查询员工列表
     *
     * @param bsEmployee 员工
     * @return 员工集合
     */
    public List<BsEmployee> selectBsEmployeeList(BsEmployee bsEmployee);

    /**
     * 新增员工
     *
     * @param bsEmployee 员工
     * @return 结果
     */
    public int insertBsEmployee(BsEmployee bsEmployee);

    /**
     * 修改员工
     *
     * @param bsEmployee 员工
     * @return 结果
     */
    public int updateBsEmployee(BsEmployee bsEmployee);

    /**
     * 批量删除员工
     *
     * @param pids 需要删除的员工主键集合
     * @return 结果
     */
    public int deleteBsEmployeeByPids(Long[] pids);

    /**
     * 删除员工信息
     *
     * @param pid 员工主键
     * @return 结果
     */
    public int deleteBsEmployeeByPid(Long pid);

    public R<BsEmployee> loginEmp(String username, String password);
}
