package micromaintainsys.exceptions;

public class InvalidUserException extends RuntimeException{
    int id;
    public InvalidUserException(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
}
