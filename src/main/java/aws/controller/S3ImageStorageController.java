package aws.controller;

import aws.service.S3ImageStorageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/aws/s3")
@CrossOrigin("*")
@AllArgsConstructor
public class S3ImageStorageController {
    private final S3ImageStorageService s3ImageStorageService;

    @PostMapping("/image/save")
    public ResponseEntity<String> storeImageToBucket(@RequestParam MultipartFile multipartFile){
        return ResponseEntity.status(HttpStatus.OK)
                .body(s3ImageStorageService.storeToBucket(multipartFile));
    }

    @GetMapping("/image/get")
    public ResponseEntity<byte[]> getImage(){
        return ResponseEntity.status(HttpStatus.OK).body(s3ImageStorageService.getImage());
    }
}
