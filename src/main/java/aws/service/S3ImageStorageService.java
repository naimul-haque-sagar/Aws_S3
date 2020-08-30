package aws.service;

import aws.bucketName_enum.BucketName;
import aws.model.ImageDescription;
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

    public byte[] getImage() {
        return s3ImageStore.getImage(BucketName.PROFILE_IMAGE.getBucketName(),
                "hero-bg.jpg-07c869db-2941-465e-9876-25ce10d1ed75");
    }

    public ImageDescription postImageToBucket(MultipartFile multipartFile, ImageDescription imageDescription) {
        isEmpty(multipartFile);
        isImage(multipartFile);
        Map<String, String> imageMetadata = extractMetadata(multipartFile);
        String pathName=BucketName.PROFILE_IMAGE.getBucketName();
        String imageLink=String.format("%s-%s",multipartFile.getOriginalFilename(),UUID.randomUUID());

        try {
            s3ImageStore.postImage(pathName,imageLink,Optional.of(imageMetadata),multipartFile.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageDescription.setImageLink(imageLink);
        return imageDescription;
    }

    public byte[] getImageByLink(String imageLink, String imagePath) {
        return s3ImageStore.getImage(imagePath,imageLink);
    }

    public Map<String, String> extractMetadata(MultipartFile multipartFile) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", multipartFile.getContentType());
        metadata.put("Content-Length", String.valueOf(multipartFile.getSize()));
        return metadata;
    }

    public void isImage(MultipartFile multipartFile) {
        if(!Arrays.asList(IMAGE_JPEG.getMimeType(),IMAGE_PNG.getMimeType(),IMAGE_GIF.getMimeType()).contains(multipartFile.getContentType())){
            throw new IllegalStateException("Not a image");
        }
    }

    public void isEmpty(MultipartFile multipartFile) {
        if(multipartFile.isEmpty()){
            throw new IllegalStateException("Empty file !!!!!");
        }
    }
}
