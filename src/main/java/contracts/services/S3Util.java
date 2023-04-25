package contracts.services;

import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.waiters.WaiterResponse;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.waiters.S3Waiter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class S3Util {
    private static final String BUCKET = "java-jsp";
    public static final String BUCKET_URL = "https://java-jsp.s3.eu-central-1.amazonaws.com";

    public static List<String> getFiles(Integer id)
            throws S3Exception, AwsServiceException, SdkClientException, IOException {

        S3Client client = S3Client.builder().build();

        ListObjectsRequest request = ListObjectsRequest.builder()
                .bucket(BUCKET)
                .prefix("Id_"+ id)
                .build();

        ListObjectsResponse response = client.listObjects(request);
        List<S3Object> objects = response.contents();

        ListIterator<S3Object> listIterator = objects.listIterator();

        List<String> urls = new ArrayList<>();
        while (listIterator.hasNext()) {
            S3Object object = listIterator.next();
            String url = BUCKET_URL + "/" + object.key();
            if(!url.equals(BUCKET_URL + "/" + "Id_" + id + "/")){
                urls.add(url);
            }
        }
        return urls;
    }

    public static void uploadFiles(String id, String fileName, InputStream inputStream) throws IOException {
        S3Client client = S3Client.builder().build();
        String key = "Id_" + id + "/" + fileName;

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(BUCKET)
                .key(key)
                .acl("public-read")
                .build();

            client.putObject(request,
                    RequestBody.fromInputStream(inputStream, inputStream.available()));

        S3Waiter waiter = client.waiter();
        HeadObjectRequest requestWait = HeadObjectRequest.builder().bucket(BUCKET).key(key).build();

        WaiterResponse<HeadObjectResponse> waiterResponse = waiter.waitUntilObjectExists(requestWait);

        waiterResponse.matched().response().ifPresent(System.out::println);

        System.out.println("File " + fileName + " was uploaded.");
    }

    public static void deleteFiles(List<String> files, String id){
        S3Client client = S3Client.builder().build();

        List<ObjectIdentifier> listObjects = new ArrayList<>();

        String key = "Id_" + id + "/";
        for (String file : files) {
            listObjects.add(ObjectIdentifier.builder().key(key + file).build());
        }

        DeleteObjectsRequest request = DeleteObjectsRequest.builder()
                .bucket(BUCKET)
                .delete(Delete.builder().objects(listObjects).build())
                .build();

        DeleteObjectsResponse response = client.deleteObjects(request);

        System.out.println("Deleted: " + response.hasDeleted());

        if(IsEmptyFolder(id)){
            DeleteObjectRequest requestFolder = DeleteObjectRequest.builder()
                    .bucket(BUCKET)
                    .key("Id_" + id)
                    .build();
            client.deleteObject(requestFolder);
        }

    }

    private static boolean isExists(String id) {
        S3Client client = S3Client.builder().build();

        ListObjectsRequest request = ListObjectsRequest.builder()
                .bucket(BUCKET)
                .prefix("Id_"+ id)
                .build();

        ListObjectsResponse response = client.listObjects(request);
        List<S3Object> objects = response.contents();

        return objects.size() > 0;
    }

    private static boolean IsEmptyFolder(String id){
        S3Client client = S3Client.builder().build();

        ListObjectsRequest request = ListObjectsRequest.builder()
                .bucket(BUCKET)
                .prefix("Id_"+ id).build();

        ListObjectsResponse response = client.listObjects(request);
        List<S3Object> objects = response.contents();
        return objects.size() > 0;
    }
}
