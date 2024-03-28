package com.ruoyi.util;

import com.alibaba.fastjson.JSON;
import com.ruoyi.pojo.VjudgeEntity;
import com.ruoyi.pojo.VjudgeEntity.DataDTO;
import com.ruoyi.submission.domain.submission;
import lombok.SneakyThrows;
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

public class VjudgeSpider {
    public static final String uri = "https://vjudge.csgrandeur.cn/status/data";
    public static final String webSite = "https://vjudge.net/problem/";
    private static final PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();

    static{
        cm.setMaxTotal(100);
        cm.setDefaultMaxPerRoute(10);
    }
    public static List<DataDTO> getAllVjudgeEntity(String username, int start, int length)
    {
        CloseableHttpClient build = HttpClients.custom().setConnectionManager(cm).build();

        CloseableHttpResponse execute = null;
        try {
            URI handle = new URIBuilder(uri).setParameter("start", String.valueOf(start))
                    .setParameter("length", String.valueOf(length))
                    .setParameter("un", username).build();
            execute = build.execute(new HttpGet(
                    handle));
            if (execute.getStatusLine().getStatusCode() == 200) {
                VjudgeEntity vjudgeEntity = JSON.parseObject(EntityUtils.toString(execute.getEntity(), "utf-8"), VjudgeEntity.class);
                return vjudgeEntity.getData();
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
    @SneakyThrows
    public static List<submission> getSubmissions(String username, String userScore) {
        List<submission> submissions = new ArrayList<>();
        int length = 20;
        for(int i = 0; i <= 100; i += 20)
        {
            List<DataDTO> allVjudgeEntity = getAllVjudgeEntity(username, i, length);
//            Thread.sleep(2000);
            if (allVjudgeEntity == null)
                break;
            for (DataDTO dataDTO : allVjudgeEntity) {
                submissions.add(
                        new submission("vj" + dataDTO.getRunId(), new Date(dataDTO.getTime()),dataDTO.getStatus(),
                                dataDTO.getLanguage(), dataDTO.getMemory() * 1024L,
                                dataDTO.getUserName(), userScore, webSite + dataDTO.getOj() + "-" + dataDTO.getProbNum(),
                                dataDTO.getOj() + "-" + dataDTO.getProbNum()
                                )
                );
            }
        }
        return submissions;

    }


}
