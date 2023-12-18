package com.hh.procure.service.imp;

import com.hh.procure.domain.PpmLineItems;
import com.hh.procure.mapper.PpmLineItemsMapper;
import com.hh.procure.service.IPpmLineItemsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 行项目Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-28
 */
@Service
public class PpmLineItemsServiceImpl implements IPpmLineItemsService {
    @Resource
    private PpmLineItemsMapper ppmLineItemsMapper;

    /**
     * 查询行项目
     *
     * @param vid 行项目主键
     * @return 行项目
     */
    @Override
    public PpmLineItems selectPpmLineItemsByVid(Integer vid) {
        return ppmLineItemsMapper.selectPpmLineItemsByVid(vid);
    }

    /**
     * 查询行项目列表
     *
     * @param ppmLineItems 行项目
     * @return 行项目
     */
    @Override
    public List<PpmLineItems> selectPpmLineItemsList(PpmLineItems ppmLineItems) {
        return ppmLineItemsMapper.selectPpmLineItemsList(ppmLineItems);
    }

    /**
     * 新增行项目
     *
     * @param ppmLineItems 行项目
     * @return 结果
     */
    @Override
    public int insertPpmLineItems(PpmLineItems ppmLineItems) {
        return ppmLineItemsMapper.insertPpmLineItems(ppmLineItems);
    }

    /**
     * 修改行项目
     *
     * @param ppmLineItems 行项目
     * @return 结果
     */
    @Override
    public int updatePpmLineItems(PpmLineItems ppmLineItems) {
        return ppmLineItemsMapper.updatePpmLineItems(ppmLineItems);
    }

    /**
     * 批量删除行项目
     *
     * @param vids 需要删除的行项目主键
     * @return 结果
     */
    @Override
    public int deletePpmLineItemsByVids(Integer[] vids) {
        return ppmLineItemsMapper.deletePpmLineItemsByVids(vids);
    }

    /**
     * 删除行项目信息
     *
     * @param vid 行项目主键
     * @return 结果
     */
    @Override
    public int deletePpmLineItemsByVid(Integer vid) {
        return ppmLineItemsMapper.deletePpmLineItemsByVid(vid);
    }

    public int deletePpmLineItemsByAid(Integer aid) {
        return ppmLineItemsMapper.deletePpmLineItemsByAid(aid);
    }

    @Override
    public int deletePpmLineItemByJhId(Integer jhId) {
        return ppmLineItemsMapper.deletePpmLineItemByJhId(jhId);
    }

    //查询框架计划内的设备信息
    @Override
    public List<PpmLineItems> selectItemsDevice(Long jhId) {
        return ppmLineItemsMapper.selectItemsDevice(jhId);
    }

}
