package micromaintainsys.dao;

public interface CRUD<T>{
    //public T create(T obj);

    public T pegaPorId(int id);
    public <T> boolean atualiza(int Id, String atributo, T valor);
    public boolean remove(int id);

}
