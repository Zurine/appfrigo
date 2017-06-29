package es.smt.appfrigo.constants;


public class Constants
{
	private static Constants instance;
	
	public Constants() 
	{
	     super();
	}
	 
	public static Constants getInstance()
	{
		if (instance == null )
		{
			instance = new Constants();
		}
		
		return instance;
	}  

	 
	
	//public String trying = System.getProperty("server.url");
//	public static final String serverServices= "";
//	public static final String serverServices= "http://localhost/BeaconProject/";
//	public static final String serverServices = "https://appfrigoprod.trafficmanager.net/";
//	public static final String serverServices = "http://localhost/UnivendingProject/";
//	public static final String serverServices = "http://localhost:1227/";
//	public static final String  serverServices = "http://appfrigo.trafficmanager.net/";

//	 public static final String  serverServices = "";/* = "http://smtdev-appfrigoback.azurewebsites.net/";*/
	
    public static final String logoutRedirect = "redirect:/logout";
	
    public static final String server = "server.url";


    
	 public static final int appFrigo = 1;
     public static final int smt = 2;
     public static final int deusto = 3;

     public static final int pageSize = 10;

     public static final int codeOK = 200;
     public static final int codeGeneric = 400;
     public static final int codeLogout = 101;
     public static final int codeError = 1;

     public static final String warningCode = "INFO";
     public static final String errorCode = "ERROR";
     
     public static final String errormsg = "errormsg";
     public static final String wrnmsg = "warningmsg";
     public static final String infomsg = "infomsg";
     
     public static final String wrong = "Something was wrong :(. Try again later, please. If the error persist, contact with support";
     public static final String noBusiness = "There are no business";
     public static final String noBeacons = "There are no beacons";
     public static final String noOffer = "There are no offers";
     public static final String formErrors = "Please, check the errors";
     public static final String passwordChange = "The password has been changed";
     public static final String passwordError = "The current password is incorrect or the new password is invalid";

     public static final String loginError = "Error login session";
     
     //Roles

     public static final String superAdmin = "superadmin";
     public static final String enterpriseAdmin = "enterpriseadmin";
     public static final String businessUser = "businessuser";
     public static final String appUser = "appuser";

     public static final String enterpriseAdminName = "EnterpriseAdmin";
     public static final String businessUserName = "BusinessUser";


     //**Pages
     public static final String statisticsDetails = "/Admin/Statistics";
     
     //**States
     public static final int active = 1;
     public static final int all = 2;
     
     //Cabinets state
     public static final int okay = 3;
     public static final int revision = 2;
     public static final int bad= 1;
     
     
     //Container
     public static final String productContainer = "products";
     public static final String defaultImage = "http://appfrigo.blob.core.windows.net/products/IamWalls.png";//"https://appfrigo.blob.core.windows.net/products/SoyFrigo.png";
     
     
     public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
    			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
     
//     public static final String[] tax = {"IVA","IGIC"};
//

}