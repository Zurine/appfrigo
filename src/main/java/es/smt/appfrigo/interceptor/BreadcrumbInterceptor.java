package es.smt.appfrigo.interceptor;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import es.smt.appfrigo.bean.BreadCrumb;
import es.smt.appfrigo.bean.Category;
import es.smt.appfrigo.bean.Channel;
import es.smt.appfrigo.bean.Data;
import es.smt.appfrigo.bean.Default;
import es.smt.appfrigo.bean.Distributor;
import es.smt.appfrigo.bean.Notification;
import es.smt.appfrigo.bean.Operator;
import es.smt.appfrigo.bean.Region;
import es.smt.appfrigo.bean.Registration;
import es.smt.appfrigo.bean.SelectProduct;
import es.smt.appfrigo.constants.Constants;
import es.smt.appfrigo.model.BusinessDTO;
import es.smt.appfrigo.model.ProductDTO;
import es.smt.appfrigo.model.SellerDTO;
import es.smt.appfrigo.model.UserAdminDTO;

@Component
public class BreadcrumbInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {

        // set few parameters to handle ajax request from different host
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        response.addHeader("Access-Control-Max-Age", "1000");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type");
        response.addHeader("Cache-Control", "private");

        String reqUri = request.getRequestURI();
        String serviceName = reqUri.substring(reqUri.lastIndexOf("/") + 1,
                reqUri.length());
                if (serviceName.equals("SOMETHING")) {

                }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

    	if(modelAndView!=null && modelAndView.getViewName()!=null){
    		BreadCrumb b = new BreadCrumb();
    		b.setSteps(new ArrayList<Data>());
    		
    		String[] steps = modelAndView.getViewName().split("/");
    		
    		switch (steps[0]) {
		 		case "product":
		 		{
		 			if(steps[1].equals("list")){
		 				b = setActive(b,"Products");
		 			}
		 			else{
		 				b = setStep(b,"Products","/product/list?state="+Constants.active);
		 				ProductDTO p = (ProductDTO) modelAndView.getModelMap().get("product");
		 				switch (steps[1]) {
					 		case "add":
					 		case "edit":
					 		{
					 			if(p.getId() == 0 )
					 				b = setActive(b,"New");
					 			else{
					 				b = setStep(b,p.getName(),"/product/get?id="+p.getId());
				            		b = setActive(b,"Edit");
					 			}
		            			
					 			break;
					 		}
					 		case "details":
					 		{
			            		b = setActive(b,p.getName());
					 			break;
					 		}
		 				}
		 			}

					break;
		 		}
		 		case "category":
		 		{
		 			if(steps[1].equals("list")){
		 				b = setActive(b,"Categories");
		 			}
		 			else{
		 				b = setStep(b,"Categories","/category/list");
		 				switch (steps[1]) {
					 		case "details":
					 		case "edit":
					 		case "add":
					 		{
			            		Category c = (Category) modelAndView.getModelMap().get("category");
			            		if(c.getId() == 0)
			            			b = setActive(b,"New");
			            		else b = setActive(b,c.getName());
					 			break;
					 		}
		 				}
		 			}

					break;
		 		}
		 		case "operator":
		 		{
		 			
		 			
		 			
		 			if(steps[1].equals("list")){
		 				b = setActive(b,"Operators");
		 			}
		 			else if(steps[1].equals("stock")){
		 				switch (steps[2]) {
					 		case "list":
					 		{
				 				b = setActive(b,"Stock");
				 				
					 			break;
					 		}
					 		case "stock":
					 		{
					 			b = setStep(b,"Stock","/operator/stock/list?operatorId=0");
					 			b = setActive(b,"Manage Stock");
					 			break;
					 		}
		 				}
		 			}
		 			else{
		 				b = setStep(b,"Operators","/operator/list?state="+Constants.active);
		 				Operator o = (Operator) modelAndView.getModelMap().get("operator");
		 				switch (steps[1]) {
					 		case "edit":
					 		case "add":
					 		{
					 			if(o.getId() == 0)
					 				b = setActive(b,"New");
					 			else {
					 				b = setStep(b,o.getName(),"/operator/get?id="+o.getId());
				            		b = setActive(b,"Edit");
					 			}
					 			break;
					 		}
					 		case "details":
					 		{
			            		b = setActive(b,o.getName());
					 			break;
					 		}
		 				}
		 			}
					break;
		 		}
		 		case "equipment":
		 		{
		 			if(steps[1].equals("list")){
		 				b = setActive(b,"Equipments");
		 			}
		 			else{
		 				b = setStep(b,"Equipments","/equipment/list?state="+Constants.active);
		 			
	 					switch (steps[1]) {
					 		case "edit":
					 		case "add":
					 		{
					 			BusinessDTO e = (BusinessDTO) modelAndView.getModelMap().get("equipment");
					 			if(e.getId() == 0)
					 				b = setActive(b,"New");
					 			else{
					 				b = setStep(b,e.getName(),"/equipment/get?id="+e.getId());
					 				b = setActive(b,"Edit");
					 			}
					 			break;
					 		}
					 		case "details":
					 		{
					 			BusinessDTO e = (BusinessDTO) modelAndView.getModelMap().get("equipment");
				 				b = setActive(b,e.getName());
					 			break;
					 		}
					 		case "product":
					 		{
				 				Default e = (Default) modelAndView.getModelMap().get("current-b");
				 				b = setStep(b,e.getName(),"/equipment/get?id="+e.getId());
					 			if(steps[2].equals("list")){
					 				b = setActive(b,"Products");
					 			}
					 			else{
					 				b = setStep(b,"Products","/equipment/product/list?id="+e.getId());
					 				switch (steps[2]) {
							 			case "edit":
								 		case "add":
								 		{
								 			SelectProduct p = (SelectProduct) modelAndView.getModelMap().get("product");
								 			if(!p.isUpdate())
								 				b = setActive(b,"New");
								 			else{
								 				b = setStep(b,p.getProduct().getName(),"/equipment/product/get?product="+p.getProduct().getId()+"&business="+e.getId());
								 				b = setActive(b,"Edit");
								 			}
								 			break;
								 		}
								 		case "details":
								 		{
								 			ProductDTO p = (ProductDTO) modelAndView.getModelMap().get("product");
							 				b = setActive(b,p.getName());
		
								 			break;
								 		}
					 				}
					 			}
					 		
					 			break;
					 		}
					 		case "seller":
					 		{
								Default e = new Default(0);
					 			//if(modelAndView!=null && modelAndView.getModelMap()!=null && modelAndView.getModelMap().get("current-b")!=null)
								e = (Default) modelAndView.getModelMap().get("current-b");
								if(e == null)
									e = new Default(0,"");
				 				b = setStep(b,e.getName(),"/equipment/get?id="+e.getId());
					 			if(steps[2].equals("list")){
					 				b = setActive(b,"MSM");
					 			}
					 			else{
					 				b = setStep(b,"MSM","/equipment/seller/list?id="+e.getId());
					 				switch (steps[2]) {
							 			case "edit":
								 		case "add":
								 		{
								 			b = setActive(b,"New");
								 			break;
								 		}
					 				}
					 			}
					 		
					 			break;
					 		}
	 					}
		 			
		 			}
					break;
		 		}
				case "seller":
		 		{
		 			if(steps[1].equals("list")){
		 				b = setActive(b,"MSM");
		 			}
		 			else{
		 				b = setStep(b,"MSM","/seller/list?state="+Constants.active);
		 				switch (steps[1]) {
					 		case "edit":
					 		case "add":
					 		case "upload":
					 		{
					 			SellerDTO s = (SellerDTO) modelAndView.getModelMap().get("seller");
					 			if(s.getId() == 0)
					 				b = setActive(b,"New");
					 			else {
					 				b = setStep(b,s.getName(),"/seller/get?id="+s.getId());
					 				b = setActive(b,"Edit");
					 			}
					 			break;
					 		}
					 		case "editPassword":
					 		{
					 			Registration s = (Registration) modelAndView.getModelMap().get("seller");
					 			b = setStep(b,s.getNickName(),"/seller/get?id="+s.getId());
					 			b = setActive(b,"Edit Password");
					 			break;
					 		}
					 		case "details":
					 		{
					 			SellerDTO s = (SellerDTO) modelAndView.getModelMap().get("seller");
					 			
			            		b = setActive(b,s.getName());
					 			break;
					 		}
		 				}
		 			}
					break;
		 		}
				case "sales":
		 		{
	 				b = setActive(b,"Sales");
					break;
		 		}
				case "ticket":
		 		{
	 				b = setActive(b,"Ticketing");
					break;
		 		}
				case "settings":
		 		{
	 				b = setActive(b,"Settings");
					break;
		 		}
				case "statistic":
		 		{
	 			
	 				if(steps[1].equals("sales")){
	 					b = setActive(b,"Statistics");
		 			}
	 				else{
	 					b = setStep(b,"Statistics","/statistic/sales");
	 					b = setActive(b,"Sales Map");
	 				}
	 				
					break;
		 		}
				case "channel":
		 		{
		 			if(steps[1].equals("list")){
		 				b = setActive(b,"Channels");
		 			}
		 			else{
		 				b = setStep(b,"Channels","/channel/list");
		 				switch (steps[1]) {
					 		case "details":
					 		case "edit":
					 		case "add":
					 		{
			            		Channel c = (Channel) modelAndView.getModelMap().get("channel");
			            		if(c.getId() == 0)
			            			b = setActive(b,"New");
			            		else b = setActive(b,c.getName());
					 			break;
					 		}
		 				}
		 			}

					break;
		 		}
				case "region":
		 		{
		 			if(steps[1].equals("list")){
		 				b = setActive(b,"Regions");
		 			}
		 			else{
		 				b = setStep(b,"Regions","/region/list");
		 				switch (steps[1]) {
					 		case "details":
					 		case "edit":
					 		case "add":
					 		{
					 			Region c = (Region) modelAndView.getModelMap().get("region");
			            		if(c.getId() == 0)
			            			b = setActive(b,"New");
			            		else b = setActive(b,c.getName());
					 			break;
					 		}
		 				}
		 			}

					break;
		 		}
				case "distributor":
		 		{
		 			if(steps[1].equals("list")){
		 				b = setActive(b,"Distributors");
		 			}
		 			else{
		 				b = setStep(b,"Distributors","/distributor/list");
		 				switch (steps[1]) {
					 		case "details":
					 		case "edit":
					 		case "add":
					 		{
			            		Distributor c = (Distributor) modelAndView.getModelMap().get("distributor");
			            		if(c.getId() == 0)
			            			b = setActive(b,"New");
			            		else b = setActive(b,c.getConcession());
					 			break;
					 		}
		 				}
		 			}

					break;
		 		}
				case "tracking":
		 		{
		 			if(steps[1].contains("trackingPath")){
		 				b = setStep(b,"Tracking","/tracking/list");
			            b = setActive(b,"User Tracking");
		 			}
		 			else{
			            b = setActive(b,"Tracking");
		 			}

					break;
		 		}
				case "equipmentType":
		 		{
		 			if(steps[1].contains("products")){
		 				b = setStep(b,"Equipment Type","/equipmentType/list");
			            b = setActive(b,"Products");
		 			}
		 			else{
			            b = setActive(b,"Equipment Type");
		 			}

					break;
		 		}
		 		
				case "notification":
		 		{
		 			if(steps[2].equals("list")){
		 				b = setActive(b,"Notifications");
		 			}
		 			else{
		 				b = setStep(b,"Notifications","/notification/seller/list");
		 				
		 				Notification n = null;
		 				if(modelAndView.getModelMap().get("notification") instanceof Notification){
		 					n = (Notification) modelAndView.getModelMap().get("notification");
		 				}
		 				
		 				switch (steps[2]) {
			 				case "edit":
					 		case "add":
					 		{
					 			if(n.getId() == 0)
					 				b = setActive(b,"New");
					 			else {
					 				b = setStep(b,n.getTitle().substring(0, 10),"/notification/seller/get?id="+n.getId());
				            		b = setActive(b,"Edit");
					 			}
					 			break;
					 		}
					 		case "details":
					 		{
			            		b = setActive(b,n.getTitle().substring(0, 10));
					 			break;
					 		}
					 		case "send":
					 		{
			            		b = setActive(b,"Send");
					 			break;
					 		}
		 				}
		 			}

					break;
		 		}
				case "user":
		 		{
		 			if(steps[1].equals("list")){
		 				b = setActive(b,"Users");
		 			}
		 			else{
		 				b = setStep(b,"Users","/user/list?state="+Constants.active);
		 				switch (steps[1]) {
					 		case "edit":
					 		case "add":
					 		{
					 			UserAdminDTO s = (UserAdminDTO) modelAndView.getModelMap().get("user");
					 			if(s.getId() == 0)
					 				b = setActive(b,"New");
					 			else {
					 				b = setStep(b,s.getName()+" "+s.getSurname(),"/user/get?id="+s.getId());
					 				b = setActive(b,"Edit");
					 			}
					 			break;
					 		}
					 		case "editPassword":
					 		{
					 			Registration s = (Registration) modelAndView.getModelMap().get("seller");
					 			b = setStep(b,s.getNickName(),"/seller/get?id="+s.getId());
					 			b = setActive(b,"Edit Password");
					 			break;
					 		}
					 		case "details":
					 		{
					 			UserAdminDTO s = (UserAdminDTO) modelAndView.getModelMap().get("user");
					 			
			            		b = setActive(b,s.getName());
					 			break;
					 		}
		 				}
		 			}
					break;
		 		}
    		}

    		modelAndView.addObject("breadCrumb", b);
    	}
    
    	
        super.postHandle(request, response, handler, modelAndView);
    }
    
    private BreadCrumb setStep(BreadCrumb b, String name, String url){
    	Data d = new Data();
    	d.setLabel(name);
    	d.setData(url);
    	b.getSteps().add(d);
    	
    	return b;
    }
    
    private BreadCrumb setActive(BreadCrumb b, String name){
    	Data d = new Data();
    	d.setLabel(name);
    	d.setData(null);
    	b.getSteps().add(d);
    	
    	return b;
    }
}
