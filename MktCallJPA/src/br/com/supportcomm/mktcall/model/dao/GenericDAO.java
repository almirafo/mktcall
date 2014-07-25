package br.com.supportcomm.mktcall.model.dao;

import java.util.List;

import br.com.supportcomm.mktcall.exception.MktCallException;

public interface GenericDAO<T>
{

    public T persist(T object) throws MktCallException;

    public List<T> persistAll(List<T> list) throws MktCallException;

    public T update(T object) throws MktCallException;

    public List<T> update(List<T> object) throws MktCallException;

    public boolean removeAll(List<T> object) throws MktCallException;

    public boolean remove(Long id) throws MktCallException;

    public List<T> findAll() throws MktCallException;

    public T load(Long id) throws MktCallException;
}
