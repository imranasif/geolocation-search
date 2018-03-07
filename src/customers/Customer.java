package customers;

public class Customer {
  
  public int userId;
  
  public String name;
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  @Override
  public String toString() {
    return "User [userId=" + userId + ", name=" + name + "]";
  }

}
