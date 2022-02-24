package riverDTO;

public class POSTNewVoteRequestDTO {
	
	private String image_id;
	private String sub_id;
	private int value;
	
	public String getImage_id() {  return image_id;  }

    public void setImage_id(String image_id) { this.image_id = image_id; }
    
	public String getSub_id() {  return sub_id;  }

    public void setISub_id(String sub_id) { this.sub_id = sub_id; }
    
	public int getvalue() {  return value;  }

    public void setvalue(int value) { this.value = value; }

}
