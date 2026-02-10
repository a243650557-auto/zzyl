package com.zzyl.framework.config;

import java.util.Date;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.zzyl.common.utils.DateUtils;
import com.zzyl.common.utils.SecurityUtils;
import com.zzyl.common.utils.StringUtils;

/**
 * MyBatis-Plus 自动填充处理器
 * 
 * 作用：在插入或更新数据时，自动填充 createBy、createTime、updateBy、updateTime 字段
 * 
 * @author ruoyi
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler
{
    /**
     * 插入时的填充策略
     * 
     * @param metaObject 元对象（可以理解为要插入的实体对象）
     */
    @Override
    public void insertFill(MetaObject metaObject)
    {
        // 获取当前登录用户名（如果获取失败，使用 "system" 作为默认值）
        String username = getUsernameSafely();
        Date now = DateUtils.getNowDate();
        
        // 插入时自动填充：只有当字段为 null 时才填充
        // createTime: 插入时自动填充
        Object createTime = metaObject.getValue("createTime");
        if (createTime == null)
        {
            this.setFieldValByName("createTime", now, metaObject);
        }
        
        // createBy: 插入时自动填充
        Object createBy = metaObject.getValue("createBy");
        if (createBy == null)
        {
            this.setFieldValByName("createBy", username, metaObject);
        }
        
        // updateTime: 插入和更新时都自动填充
        Object updateTime = metaObject.getValue("updateTime");
        if (updateTime == null)
        {
            this.setFieldValByName("updateTime", now, metaObject);
        }
        
        // updateBy: 插入和更新时都自动填充
        Object updateBy = metaObject.getValue("updateBy");
        if (updateBy == null)
        {
            this.setFieldValByName("updateBy", username, metaObject);
        }
    }

    /**
     * 更新时的填充策略
     * 
     * @param metaObject 元对象（可以理解为要更新的实体对象）
     */
    @Override
    public void updateFill(MetaObject metaObject)
    {
        // 获取当前登录用户名（如果获取失败，使用 "system" 作为默认值）
        String username = getUsernameSafely();
        Date now = DateUtils.getNowDate();
        
        // 更新时强制填充：无论字段是否为空，更新时都强制填充
        // updateTime: 更新时强制填充当前时间
        this.setFieldValByName("updateTime", now, metaObject);
        
        // updateBy: 更新时强制填充当前登录用户名
        this.setFieldValByName("updateBy", username, metaObject);
    }

    /**
     * 安全地获取当前登录用户名
     * 如果获取失败（比如未登录、定时任务等场景），返回 "system"
     * 
     * @return 当前登录用户名，获取失败时返回 "system"
     */
    private String getUsernameSafely()
    {
        try
        {
            String username = SecurityUtils.getUsername();
            return StringUtils.isNotBlank(username) ? username : "system";
        }
        catch (Exception e)
        {
            // 如果获取用户信息失败（比如定时任务、系统初始化等场景），使用默认值
            return "system";
        }
    }
}
