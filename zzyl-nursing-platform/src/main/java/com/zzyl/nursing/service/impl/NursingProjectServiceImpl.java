package com.zzyl.nursing.service.impl;

import java.util.Arrays;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzyl.nursing.domain.NursingProject;
import com.zzyl.nursing.mapper.NursingProjectMapper;
import com.zzyl.nursing.service.INursingProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 护理项目Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-02-09
 */
@Service
public class NursingProjectServiceImpl implements INursingProjectService 
{
    @Autowired
    private NursingProjectMapper nursingProjectMapper;

    /**
     * 查询护理项目
     * 
     * @param id 护理项目主键
     * @return 护理项目
     */
    @Override
    public NursingProject selectNursingProjectById(Long id)
    {
        // MyBatis-Plus: 自动生成 SQL: SELECT * FROM nursing_project WHERE id = ?
        return nursingProjectMapper.selectById(id);
    }

    /**
     * 查询护理项目列表
     * 
     * @param nursingProject 护理项目
     * @return 护理项目
     */
    @Override
    public List<NursingProject> selectNursingProjectList(NursingProject nursingProject)
    {
        // MyBatis-Plus: 用 LambdaQueryWrapper 动态拼接 WHERE 条件
        LambdaQueryWrapper<NursingProject> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(nursingProject.getId() != null, NursingProject::getId, nursingProject.getId())
               .like(nursingProject.getName() != null && !nursingProject.getName().isEmpty(),
                     NursingProject::getName, nursingProject.getName())
               .eq(nursingProject.getStatus() != null, NursingProject::getStatus, nursingProject.getStatus())
               .orderByAsc(nursingProject.getOrderNo() != null, NursingProject::getOrderNo);
        // 自动生成 SQL: SELECT * FROM nursing_project WHERE name LIKE ? AND status = ? ORDER BY order_no ASC
        return nursingProjectMapper.selectList(wrapper);
    }

    /**
     * 新增护理项目
     * 
     * @param nursingProject 护理项目
     * @return 结果
     */
    @Override
    public int insertNursingProject(NursingProject nursingProject)
    {
        // MyBatis-Plus: 自动生成 SQL: INSERT INTO nursing_project (name, order_no, unit, price, ...) VALUES (?, ?, ?, ?, ...)
        // 注意：createTime、createBy、updateTime、updateBy 会由 MyMetaObjectHandler 自动填充，无需手动设置
        return nursingProjectMapper.insert(nursingProject);
    }

    /**
     * 修改护理项目
     * 
     * @param nursingProject 护理项目
     * @return 结果
     */
    @Override
    public int updateNursingProject(NursingProject nursingProject)
    {
        // MyBatis-Plus: 自动生成 SQL: UPDATE nursing_project SET name = ?, price = ?, ... WHERE id = ?
        // 注意：updateTime、updateBy 会由 MyMetaObjectHandler 自动填充，无需手动设置
        return nursingProjectMapper.updateById(nursingProject);
    }

    /**
     * 批量删除护理项目
     * 
     * @param ids 需要删除的护理项目主键
     * @return 结果
     */
    @Override
    public int deleteNursingProjectByIds(Long[] ids)
    {
        // MyBatis-Plus: 自动生成 SQL: DELETE FROM nursing_project WHERE id IN (?, ?, ...)
        return nursingProjectMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除护理项目信息
     * 
     * @param id 护理项目主键
     * @return 结果
     */
    @Override
    public int deleteNursingProjectById(Long id)
    {
        // MyBatis-Plus: 自动生成 SQL: DELETE FROM nursing_project WHERE id = ?
        return nursingProjectMapper.deleteById(id);
    }
}
