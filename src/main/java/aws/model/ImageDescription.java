package aws.model;

import lombok.Data;

import java.util.UUID;

@Data
public class ImageDescription {
    private String category;
    private String productName;
    private String productPrice;
    private String productDescription;
    private String imageLink;
}
