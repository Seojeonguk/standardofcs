package com.js.freeproject.domain.amazonS3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.js.freeproject.domain.file.domain.BoardFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3Service {
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public BoardFile upload(MultipartFile multipartFile, String dirName) throws IOException {
        File uploadFile = convert(multipartFile).orElseThrow(
                () -> new IllegalArgumentException("MultipartFile => File 변환에 실패했습니다.")
        );
        return upload(uploadFile, dirName);
    }


    public String uploadImage(MultipartFile multipartFile, String dirName) throws IOException {
        File uploadFile = convert(multipartFile).orElseThrow(
                () -> new IllegalArgumentException("MultipartFile => File 변환에 실패했습니다.")
        );
        return uploadImage(uploadFile, dirName);
    }


    public void deleteFile(String fileName) {
        try {
            DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(this.bucket, "board/" + fileName);
            this.amazonS3Client.deleteObject(deleteObjectRequest);
        } catch (AmazonS3Exception e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }

    }

    private BoardFile upload(File uploadFile, String dirName) {
        String fileName = dirName
                + "/"
                + UUID.randomUUID().toString()
                + "."
                + FilenameUtils.getExtension(uploadFile.getName());
        String uploadUrl = putS3(uploadFile, fileName);

        BoardFile dto = BoardFile.builder()
                .name(uploadUrl)
//                .name(uploadUrl.substring(uploadUrl.lastIndexOf("/") + 1, uploadUrl.lastIndexOf(".")))
//                .imageExtension(FilenameUtils.getExtension(uploadFile.getName()))
                .build();

        removeNewFile(uploadFile);
        return dto;
    }

    private String uploadImage(File uploadFile, String dirName) {
        String fileName = dirName
                + "/"
                + UUID.randomUUID().toString()
                + "."
                + FilenameUtils.getExtension(uploadFile.getName());
        String uploadUrl = putS3(uploadFile, fileName);
        removeNewFile(uploadFile);
        return uploadUrl;
    }

    private String putS3(File uploadFile, String fileName) {
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("파일이 삭제되었습니다.");
        } else {
            log.info("파일이 삭제되지 못했습니다.");
        }
    }

    private Optional<File> convert(MultipartFile multipartFile) throws IOException {
        File convertedFile = new File(multipartFile.getOriginalFilename());
        System.out.println(convertedFile);
        boolean success = convertedFile.createNewFile();
        System.out.println(success);
        if (success) {
            try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
                fos.write(multipartFile.getBytes());
            }
            return Optional.of(convertedFile);
        }
        return Optional.empty();
    }

    public boolean validateType(MultipartFile file) {
        String mimeType = file.getContentType();
        if (mimeType.contains("image/")) {
            return true;
        }
        return false;
    }
}
