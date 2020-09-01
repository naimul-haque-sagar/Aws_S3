package aws.s3ImageStoreController;

import aws.service.EcommerceImageUploadService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

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

    @PostMapping("/get/ecommerce/image")
    public ResponseEntity<byte[]> getEcommerceImage(@RequestParam String bucketName,
                                                    @RequestParam String category,
                                                    @RequestParam String productImageLink) throws IOException {
        byte[] image=ecommerceImageUploadService.getEcommerceImage(bucketName,category,productImageLink);
        return ResponseEntity.status(HttpStatus.OK).
                body(Base64Utils.encode(image));
    }
}
