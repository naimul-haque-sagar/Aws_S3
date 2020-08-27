package aws.storeService;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class S3BucketOperation {
    private final AmazonS3 amazonS3;

    public void createBucket(String bucketName) {
        amazonS3.createBucket(bucketName);
    }

    public String getBucketLocation(String bucketName) {
        return amazonS3.getBucketLocation(bucketName);
    }

    public List<Bucket> getListOfBucket(){
        return amazonS3.listBuckets();
    }

    public boolean bucketExists(String bucketName) {
        return amazonS3.doesBucketExistV2(bucketName);
    }

    public void deleteBucket(String bucketName) {
        amazonS3.deleteBucket(bucketName);
    }
}
