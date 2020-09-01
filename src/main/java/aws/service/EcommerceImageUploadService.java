package aws.service;

import aws.dto.ImageData;
import aws.storeService.S3ImageStore;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EcommerceImageUploadService {
    private final S3ImageStore s3ImageStore;

    private final S3ImageStorageService s3ImageStorageService;

    public String postImage(MultipartFile multipartFile, String bucketName,String category) {
        s3ImageStorageService.isEmpty(multipartFile);
        s3ImageStorageService.isImage(multipartFile);
        Map<String, String> imageMetadata = s3ImageStorageService.extractMetadata(multipartFile);
        String pathName = String.format("%s/%s", bucketName, category);
        String imageLink=String.format("%s-%s",multipartFile.getOriginalFilename(), UUID.randomUUID());

        try {
            s3ImageStore.postImage(pathName,imageLink, Optional.of(imageMetadata),multipartFile.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageLink;
    }

    public byte[] getEcommerceImage(String bucketName, String category, String productImageLink) {
        String pathName = String.format("%s/%s", bucketName, category);
        return s3ImageStore.getImage(pathName,productImageLink);
    }
}
