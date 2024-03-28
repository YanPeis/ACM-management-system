package com.ruoyi.ojAccount.mapper;

import java.util.List;
import com.ruoyi.ojAccount.domain.OjAccount;

/**
 * ojAccountMapper接口
 * 
 * @author ruoyi
 * @date 2022-04-02
 */
public interface OjAccountMapper 
{
    /**
     * 查询ojAccount
     * 
     * @param userscore ojAccount主键
     * @return ojAccount
     */
    public OjAccount selectOjAccountByUserscore(String userscore);

    /**
     * 查询ojAccount列表
     * 
     * @param ojAccount ojAccount
     * @return ojAccount集合
     */
    public List<OjAccount> selectOjAccountList(OjAccount ojAccount);

    /**
     * 新增ojAccount
     * 
     * @param ojAccount ojAccount
     * @return 结果
     */
    public int insertOjAccount(OjAccount ojAccount);

    /**
     * 修改ojAccount
     * 
     * @param ojAccount ojAccount
     * @return 结果
     */
    public int updateOjAccount(OjAccount ojAccount);

    /**
     * 删除ojAccount
     * 
     * @param userscore ojAccount主键
     * @return 结果
     */
    public int deleteOjAccountByUserscore(String userscore);

    /**
     * 批量删除ojAccount
     * 
     * @param userscores 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOjAccountByUserscores(String[] userscores);
}
