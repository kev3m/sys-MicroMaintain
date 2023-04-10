package micromaintainsys.exceptions;

public class NotAllowedException extends RuntimeException{
    int id;
    public NotAllowedException(int id){
        this.id = id;
    }

}
