package aws.s3ImageStoreController;

import aws.model.ImageDescription;
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
public class ImageStorageController {
    private final S3ImageStorageService s3ImageStorageService;

    //stores a image in bucket
    @PostMapping("/image/save")
    public ResponseEntity<String> storeImageToBucket(@RequestParam MultipartFile multipartFile){
        return ResponseEntity.status(HttpStatus.OK)
                .body(s3ImageStorageService.storeToBucket(multipartFile));
    }

    //get's a image from bucket
    @GetMapping("/image/get")
    public ResponseEntity<byte[]> getImage(){
        return ResponseEntity.status(HttpStatus.OK).body(s3ImageStorageService.getImage());
    }

    //post image and returns imageLink with a class ImageDescription
    @PostMapping("/image/model/save")
    public ResponseEntity<ImageDescription> postImageToBucket(@RequestParam MultipartFile multipartFile,
                                                              @RequestBody ImageDescription imageDescription){
        return ResponseEntity.status(HttpStatus.OK).body(s3ImageStorageService.postImageToBucket(multipartFile,imageDescription));
    }

    //get a image by using imageLink and imagePath
    @GetMapping("image/get/{imageLink}/{imagePath}")
    public ResponseEntity<byte[]> getImageByLink(@PathVariable String imageLink,@PathVariable String imagePath){
        return ResponseEntity.status(HttpStatus.OK).body(s3ImageStorageService.getImageByLink(imageLink,imagePath));
    }
}
