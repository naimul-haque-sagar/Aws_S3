package aws.model;

import lombok.Data;

import java.util.UUID;

@Data
public class ImageDescription {
    private String name;
    private String description;
    private String imageLink;
}
