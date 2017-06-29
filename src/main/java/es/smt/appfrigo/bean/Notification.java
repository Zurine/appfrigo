package es.smt.appfrigo.bean;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import es.smt.appfrigo.model.WelcomeOffer;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class Notification extends Result implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("Nid")
	private int id;
	@Size(min=3, max=200, message="Title is required and must be between 3 and 2")
	@NotEmpty(message="Title is required")
	@NotNull(message="Title is required")
	@JsonProperty("t")
    private String title;
	@Size(min=3, max=500, message="Message is required and must be between 3 and 50")
	@NotEmpty(message="Message is required")
	@NotNull(message="Message is required")
	@JsonProperty("d")
	private String message;
	@JsonProperty("p")
	private String photo;
	@JsonProperty("tC")
	private int content;

	private Promotion promotion;
	@JsonProperty("ODto")
	private WelcomeOffer offer;
	
	@JsonProperty("tN")
	private int type;
	@JsonProperty("Us")
	private List<UserNotif> users;
	@JsonIgnore
	private Image image;
	
	public Notification() {
		super();
	}
	

	
	public Notification(int id, String title, String message, String photo, int content, Promotion promotion,
			WelcomeOffer offer, int type) {
		super();
		this.id = id;
		this.title = title;
		this.message = message;
		this.photo = photo;
		this.content = content;
		this.promotion = promotion;
		this.offer = offer;
		this.type = type;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	
	public int getContent() {
		return content;
	}

	public void setContent(int content) {
		this.content = content;
	}

	public Promotion getPromotion() {
		return promotion;
	}
	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}
	
	
	public WelcomeOffer getOffer() {
		return offer;
	}

	public void setOffer(WelcomeOffer offer) {
		this.offer = offer;
	}



	public int getType() {
		return type;
	}



	public void setType(int type) {
		this.type = type;
	}


	

	public List<UserNotif> getUsers() {
		return users;
	}



	public void setUsers(List<UserNotif> users) {
		this.users = users;
	}



	public Image getImage() {
		return image;
	}



	public void setImage(Image image) {
		this.image = image;
	}



	@Override
	public String toString() {
		return "Notification [id=" + id + ", title=" + title + ", message=" + message + ", photo=" + photo
				+ ", content=" + content + ", promotion=" + promotion + ", offer=" + offer + ", type=" + type + "]";
	}

	
	
}