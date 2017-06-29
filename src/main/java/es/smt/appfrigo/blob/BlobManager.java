package es.smt.appfrigo.blob;




import es.smt.appfrigo.constants.Constants;

public class BlobManager {

	private static BlobManager instance;
	
	public BlobManager(){}
	
	public static BlobManager getInstance()
	{
		if (instance == null )
		{
			instance = new BlobManager();
		}
		return instance;
	}
	
	// Define the connection-string with your values
	public static final String storageConnectionString =
		    "DefaultEndpointsProtocol=http;" +
		    "AccountName=appfrigo;" +
		    "AccountKey=/eoIEaULTKAv9Ax6WXrUMhySEj8+S3BqIMjg7CBEeIk4VD+gAw4ly3RILemRS/ceoDNFjol6LvSsua+Tpf4jZw==";
	

	
	public String uploadProductBlob(String filePath, String fileName, String contentType) 
	{
		String result = BlobService.getInstance().uploadBlob(Constants.productContainer, filePath,fileName, contentType);
		
		return result;
	}
}
