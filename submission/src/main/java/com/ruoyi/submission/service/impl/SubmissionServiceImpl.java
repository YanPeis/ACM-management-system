package com.ruoyi.submission.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.submission.mapper.SubmissionMapper;
import com.ruoyi.submission.domain.submission;
import com.ruoyi.submission.service.ISubmissionService;

/**
 * submissionService业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-03
 */
@Service
public class SubmissionServiceImpl implements ISubmissionService 
{
    @Autowired
    private SubmissionMapper submissionMapper;

    /**
     * 查询submission
     * 
     * @param submitid submission主键
     * @return submission
     */
    @Override
    public submission selectSubmissionBySubmitid(String submitid)
    {
        return submissionMapper.selectSubmissionBySubmitid(submitid);
    }

    /**
     * 查询submission列表
     * 
     * @param submission submission
     * @return submission
     */
    @Override
    public List<submission> selectSubmissionList(submission submission)
    {
        return submissionMapper.selectSubmissionList(submission);
    }

    /**
     * 新增submission
     * 
     * @param submission submission
     * @return 结果
     */
    @Override
    public int insertSubmission(submission submission)
    {
        return submissionMapper.insertSubmission(submission);
    }

    /**
     * 修改submission
     * 
     * @param submission submission
     * @return 结果
     */
    @Override
    public int updateSubmission(submission submission)
    {
        return submissionMapper.updateSubmission(submission);
    }

    /**
     * 批量删除submission
     * 
     * @param submitids 需要删除的submission主键
     * @return 结果
     */
    @Override
    public int deleteSubmissionBySubmitids(String[] submitids)
    {
        return submissionMapper.deleteSubmissionBySubmitids(submitids);
    }

    /**
     * 删除submission信息
     * 
     * @param submitid submission主键
     * @return 结果
     */
    @Override
    public int deleteSubmissionBySubmitid(String submitid)
    {
        return submissionMapper.deleteSubmissionBySubmitid(submitid);
    }
}
