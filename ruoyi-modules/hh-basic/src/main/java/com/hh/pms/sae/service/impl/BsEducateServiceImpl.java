package com.hh.pms.sae.service.impl;

import java.util.List;

import com.hh.pms.sae.domain.BsEducate;
import com.hh.pms.sae.service.IBsEducateService;
import org.springframework.stereotype.Service;
import com.hh.pms.sae.mapper.BsEducateMapper;

import javax.annotation.Resource;

/**
 * 教育Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@Service
public class BsEducateServiceImpl implements IBsEducateService {
    @Resource
    private BsEducateMapper bsEducateMapper;

    /**
     * 查询教育
     *
     * @param rid 教育主键
     * @return 教育
     */
    @Override
    public BsEducate selectBsEducateByRid(Long rid) {
        return bsEducateMapper.selectBsEducateByRid(rid);
    }

    /**
     * 查询教育列表
     *
     * @param bsEducate 教育
     * @return 教育
     */
    @Override
    public List<BsEducate> selectBsEducateList(BsEducate bsEducate) {
        return bsEducateMapper.selectBsEducateList(bsEducate);
    }

    /**
     * 新增教育
     *
     * @param bsEducate 教育
     * @return 结果
     */
    @Override
    public int insertBsEducate(BsEducate bsEducate) {
        return bsEducateMapper.insertBsEducate(bsEducate);
    }

    /**
     * 修改教育
     *
     * @param bsEducate 教育
     * @return 结果
     */
    @Override
    public int updateBsEducate(BsEducate bsEducate) {
        return bsEducateMapper.updateBsEducate(bsEducate);
    }

    /**
     * 批量删除教育
     *
     * @param rids 需要删除的教育主键
     * @return 结果
     */
    @Override
    public int deleteBsEducateByRids(Long[] rids) {
        return bsEducateMapper.deleteBsEducateByRids(rids);
    }

    /**
     * 删除教育信息
     *
     * @param rid 教育主键
     * @return 结果
     */
    @Override
    public int deleteBsEducateByRid(Long rid) {
        return bsEducateMapper.deleteBsEducateByRid(rid);
    }
}
