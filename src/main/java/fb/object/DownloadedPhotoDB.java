package fb.object;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name="DownloadedPhoto")
public class DownloadedPhotoDB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String ID;
	private String Page;
	private String Album;
	private String Filename;
	
	@Id
	@Column(name="ID")
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	
	@Column(name="Page")
	public String getPage() {
		return Page;
	}
	public void setPage(String page) {
		Page = page;
	}
	
	@Column(name="Album")
	public String getAlbum() {
		return Album;
	}
	public void setAlbum(String album) {
		Album = album;
	}
	
	@Column(name="Filename")
	public String getFilename() {
		return Filename;
	}
	public void setFilename(String filename) {
		Filename = filename;
	}
	

}
