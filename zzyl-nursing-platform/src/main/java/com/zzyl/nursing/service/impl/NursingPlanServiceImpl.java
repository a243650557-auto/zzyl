package com.zzyl.nursing.service.impl;

import java.util.Arrays;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzyl.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zzyl.nursing.mapper.NursingPlanMapper;
import com.zzyl.nursing.domain.NursingPlan;
import com.zzyl.nursing.service.INursingPlanService;

/**
 * 护理计划Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-02-09
 */
@Service
public class NursingPlanServiceImpl implements INursingPlanService 
{
    @Autowired
    private NursingPlanMapper nursingPlanMapper;

    /**
     * 查询护理计划
     * 
     * @param id 护理计划主键
     * @return 护理计划
     */
    @Override
    public NursingPlan selectNursingPlanById(Long id)
    {
        // MyBatis-Plus: 自动生成 SQL: SELECT * FROM nursing_plan WHERE id = ?
        return nursingPlanMapper.selectById(id);
    }

    /**
     * 查询护理计划列表
     * 
     * @param nursingPlan 护理计划
     * @return 护理计划
     */
    @Override
    public List<NursingPlan> selectNursingPlanList(NursingPlan nursingPlan)
    {
        // MyBatis-Plus: 用 LambdaQueryWrapper 动态拼接 WHERE 条件
        LambdaQueryWrapper<NursingPlan> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(nursingPlan.getId() != null, NursingPlan::getId, nursingPlan.getId())
               .like(nursingPlan.getPlanName() != null && !nursingPlan.getPlanName().isEmpty(),
                     NursingPlan::getPlanName, nursingPlan.getPlanName())
               .eq(nursingPlan.getStatus() != null, NursingPlan::getStatus, nursingPlan.getStatus())
               .orderByAsc(nursingPlan.getSortNo() != null, NursingPlan::getSortNo);
        // 自动生成 SQL: SELECT * FROM nursing_plan WHERE plan_name LIKE ? AND status = ? ORDER BY sort_no ASC
        return nursingPlanMapper.selectList(wrapper);
    }

    /**
     * 新增护理计划
     * 
     * @param nursingPlan 护理计划
     * @return 结果
     */
    @Override
    public int insertNursingPlan(NursingPlan nursingPlan)
    {
        nursingPlan.setCreateTime(DateUtils.getNowDate());
        // MyBatis-Plus: 自动生成 SQL: INSERT INTO nursing_plan (plan_name, status, sort_no, ...) VALUES (?, ?, ?, ...)
        return nursingPlanMapper.insert(nursingPlan);
    }

    /**
     * 修改护理计划
     * 
     * @param nursingPlan 护理计划
     * @return 结果
     */
    @Override
    public int updateNursingPlan(NursingPlan nursingPlan)
    {
        nursingPlan.setUpdateTime(DateUtils.getNowDate());
        // MyBatis-Plus: 自动生成 SQL: UPDATE nursing_plan SET plan_name = ?, status = ?, ... WHERE id = ?
        return nursingPlanMapper.updateById(nursingPlan);
    }

    /**
     * 批量删除护理计划
     * 
     * @param ids 需要删除的护理计划主键
     * @return 结果
     */
    @Override
    public int deleteNursingPlanByIds(Long[] ids)
    {
        // MyBatis-Plus: 自动生成 SQL: DELETE FROM nursing_plan WHERE id IN (?, ?, ...)
        return nursingPlanMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除护理计划信息
     * 
     * @param id 护理计划主键
     * @return 结果
     */
    @Override
    public int deleteNursingPlanById(Long id)
    {
        // MyBatis-Plus: 自动生成 SQL: DELETE FROM nursing_plan WHERE id = ?
        return nursingPlanMapper.deleteById(id);
    }
}
