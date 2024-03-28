package com.ruoyi.util;

import com.alibaba.fastjson.JSON;
import com.ruoyi.pojo.CodeforcesEntity;
import com.ruoyi.pojo.CodeforcesEntity.ResultDTO;
import com.ruoyi.submission.domain.submission;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CodeforecesSpider {
    private static final String uri = "https://codeforces.com/api/user.status";
    private static final String webSite = "https://codeforces.com/problemset/problem/";

    private static PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();

    static {
        cm.setMaxTotal(100);
        cm.setDefaultMaxPerRoute(10);
    }

    private static List<ResultDTO> getAllCodeforcesStatus(String username) {
        CloseableHttpClient build = HttpClients.custom().setConnectionManager(cm).build();
        CloseableHttpResponse execute = null;
        try {
            URI handle = new URIBuilder(uri).setParameter("handle", username).build();
            execute = build.execute(new HttpGet(
                    handle));
            if (execute.getStatusLine().getStatusCode() == 200) {
                CodeforcesEntity codeforcesEntity = JSON.parseObject(EntityUtils.toString(execute.getEntity(), "utf-8"), CodeforcesEntity.class);
                return codeforcesEntity.getResult();
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        } finally {
            if (execute != null) {
                try {
                    execute.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    public static List<submission> getSubmissions(String username, String userScore) {
        List<ResultDTO> allCodeforcesStatus = getAllCodeforcesStatus(username);
        List<submission> submissions = new ArrayList<>();
        if (allCodeforcesStatus == null)
            return null;
        for (ResultDTO codeforcesStatus : allCodeforcesStatus) {
            submissions.add(new submission("cf" + codeforcesStatus.getId(), new Date(codeforcesStatus.getCreationTimeSeconds() * 1000L)
                    , codeforcesStatus.getVerdict(), codeforcesStatus.getProgrammingLanguage(),
                    codeforcesStatus.getMemoryConsumedBytes().longValue(),
                    codeforcesStatus.getAuthor().getMembers().get(0).getHandle(),
                    userScore, webSite + codeforcesStatus.getProblem().getContestId()
                    + "/" + codeforcesStatus.getProblem().getIndex(),
                    codeforcesStatus.getProblem().getName()
            ));
        }
        return submissions;
    }

}
