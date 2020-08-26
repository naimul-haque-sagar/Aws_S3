package aws.service;

import aws.repo.S3ImageStorageRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class S3ImageStorageService {
    private final S3ImageStorageRepo s3ImageStorageRepo;

    public String storeToBucket(MultipartFile multipartFile) {
        //is empty
        //is image
        //get metadata
        //make link
        //store
        s3ImageStorageRepo.saveImageInBucket();

        return "";
    }
}
