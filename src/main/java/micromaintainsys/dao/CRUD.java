package micromaintainsys.dao;

public interface CRUD<T>{

    public T pegaPorId(int id);
    public boolean remove(int id);

}
