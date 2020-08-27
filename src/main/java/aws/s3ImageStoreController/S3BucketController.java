package aws.s3ImageStoreController;

import aws.service.S3BucketService;
import com.amazonaws.services.s3.model.Bucket;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/aws/s3")
@CrossOrigin("*")
public class S3BucketController {
    private final S3BucketService s3BucketService;

    @PostMapping("/create/bucket/{bucketName}")
    public ResponseEntity createBucket(@PathVariable String bucketName){
        s3BucketService.createBucket(bucketName);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/bucket/location/{bucketName}")
    public ResponseEntity<String> getBucketLocation(@PathVariable String bucketName){
        return ResponseEntity.status(HttpStatus.OK).body(s3BucketService.getBucketLocation(bucketName));
    }

    @GetMapping("/bucket/list")
    public ResponseEntity<List<Bucket>> getAllBucketList(){
        return ResponseEntity.status(HttpStatus.OK).body(s3BucketService.getAllBucketList());
    }

    @GetMapping("/bucket/exists/{bucketName}")
    public ResponseEntity<Boolean> bucketExists(@PathVariable String bucketName){
        return ResponseEntity.status(HttpStatus.OK).body(s3BucketService.checkBucketExists(bucketName));
    }

    @DeleteMapping("/delete/bucket/{bucketName}")
    public ResponseEntity deleteBucket(@PathVariable String bucketName){
        s3BucketService.deleteBucket(bucketName);
        return new ResponseEntity(HttpStatus.OK);
    }
}
