package cn.zsh.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author luoli
 * @date 2021/5/7 10:11
 * Feature:
 */
public interface UploadService {
    /**
     * 文件上传
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}
