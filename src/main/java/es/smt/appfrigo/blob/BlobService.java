package es.smt.appfrigo.blob;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;


public class BlobService {

	private static BlobService instance;
	
	public BlobService(){}
	
	public static BlobService getInstance()
	{
		if (instance == null )
		{
			instance = new BlobService();
		}
		return instance;
	}
	
	// Define the connection-string with your values
	public static final String storageConnectionString =
		    "DefaultEndpointsProtocol=http;" +
		    "AccountName=appfrigo;" +
		    "AccountKey=/eoIEaULTKAv9Ax6WXrUMhySEj8+S3BqIMjg7CBEeIk4VD+gAw4ly3RILemRS/ceoDNFjol6LvSsua+Tpf4jZw==";
	
	public String uploadBlob(String containerName, String filePath, String fileName, String contentType) 
	{
		try
		{
		    // Retrieve storage account from connection-string.
		    CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);
		    
		    // Create the blob client.
		    CloudBlobClient blobClient = storageAccount.createCloudBlobClient();

		   // Retrieve reference to a previously created container.
		    CloudBlobContainer container = blobClient.getContainerReference(containerName);

		    // Create or overwrite the "myimage.jpg" blob with contents from a local file.
		    CloudBlockBlob blob = container.getBlockBlobReference(fileName);
		   
		    blob.getProperties().setContentType(contentType);
		    
		    File source = new File(filePath);

		    // Uploading stream to the blob.
		    InputStream stream = new FileInputStream( source);
		    
//		    blob.getProperties().setCacheControl("max-age=3600, must-revalidate");
		    blob.getProperties().setCacheControl("private, no-cache");
		   
//		    blob.getProperties().set
		    stream.skip(0);
		    blob.upload(stream, stream.available());
		    blob.getProperties().setCacheControl("max-age=3600, must-revalidate");
		    
		    String pas = storageAccount.getBlobStorageUri().getPrimaryUri().toString()+blob.getUri().getPath();
		    
		    return pas;
		}
		catch (Exception e)
		{
		    // Output the stack trace.
		    e.printStackTrace();
		    
		    return null;
		}
	}
}
