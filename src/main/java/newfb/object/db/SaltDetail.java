package newfb.object.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="SaltDetail")
@NamedQueries(
		{@NamedQuery(name="SaltDetail.getEmailSalt",
				query="SELECT s FROM SaltDetail s WHERE s.saltName = 'Email'"),
		@NamedQuery(name="SaltDetail.getSalt",
		query="SELECT s FROM SaltDetail s WHERE s.saltName = 'Passwd'")}
)
public class SaltDetail extends DBObject {
	
	private String saltName;
	private String saltDetail;
	

	@Column(name="Name")
	@Id
	public String getSaltName() {
		return this.saltName;
	}
	
	public void setSaltName(String saltName) {
		this.saltName = saltName;
	}
	
	@Column(name="Salt")
	public String getSaltDetail() {
		return this.saltDetail;
	}
	
	public void setSaltDetail(String saltDetail) {
		this.saltDetail = saltDetail;
	}

}
