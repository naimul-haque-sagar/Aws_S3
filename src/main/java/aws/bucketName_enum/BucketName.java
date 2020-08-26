package aws.bucketName_enum;

public enum BucketName {

    PROFILE_IMAGE("spring-aws-s3");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
