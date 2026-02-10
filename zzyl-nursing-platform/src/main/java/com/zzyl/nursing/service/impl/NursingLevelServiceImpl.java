package com.zzyl.nursing.service.impl;

import java.util.Arrays;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zzyl.nursing.mapper.NursingLevelMapper;
import com.zzyl.nursing.domain.NursingLevel;
import com.zzyl.nursing.service.INursingLevelService;

/**
 * 护理等级Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-02-09
 */
@Service
public class NursingLevelServiceImpl implements INursingLevelService 
{
    @Autowired
    private NursingLevelMapper nursingLevelMapper;

    /**
     * 查询护理等级
     * 
     * @param id 护理等级主键
     * @return 护理等级
     */
    @Override
    public NursingLevel selectNursingLevelById(Long id)
    {
        // MyBatis-Plus: 自动生成 SQL: SELECT * FROM nursing_level WHERE id = ?
        return nursingLevelMapper.selectById(id);
    }

    /**
     * 查询护理等级列表
     * 
     * @param nursingLevel 护理等级
     * @return 护理等级
     */
    @Override
    public List<NursingLevel> selectNursingLevelList(NursingLevel nursingLevel)
    {
        // MyBatis-Plus: 用 LambdaQueryWrapper 动态拼接 WHERE 条件
        // 类似前端: const query = { name: nursingLevel.name, status: nursingLevel.status }
        // 然后 axios.get('/api/list', { params: query })
        LambdaQueryWrapper<NursingLevel> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(nursingLevel.getId() != null, NursingLevel::getId, nursingLevel.getId())
               .like(nursingLevel.getName() != null && !nursingLevel.getName().isEmpty(), 
                     NursingLevel::getName, nursingLevel.getName())
               .eq(nursingLevel.getPlanId() != null, NursingLevel::getPlanId, nursingLevel.getPlanId())
               .eq(nursingLevel.getStatus() != null, NursingLevel::getStatus, nursingLevel.getStatus())
               .like(nursingLevel.getDescription() != null && !nursingLevel.getDescription().isEmpty(),
                     NursingLevel::getDescription, nursingLevel.getDescription());
        // 自动生成 SQL: SELECT * FROM nursing_level WHERE id = ? AND name LIKE ? AND status = ? ...
        return nursingLevelMapper.selectList(wrapper);
    }

    /**
     * 新增护理等级
     * 
     * @param nursingLevel 护理等级
     * @return 结果
     */
    @Override
    public int insertNursingLevel(NursingLevel nursingLevel)
    {
        // MyBatis-Plus: 自动生成 SQL: INSERT INTO nursing_level (name, plan_id, fee, status, ...) VALUES (?, ?, ?, ?, ...)
        // 注意：createTime、createBy、updateTime、updateBy 会由 MyMetaObjectHandler 自动填充，无需手动设置
        return nursingLevelMapper.insert(nursingLevel);
    }

    /**
     * 修改护理等级
     * 
     * @param nursingLevel 护理等级
     * @return 结果
     */
    @Override
    public int updateNursingLevel(NursingLevel nursingLevel)
    {
        // MyBatis-Plus: 自动生成 SQL: UPDATE nursing_level SET name = ?, plan_id = ?, ... WHERE id = ?
        // 注意：updateTime、updateBy 会由 MyMetaObjectHandler 自动填充，无需手动设置
        return nursingLevelMapper.updateById(nursingLevel);
    }

    /**
     * 批量删除护理等级
     * 
     * @param ids 需要删除的护理等级主键
     * @return 结果
     */
    @Override
    public int deleteNursingLevelByIds(Long[] ids)
    {
        // MyBatis-Plus: 自动生成 SQL: DELETE FROM nursing_level WHERE id IN (?, ?, ...)
        return nursingLevelMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除护理等级信息
     * 
     * @param id 护理等级主键
     * @return 结果
     */
    @Override
    public int deleteNursingLevelById(Long id)
    {
        // MyBatis-Plus: 自动生成 SQL: DELETE FROM nursing_level WHERE id = ?
        return nursingLevelMapper.deleteById(id);
    }
}
