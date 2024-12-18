import java.io.Serializable;
public class Users implements Serializable{
    private String username,password;
    public String First_name,Last_name,Email,Mobile_number;
    public int age;
    public String gender;

    public Users(String username,String password,String First_name,String Last_name,String Email,String Mobile_number,int age, String gender){
        this.age=age;
        this.Email=Email;
        this.First_name= First_name;
        this.Last_name=Last_name;
        this.Mobile_number=Mobile_number;
        this.password=password;
        this.username=username;
        this.gender= gender;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getFirst_name() {
        return First_name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getMobile_number() {
        return Mobile_number;
    }

    public String getLast_name() {
        return Last_name;
    }

    public String getEmail() {
        return Email;
    }
}