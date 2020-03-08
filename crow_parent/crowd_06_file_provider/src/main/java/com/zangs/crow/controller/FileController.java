package com.zangs.crow.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import com.zangs.crow.common.configpropertys.AliyunOSSSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@RestController
public class FileController {
    @Autowired
    AliyunOSSSettings oSSSettings;

    public void ossPut(String objectName, InputStream inputStream) throws FileNotFoundException {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = oSSSettings.getEndPoint();
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = oSSSettings.getAccessKeyID();
        String accessKeySecret = oSSSettings.getAccessKeySecret();

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);


        // 上传文件流。

// <yourObjectName>表示上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        inputStream = new FileInputStream("<yourlocalFile>");
        PutObjectResult putObjectResult = ossClient.putObject(oSSSettings.getCrowBucketName(), objectName, inputStream);

// 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
// ObjectMetadata metadata = new ObjectMetadata();
// metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
// metadata.setObjectAcl(CannedAccessControlList.Private);
// putObjectRequest.setMetadata(metadata);

/* 上传字符串。
 // 创建PutObjectRequest对象。
 String content = "Hello OSS";
 PutObjectRequest putObjectRequest = new PutObjectRequest(oSSSettings.getCrowBucketName(), "<yourObjectName>", new ByteArrayInputStream(content.getBytes()));
 ossClient.putObject(putObjectRequest);
 */
// 关闭OSSClient。
        ossClient.shutdown();
    }
}
