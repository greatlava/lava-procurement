package com.hh.bidding.service.impl;

import com.hh.bidding.domain.ComCodeRules;
import com.hh.bidding.mapper.ComCodeRulesMapper;
import com.hh.bidding.service.IComCodeRulesService;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-22
 */
@Service
public class ComCodeRulesServiceImpl implements IComCodeRulesService {
    @Resource
    private ComCodeRulesMapper comCodeRulesMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public ComCodeRules selectComCodeRulesById(Long id) {
        return comCodeRulesMapper.selectComCodeRulesById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param comCodeRules 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<ComCodeRules> selectComCodeRulesList(ComCodeRules comCodeRules) {
        return comCodeRulesMapper.selectComCodeRulesList(comCodeRules);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param comCodeRules 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertComCodeRules(ComCodeRules comCodeRules) {
        return comCodeRulesMapper.insertComCodeRules(comCodeRules);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param comCodeRules 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateComCodeRules(ComCodeRules comCodeRules) {
        comCodeRules.setUpdateTime(DateUtils.getNowDate());
        return comCodeRulesMapper.updateComCodeRules(comCodeRules);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteComCodeRulesByIds(Long[] ids) {
        return comCodeRulesMapper.deleteComCodeRulesByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteComCodeRulesById(Long id) {
        return comCodeRulesMapper.deleteComCodeRulesById(id);
    }

    @Override
    public ComCodeRules selectComCodeRulesByTargetForm(String str) {
        return comCodeRulesMapper.selectComCodeRulesByTargetForm(str);
    }
}
