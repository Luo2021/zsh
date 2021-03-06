package cn.zsh.controller;

import cn.zsh.service.impl.UploadServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author luoli
 * @date 2021/5/7 10:11
 * Feature:
 */
@RestController
@RequestMapping("upload")
@Api(tags = "文件上传")
public class UploadController {
    @Autowired
    private UploadServiceImpl uploadServiceImpl;

    /**
     * 图片上传
     * @param file
     * @return
     */
    @ApiOperation(value= "图片上传接口")
    @PostMapping("image")
    public ResponseEntity<String> uploadImage(@RequestParam("file")MultipartFile file){
        String url= this.uploadServiceImpl.upload(file);
        if(StringUtils.isBlank(url)){
            //url为空，证明上传失败
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(url);
    }
}
