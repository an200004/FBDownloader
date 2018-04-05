package newfb.object.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FBError")
public class ErrorDBObject extends DBObject {
	
	private String message;
    private String type;
    private String code;
    private String errorSubcode;
    private String fbTraceId;
    private Date errorDate;
    
    @Column(name="FB_Trace_Id")
	@Id
	public String getFbTraceId() {
		return fbTraceId;
	}
	public void setFbTraceId(String fbTraceId) {
		this.fbTraceId = fbTraceId;
	}
	
    @Column(name="Message")
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Column(name="Type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name="Code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name="Error_Subcode")
	public String getErrorSubcode() {
		return errorSubcode;
	}
	public void setErrorSubcode(String errorSubcode) {
		this.errorSubcode = errorSubcode;
	}
	
	@Column(name="Error_Date")
	public Date getErrorDate() {
		return this.errorDate;
	}
	
	public void setErrorDate(Date errorDate) {
		this.errorDate = errorDate;
	}
	
    
    

}
