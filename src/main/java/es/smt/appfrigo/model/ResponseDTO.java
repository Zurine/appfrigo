package es.smt.appfrigo.model;

import java.io.Serializable;
public class ResponseDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 //  [DataMember]
		        private ErrorDTO error;
		        private ErrorDTO errorToken;
		        private Object response;
				/**
				 * 
				 */
				public ResponseDTO() {
					super();
					// TODO Auto-generated constructor stub
				}
				/**
				 * @param error
				 * @param errorToken
				 * @param response
				 */
				public ResponseDTO(ErrorDTO error, ErrorDTO errorToken, Object response) {
					super();
					this.error = error;
					this.errorToken = errorToken;
					this.response = response;
				}
				public ErrorDTO getError() {
					return error;
				}
				public void setError(ErrorDTO error) {
					this.error = error;
				}
				public ErrorDTO getErrorToken() {
					return errorToken;
				}
				public void setErrorToken(ErrorDTO errorToken) {
					this.errorToken = errorToken;
				}
				public Object getResponse() {
					return response;
				}
				public void setResponse(Object response) {
					this.response = response;
				}
				@Override
				public String toString() {
					return "ResponseDTO [error=" + error + ", errorToken=" + errorToken + ", response=" + response
							+ "]";
				}

		        
		        
}
