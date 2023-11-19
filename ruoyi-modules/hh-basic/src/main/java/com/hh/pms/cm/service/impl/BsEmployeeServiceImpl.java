package com.hh.pms.cm.service.impl;

import java.util.List;

import com.hh.pms.cm.domain.BsEmployee;
import org.springframework.stereotype.Service;
import com.hh.pms.cm.mapper.BsEmployeeMapper;
import com.hh.pms.cm.service.IBsEmployeeService;

import javax.annotation.Resource;

/**
 * 员工Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@Service
public class BsEmployeeServiceImpl implements IBsEmployeeService {
    @Resource
    private BsEmployeeMapper bsEmployeeMapper;

    /**
     * 查询员工
     *
     * @param pid 员工主键
     * @return 员工
     */
    @Override
    public BsEmployee selectBsEmployeeByPid(Long pid) {
        return bsEmployeeMapper.selectBsEmployeeByPid(pid);
    }

    /**
     * 查询员工列表
     *
     * @param bsEmployee 员工
     * @return 员工
     */
    @Override
    public List<BsEmployee> selectBsEmployeeList(BsEmployee bsEmployee) {
        return bsEmployeeMapper.selectBsEmployeeList(bsEmployee);
    }

    /**
     * 新增员工
     *
     * @param bsEmployee 员工
     * @return 结果
     */
    @Override
    public int insertBsEmployee(BsEmployee bsEmployee) {
        return bsEmployeeMapper.insertBsEmployee(bsEmployee);
    }

    /**
     * 修改员工
     *
     * @param bsEmployee 员工
     * @return 结果
     */
    @Override
    public int updateBsEmployee(BsEmployee bsEmployee) {
        return bsEmployeeMapper.updateBsEmployee(bsEmployee);
    }

    /**
     * 批量删除员工
     *
     * @param pids 需要删除的员工主键
     * @return 结果
     */
    @Override
    public int deleteBsEmployeeByPids(Long[] pids) {
        return bsEmployeeMapper.deleteBsEmployeeByPids(pids);
    }

    /**
     * 删除员工信息
     *
     * @param pid 员工主键
     * @return 结果
     */
    @Override
    public int deleteBsEmployeeByPid(Long pid) {
        return bsEmployeeMapper.deleteBsEmployeeByPid(pid);
    }
}
