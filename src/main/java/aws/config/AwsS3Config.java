package aws.config;

import com.amazonaws.services.s3.AmazonS3Client;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
@ConfigurationProperties("aws")
@Data
public class AwsS3Config {
    private String accessKey;
    private String secretKey;

    @Bean
    public AmazonS3 s3() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey,secretKey);
        AmazonS3 amazonS3 = AmazonS3Client.builder()
                .withRegion("ap-south-1")
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
        return amazonS3;
    }
}
