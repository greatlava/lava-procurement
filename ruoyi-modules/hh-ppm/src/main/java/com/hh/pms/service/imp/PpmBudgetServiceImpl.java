package com.hh.pms.service.imp;

import java.util.List;

import com.hh.pms.domain.PpmBudget;
import com.hh.pms.mapper.PpmBudgetMapper;
import com.hh.pms.service.IPpmBudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 预算Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-11-28
 */
@Service
public class PpmBudgetServiceImpl implements IPpmBudgetService
{
    @Autowired
    private PpmBudgetMapper ppmBudgetMapper;

    /**
     * 查询预算
     * 
     * @param duId 预算主键
     * @return 预算
     */
    @Override
    public PpmBudget selectPpmBudgetByDuId(String duId)
    {
        return ppmBudgetMapper.selectPpmBudgetByDuId(duId);
    }

    /**
     * 查询预算列表
     * 
     * @param ppmBudget 预算
     * @return 预算
     */
    @Override
    public List<PpmBudget> selectPpmBudgetList(PpmBudget ppmBudget)
    {
        return ppmBudgetMapper.selectPpmBudgetList(ppmBudget);
    }

    /**
     * 新增预算
     * 
     * @param ppmBudget 预算
     * @return 结果
     */
    @Override
    public int insertPpmBudget(PpmBudget ppmBudget)
    {
        return ppmBudgetMapper.insertPpmBudget(ppmBudget);
    }

    /**
     * 修改预算
     * 
     * @param ppmBudget 预算
     * @return 结果
     */
    @Override
    public int updatePpmBudget(PpmBudget ppmBudget)
    {
        return ppmBudgetMapper.updatePpmBudget(ppmBudget);
    }

    /**
     * 批量删除预算
     * 
     * @param duIds 需要删除的预算主键
     * @return 结果
     */
    @Override
    public int deletePpmBudgetByDuIds(String[] duIds)
    {
        return ppmBudgetMapper.deletePpmBudgetByDuIds(duIds);
    }

    /**
     * 删除预算信息
     * 
     * @param duId 预算主键
     * @return 结果
     */
    @Override
    public int deletePpmBudgetByDuId(String duId)
    {
        return ppmBudgetMapper.deletePpmBudgetByDuId(duId);
    }

    @Override
    public List<PpmBudget> selectPpmBudgetByAid(String aid){
        return ppmBudgetMapper.selectPpmBudgetByAid(aid);
    }
}
