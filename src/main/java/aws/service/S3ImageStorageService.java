package aws.service;

import aws.bucketName_enum.BucketName;
import aws.storeService.S3ImageStore;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import static org.apache.http.entity.ContentType.*;

import java.io.IOException;
import java.util.*;

@Service
@AllArgsConstructor
public class S3ImageStorageService {
    private final S3ImageStore s3ImageStore;

    public String storeToBucket(MultipartFile multipartFile) {
        isEmpty(multipartFile);
        isImage(multipartFile);
        Map<String, String> imageMetadata = extractMetadata(multipartFile);
        String pathName= BucketName.PROFILE_IMAGE.getBucketName();
        String imageName=String.format("%s-%s", multipartFile.getOriginalFilename(), UUID.randomUUID());

        try {
            s3ImageStore.saveImageInBucket(pathName,imageName, Optional.of(imageMetadata),multipartFile.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageName;
    }

    private Map<String, String> extractMetadata(MultipartFile multipartFile) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", multipartFile.getContentType());
        metadata.put("Content-Length", String.valueOf(multipartFile.getSize()));
        return metadata;
    }

    private void isImage(MultipartFile multipartFile) {
        if(!Arrays.asList(IMAGE_JPEG.getMimeType(),IMAGE_PNG.getMimeType(),IMAGE_GIF.getMimeType()).contains(multipartFile.getContentType())){
            throw new IllegalStateException("Not a image");
        }
    }

    private void isEmpty(MultipartFile multipartFile) {
        if(multipartFile.isEmpty()){
            throw new IllegalStateException("Empty file !!!!!");
        }
    }
}
