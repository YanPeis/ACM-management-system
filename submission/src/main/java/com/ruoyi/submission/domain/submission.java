package com.ruoyi.submission.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * submission对象 submission
 * 
 * @author ruoyi
 * @date 2022-04-03
 */
public class submission extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 题目id */
    private String submitid;

    /** 提交时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "提交时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submittime;

    /** 题目状态 */
    @Excel(name = "题目状态")
    private String verdict;

    /** 编程语言 */
    @Excel(name = "编程语言")
    private String programminglanguage;

    /** 内存使用量 */
    @Excel(name = "内存使用量")
    private Long memoryconsumed;

    /** 作者 */
    @Excel(name = "作者")
    private String author;

    /** 用户学号 */
    @Excel(name = "用户学号")
    private String userscore;

    public submission() {
    }

    public submission(String submitid, Date submittime, String verdict, String programminglanguage, Long memoryconsumed, String author, String userscore, String problemweb, String problemname) {
        this.submitid = submitid;
        this.submittime = submittime;
        this.verdict = verdict;
        this.programminglanguage = programminglanguage;
        this.memoryconsumed = memoryconsumed;
        this.author = author;
        this.userscore = userscore;
        this.problemweb = problemweb;
        this.problemname = problemname;
    }

    /** 题目网站 */
    @Excel(name = "题目网站")
    private String problemweb;

    /** 题目名称 */
    @Excel(name = "题目名称")
    private String problemname;

    public void setSubmitid(String submitid) 
    {
        this.submitid = submitid;
    }

    public String getSubmitid() 
    {
        return submitid;
    }
    public void setSubmittime(Date submittime) 
    {
        this.submittime = submittime;
    }

    public Date getSubmittime() 
    {
        return submittime;
    }
    public void setVerdict(String verdict) 
    {
        this.verdict = verdict;
    }

    public String getVerdict() 
    {
        return verdict;
    }
    public void setProgramminglanguage(String programminglanguage) 
    {
        this.programminglanguage = programminglanguage;
    }

    public String getProgramminglanguage() 
    {
        return programminglanguage;
    }
    public void setMemoryconsumed(Long memoryconsumed) 
    {
        this.memoryconsumed = memoryconsumed;
    }

    public Long getMemoryconsumed() 
    {
        return memoryconsumed;
    }
    public void setAuthor(String author) 
    {
        this.author = author;
    }

    public String getAuthor() 
    {
        return author;
    }
    public void setUserscore(String userscore) 
    {
        this.userscore = userscore;
    }

    public String getUserscore() 
    {
        return userscore;
    }
    public void setProblemweb(String problemweb) 
    {
        this.problemweb = problemweb;
    }

    public String getProblemweb() 
    {
        return problemweb;
    }
    public void setProblemname(String problemname) 
    {
        this.problemname = problemname;
    }

    public String getProblemname() 
    {
        return problemname;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("submitid", getSubmitid())
            .append("submittime", getSubmittime())
            .append("verdict", getVerdict())
            .append("programminglanguage", getProgramminglanguage())
            .append("memoryconsumed", getMemoryconsumed())
            .append("author", getAuthor())
            .append("userscore", getUserscore())
            .append("problemweb", getProblemweb())
            .append("problemname", getProblemname())
            .toString();
    }
}
