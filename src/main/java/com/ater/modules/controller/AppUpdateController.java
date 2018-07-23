package com.ater.modules.controller;



import com.alibaba.fastjson.JSON;
import com.ater.common.fastdfs.FileUtil;
import com.ater.common.utils.JSONUtils;
import com.ater.common.utils.R;
import com.google.common.collect.Maps;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 任务生成
 *
 * @author Yam
 * @email
 * @date
 */
@RestController



@RequestMapping("/appUpdate")
@Api(value = "app更新", description = "app更新版本")
public class AppUpdateController {
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AppUpdateController .class);
    @Value("${upload_path}")
    private String uploadPath;
    @Value("${upload_dir_apk}")
    private String uploadDirApk;
    // check version
    @ApiOperation("app更新")
    @RequestMapping(value = "/neworder", method = RequestMethod.POST)
    public String appUpdate(@ApiParam(value = "version 版本号", required = true)   @RequestBody Map<String,Object> params) {
      //  logger.info("\n\n------------Check_APP_Version_9104 debug info-------------\n请求数据包信息:" + request.json.toString());
        Map<String, Object> map = Maps.newHashMap();
       String version=(String)params.get("version");
        File file = new File(uploadDirApk);
        String currentVersion = FileUtil.readFile(file).replaceAll("null","").trim();
        logger.info("当前APP版本:[" + currentVersion + "]");
        if(!version.isEmpty() && !currentVersion.isEmpty() && (!version.equals(currentVersion))){
            map.put("resultMsg", "获取成功");
            map.put("resultCode", 200);
            map.put("newVersion", currentVersion); // 返回最新版本
            map.put("url", uploadPath + currentVersion + ".wgt");// 返回wgt文件下载地址
       }else if(!version.isEmpty() && !currentVersion.isEmpty() && (version.equals(currentVersion))){
            map.put("resultMsg", "当前已是最新版本");
            map.put("resultCode", 405);
         }else{
            map.put("resultMsg", "版本信息查询失败");
            map.put("resultCode", 404);
         }
        return JSONUtils.toJson(map);
    }


    @ApiOperation("调评分接口")
    @RequestMapping(value = "/autoScore", method = RequestMethod.POST)
    public  String autoScore(@ApiParam(value = "请求的参数", required = true)  @RequestBody  Map<String,Object> params){
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        JSONObject jsonObject = null;
        String parameters= JSON.toJSONString(params);
      //  String url="http://www.luckcome.cn/autoscore?uid=zhangmin&pwd=zhangmin123&gestage=30&sctype=1&selfhr=1&detail=0";
       // String url="http://193.112.141.156/autoscore?uid=zhangmin&pwd=zhangmin123&gestage=30&sctype=3&selfhr=1&detail=0";
        try {
            //请求发起客户端
            httpclient = HttpClients.createDefault();
            //参数集合
            List postParams = new ArrayList();
            //遍历参数并添加到集合
//            for(Map.Entry<String, Object> entry:params.entrySet()){
//                postParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
//            }
            //通过post方式访问
            String urll= (String) params.get("urll");
            HttpPost post = new HttpPost(urll);
            post.addHeader("Content-type", "application/json; charset=utf-8");
            post.setHeader("Accept", "application/json");
            post.setEntity(new StringEntity(parameters, Charset.forName("UTF-8")));
       //     post.setEntity(new StringEntity(canshu, Charset.forName("UTF-8")));
         //   HttpEntity paramEntity = new UrlEncodedFormEntity(postParams,"UTF-8");
     //       post.setEntity(paramEntity);
             response = httpclient.execute(post);
            HttpEntity valueEntity = response.getEntity();
            String content = EntityUtils.toString(valueEntity);
            jsonObject = JSONObject.fromObject(content);
            return jsonObject.toString();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }   finally{
            if(httpclient != null){
                try {
                    httpclient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonObject.toString();
    }
}
