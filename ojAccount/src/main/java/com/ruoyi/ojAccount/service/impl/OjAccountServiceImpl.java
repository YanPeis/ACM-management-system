package com.ruoyi.ojAccount.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.ojAccount.mapper.OjAccountMapper;
import com.ruoyi.ojAccount.domain.OjAccount;
import com.ruoyi.ojAccount.service.IOjAccountService;

/**
 * ojAccountService业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-02
 */
@Service
public class OjAccountServiceImpl implements IOjAccountService 
{
    @Autowired
    private OjAccountMapper ojAccountMapper;

    /**
     * 查询ojAccount
     * 
     * @param userscore ojAccount主键
     * @return ojAccount
     */
    @Override
    public OjAccount selectOjAccountByUserscore(String userscore)
    {
        return ojAccountMapper.selectOjAccountByUserscore(userscore);
    }

    /**
     * 查询ojAccount列表
     * 
     * @param ojAccount ojAccount
     * @return ojAccount
     */
    @Override
    public List<OjAccount> selectOjAccountList(OjAccount ojAccount)
    {
        return ojAccountMapper.selectOjAccountList(ojAccount);
    }

    /**
     * 新增ojAccount
     * 
     * @param ojAccount ojAccount
     * @return 结果
     */
    @Override
    public int insertOjAccount(OjAccount ojAccount)
    {
        return ojAccountMapper.insertOjAccount(ojAccount);
    }

    /**
     * 修改ojAccount
     * 
     * @param ojAccount ojAccount
     * @return 结果
     */
    @Override
    public int updateOjAccount(OjAccount ojAccount)
    {
        return ojAccountMapper.updateOjAccount(ojAccount);
    }

    /**
     * 批量删除ojAccount
     * 
     * @param userscores 需要删除的ojAccount主键
     * @return 结果
     */
    @Override
    public int deleteOjAccountByUserscores(String[] userscores)
    {
        return ojAccountMapper.deleteOjAccountByUserscores(userscores);
    }

    /**
     * 删除ojAccount信息
     * 
     * @param userscore ojAccount主键
     * @return 结果
     */
    @Override
    public int deleteOjAccountByUserscore(String userscore)
    {
        return ojAccountMapper.deleteOjAccountByUserscore(userscore);
    }
}
