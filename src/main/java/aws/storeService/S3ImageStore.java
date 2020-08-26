package aws.storeService;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
}
