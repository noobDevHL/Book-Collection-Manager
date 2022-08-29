package com.example.bibliophilia;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class dvIsbnNumberUserType implements UserType {
    @Override
    public int[] sqlTypes() {
        return new int[] {Types.VARCHAR};
    }

    @Override
    public Class<dvIsbnNumber> returnedClass() {
        return dvIsbnNumber.class;
    }

    @Override
    public boolean equals(Object o, Object o1) throws HibernateException {
        return o.equals(o1);
    }

    @Override
    public int hashCode(Object o) throws HibernateException {
        return o.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] names, SharedSessionContractImplementor session, Object owner) throws HibernateException, SQLException {
        String isbn = resultSet.getObject(names[0], String.class);
        if(isbn.equals("null")) {
            return dvIsbnNumber.valueOf("978-3-000-00000-0");
        } else {
            return dvIsbnNumber.valueOf(isbn);
        }
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object isbnNumber, int i, SharedSessionContractImplementor session) throws HibernateException, SQLException {
        if(isbnNumber!= null) {
            preparedStatement.setObject(i, ((dvIsbnNumber)isbnNumber).getIsbn(), sqlTypes()[0]);
        } else {
            preparedStatement.setNull(i, sqlTypes()[0]);
        }
    }

    @Override
    public Object deepCopy(Object o) throws HibernateException {
        return o;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object o) throws HibernateException {
        return (dvIsbnNumber) o;
    }

    @Override
    public Object assemble(Serializable serializable, Object o) throws HibernateException {
        return serializable;
    }

    @Override
    public Object replace(Object o, Object o1, Object o2) throws HibernateException {
        return o;
    }
}
