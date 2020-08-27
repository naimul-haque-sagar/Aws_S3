package aws.storeService;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class S3ImageStore {
    private final AmazonS3 amazonS3;

    public void saveImageInBucket(String pathName, String imageName, Optional<Map<String, String>> imageMetadata, InputStream inputStream) {
        ObjectMetadata objectMetadata=new ObjectMetadata();
        imageMetadata.ifPresent(map->{
            if(!map.isEmpty()){
                map.forEach(objectMetadata::addUserMetadata);
            }
        });
        amazonS3.putObject(pathName,imageName,inputStream,objectMetadata);
    }

    public byte[] getImage(String pathName,String imageLink) {
        try {
            S3Object s3Object=amazonS3.getObject(pathName,imageLink);
            return IOUtils.toByteArray(s3Object.getObjectContent());
        } catch (AmazonServiceException|IOException e) {
            System.out.println(e);
            throw new IllegalStateException("Failed to get image");
        }
    }

    public void postImage(String pathName, String imageLink, Optional<Map<String, String>> imageMetadata, InputStream inputStream) {
        ObjectMetadata objectMetadata=new ObjectMetadata();
        imageMetadata.ifPresent(map->{
            if(!map.isEmpty()){
                map.forEach(objectMetadata::addUserMetadata);
            }
        });
        amazonS3.putObject(pathName,imageLink,inputStream,objectMetadata);
    }
}
