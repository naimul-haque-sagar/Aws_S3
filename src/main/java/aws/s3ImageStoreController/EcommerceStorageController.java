package aws.s3ImageStoreController;

import aws.dto.ImageData;
import aws.service.EcommerceImageUploadService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/ecommerce")
@AllArgsConstructor
@CrossOrigin("*")
public class EcommerceStorageController {
    private final EcommerceImageUploadService ecommerceImageUploadService;

    @PostMapping("/image/upload")
    public ResponseEntity<String> postImage(@RequestParam MultipartFile multipartFile,
                                                              @RequestParam String bucketName, @RequestParam String category){
        return ResponseEntity.status(HttpStatus.OK).body(ecommerceImageUploadService.postImage(multipartFile,bucketName,category));
    }
}
