package newfb.object.like;

import newfb.object.common.FBObject;

public class LikeObject extends FBObject {
	
	private LikeObjectInternal[] data;
	
	@Override
	public LikeObjectInternal[] getData() {
		// TODO Auto-generated method stub
		if (data != null && data.length > 0) {
			return data;
		}
		
		return null;
	}
	
	@Override
	protected Paging getPaging() {
		return super.paging;
				
	}
	
	private class LikeObjectInternal implements LikeInterface {
		
		private String name;
		private String id;
		
	    public String getName() {
			return name;
		}
	    
		public void setName(String name) {
			this.name = name;
		}
		
		public String getId() {
			return id;
		}
		
		public void setId(String id) {
			this.id = id;
		}
		  
	}

}
