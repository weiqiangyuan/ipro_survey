package com.ipro.survey.persistence.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * * 通用枚举处理
 *
 * <p/>
 * 在mybatis-config.xml中配置如下:
 *
 * <ul>
 * <li>必须指定javaType枚举类
 * <li>枚举类必须包含getCode和静态的codeOf方法
 * </ul>
 * 
 * Created by weiqiang.yuan on 15/12/12 16:31.
 */
public class CodeEnumTypeHandler extends BaseTypeHandler<Enum<?>> {

    private Method getCode;
    private Method codeOf;

    public CodeEnumTypeHandler(Class<Enum<?>> enumType) {

        String className = enumType.getName();
        String simpleName = enumType.getSimpleName();

        try {
            getCode = enumType.getDeclaredMethod("getCode");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Method " + className + "#getCode():int required.'");
        }

        try {
            codeOf = enumType.getDeclaredMethod("codeOf", int.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Static method " + className + "#codeOf(int code):" + simpleName + " required.");
        }

        if (!Modifier.isStatic(codeOf.getModifiers())) {
            throw new RuntimeException("Static method " + className + "#codeOf(int code):" + simpleName + " required.");
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Enum parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setInt(i, code(parameter));
    }

    @Override
    public Enum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return codeOf(rs.getInt(columnName));
    }

    @Override
    public Enum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return codeOf(rs.getInt(columnIndex));
    }

    @Override
    public Enum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return codeOf(cs.getInt(columnIndex));
    }

    private Integer code(Enum object) {
        try {
            return (Integer) getCode.invoke(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Enum codeOf(int value) {
        try {
            return (Enum) codeOf.invoke(null, value); // invoke static method
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
