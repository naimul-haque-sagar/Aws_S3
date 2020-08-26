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

    public byte[] getImage(String pathName) {
        try {
            //second argument is the image link
            S3Object s3Object=amazonS3.getObject(pathName,"hero-bg.jpg-07c869db-2941-465e-9876-25ce10d1ed75");
            return IOUtils.toByteArray(s3Object.getObjectContent());
        } catch (AmazonServiceException|IOException e) {
            System.out.println(e);
            throw new IllegalStateException("Failed to get image");
        }
    }
}
