package com.hh.nobidding.service.impl;

import java.util.List;

import com.hh.nobidding.domain.PpmLineItems;
import com.hh.nobidding.mapper.LineItemsMapper;
import com.hh.nobidding.service.IPpmLineItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 行项目Service业务层处理
 *
 * @author ruoyi
 * @date 2023-12-26
 */
@Service
public class PpmLineItemsServiceImpl implements IPpmLineItemsService {
    @Resource
    private LineItemsMapper lineItemsMapper;

    /**
     * 查询行项目
     *
     * @param vid 行项目主键
     * @return 行项目
     */
    @Override
    public PpmLineItems selectPpmLineItemsByVid(Integer vid) {
        return lineItemsMapper.selectPpmLineItemsByVid(vid);
    }

    /**
     * 查询行项目列表
     *
     * @param ppmLineItems 行项目
     * @return 行项目
     */
    @Override
    public List<PpmLineItems> selectPpmLineItemsList(PpmLineItems ppmLineItems) {
        return lineItemsMapper.selectPpmLineItemsList(ppmLineItems);
    }

    /**
     * 新增行项目
     *
     * @param ppmLineItems 行项目
     * @return 结果
     */
    @Override
    public int insertPpmLineItems(PpmLineItems ppmLineItems) {
        return lineItemsMapper.insertPpmLineItems(ppmLineItems);
    }

    /**
     * 修改行项目
     *
     * @param ppmLineItems 行项目
     * @return 结果
     */
    @Override
    public int updatePpmLineItems(PpmLineItems ppmLineItems) {
        return lineItemsMapper.updatePpmLineItems(ppmLineItems);
    }

    /**
     * 批量删除行项目
     *
     * @param vids 需要删除的行项目主键
     * @return 结果
     */
    @Override
    public int deletePpmLineItemsByVids(Integer[] vids) {
        return lineItemsMapper.deletePpmLineItemsByVids(vids);
    }

    /**
     * 删除行项目信息
     *
     * @param vid 行项目主键
     * @return 结果
     */
    @Override
    public int deletePpmLineItemsByVid(Integer vid) {
        return lineItemsMapper.deletePpmLineItemsByVid(vid);
    }

    //查询非招标对应的产品信息
    @Override
    public List<PpmLineItems> selectItemsDevice(Long aid) {
        return lineItemsMapper.selectItemsDevice(aid);
    }
}
