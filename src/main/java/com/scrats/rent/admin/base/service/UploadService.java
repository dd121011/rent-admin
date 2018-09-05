package com.scrats.rent.admin.base.service;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.scrats.rent.admin.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class UploadService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${qiniu.ak}")
    private String ak;
    @Value("${qiniu.sk}")
    private String sk;
    @Value("${qiniu.bucket}")
    private String bucket;
    @Value("${qiniu.domain}")
    private String domain;


    public String upload(MultipartFile file) throws IOException {
        if(file.isEmpty()){
            throw new BusinessException("上传文件为空");
        }
        String fileName = file.getOriginalFilename();
        fileName = UUID.randomUUID().toString().replace("-","") + fileName.substring(fileName.lastIndexOf("."));
        //创建上传对象
        UploadManager uploadManager = new UploadManager(new Configuration());
        try{
            uploadManager.put(file.getBytes(), fileName, getQiniuToken());
            //Response result =uploadManager.put(file.getBytes(), fileName, token);
            //解析上传成功的结果
            //DefaultPutRet putRet = new Gson().fromJson(result.bodyString(), DefaultPutRet.class);
            //System.out.println(putRet.key);
            //System.out.println(putRet.hash);
        }catch (QiniuException ex) {
            Response r = ex.response;
            log.error(r.toString());
            try {
                log.error(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
            return null;
        }
        return domain + fileName;
    }

    public List<String> batchUpload(List<MultipartFile> files) throws IOException {
        List<String> fileList = new ArrayList<>();
        for (MultipartFile file : files) {
            fileList.add(upload(file));
        }
        return fileList;
    }

    public String getQiniuToken() {
        //密匙配置
        Auth auth = Auth.create(ak, sk);
        //简单上传
        return auth.uploadToken(bucket);
    }
}
