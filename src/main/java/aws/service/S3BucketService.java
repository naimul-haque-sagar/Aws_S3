package aws.service;

import aws.storeService.S3BucketOperation;
import com.amazonaws.services.s3.model.Bucket;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class S3BucketService {
    private final S3BucketOperation s3BucketOperation;

    public void createBucket(String bucketName) {
        s3BucketOperation.createBucket(bucketName);
    }

    public String getBucketLocation(String bucketName) {
        return s3BucketOperation.getBucketLocation(bucketName);
    }

    public List<Bucket> getAllBucketList() {
        return s3BucketOperation.getListOfBucket();
    }

    public boolean checkBucketExists(String bucketName) {
        return s3BucketOperation.bucketExists(bucketName);
    }

    public void deleteBucket(String bucketName) {
        s3BucketOperation.deleteBucket(bucketName);
    }
}
