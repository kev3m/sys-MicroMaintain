package micromaintainsys.dao;

public interface CRUD<T>{
    //public T create(T obj);

    public T findById(int id);
    public <T> boolean update(int Id, String atributo, T valor);
    public boolean remove(int id);

}
