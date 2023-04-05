package micromaintainsys.dao;

public interface CRUD<T>{
    //public T create(T obj);

    public T pegaPorId(int id);
    public boolean remove(int id);

}
