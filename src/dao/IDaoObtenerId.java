package dao;

public interface IDaoObtenerId<T> extends IDaoExtendido<T> {
    public int obtenerIdForeignKey(int id);
}
