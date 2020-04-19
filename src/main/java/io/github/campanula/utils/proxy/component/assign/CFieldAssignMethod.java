package io.github.campanula.utils.proxy.component.assign;

public abstract class CFieldAssignMethod<T> {

    protected String fieldName;

    public CFieldAssignMethod(String fieldName) {
        this.fieldName = fieldName;
    }

    public abstract T getFieldValue();

    public String getFieldName() {
        return fieldName;
    }
}
