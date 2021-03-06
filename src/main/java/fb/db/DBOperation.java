package fb.db;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import newfb.object.db.Page;
import newfb.object.db.SaltDetail;
import fb.object.DownloadedPhotoDB;;

public class DBOperation {

	private static EntityManager em;
	
	static {
		
//		try {
//		Class clazz = Class.forName("org.hibernate.jpa.HibernatePersistenceProvider");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		em = Persistence.createEntityManagerFactory("persistentUnit").createEntityManager();
	}
	
	@Transactional
	public static void save(Object object) {
		em.getTransaction().begin();
		em.persist(object);
		em.getTransaction().commit();
	}
	
	public static void save(List<? extends Object> objects) {
		objects.forEach(o -> {
			save(o);
		});
	}
	
	public static Page getPage(String pageId) {
		return em.find(Page.class, pageId);
	}
	
	public static List<Page> getExcludePages() {
		List<Page> pages = em.createNamedQuery("Page.findInclude", Page.class).getResultList();
		
		if(pages == null) 
			pages = new ArrayList<Page>();
		
		return pages;
	}

	public static DownloadedPhotoDB getDownloadedPhoto(String photoId) {
		return em.find(DownloadedPhotoDB.class, photoId);
	}
	
	public static String getSalt(String type) {
		String salt = null;
		switch (type) {
			case "Email":
				salt = em.createNamedQuery("SaltDetail.getEmailSalt", SaltDetail.class).getSingleResult().getSaltDetail();
				break;
			case "Passwd":
				salt = em.createNamedQuery("SaltDetail.getSalt", SaltDetail.class).getSingleResult().getSaltDetail();
				break;
			default:
		} 
		return salt;
	}
}
