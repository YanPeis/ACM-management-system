package com.ruoyi.ojAccount.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * ojAccount对象 ojAccount
 * 
 * @author ruoyi
 * @date 2022-04-02
 */
public class OjAccount extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户学号 */
    @Excel(name = "用户学号")
    private String userscore;

    /** cf账号 */
    @Excel(name = "cf账号")
    private String codeforceusername;

    /** vjudge账号 */
    @Excel(name = "vjudge账号")
    private String vjudgeusername;

    public void setUserscore(String userscore) 
    {
        this.userscore = userscore;
    }

    public String getUserscore() 
    {
        return userscore;
    }
    public void setCodeforceusername(String codeforceusername) 
    {
        this.codeforceusername = codeforceusername;
    }

    public String getCodeforceusername() 
    {
        return codeforceusername;
    }
    public void setVjudgeusername(String vjudgeusername) 
    {
        this.vjudgeusername = vjudgeusername;
    }

    public String getVjudgeusername() 
    {
        return vjudgeusername;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userscore", getUserscore())
            .append("codeforceusername", getCodeforceusername())
            .append("vjudgeusername", getVjudgeusername())
            .toString();
    }
}
