package com.ruoyi.submission.service;

import java.util.List;
import com.ruoyi.submission.domain.submission;

/**
 * submissionService接口
 * 
 * @author ruoyi
 * @date 2022-04-03
 */
public interface ISubmissionService 
{
    /**
     * 查询submission
     * 
     * @param submitid submission主键
     * @return submission
     */
    public submission selectSubmissionBySubmitid(String submitid);

    /**
     * 查询submission列表
     * 
     * @param submission submission
     * @return submission集合
     */
    public List<submission> selectSubmissionList(submission submission);

    /**
     * 新增submission
     * 
     * @param submission submission
     * @return 结果
     */
    public int insertSubmission(submission submission);

    /**
     * 修改submission
     * 
     * @param submission submission
     * @return 结果
     */
    public int updateSubmission(submission submission);

    /**
     * 批量删除submission
     * 
     * @param submitids 需要删除的submission主键集合
     * @return 结果
     */
    public int deleteSubmissionBySubmitids(String[] submitids);

    /**
     * 删除submission信息
     * 
     * @param submitid submission主键
     * @return 结果
     */
    public int deleteSubmissionBySubmitid(String submitid);
}
