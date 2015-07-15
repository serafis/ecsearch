package com.aprinz.ecsearch;

import java.util.Objects;

/**
 * Created by Alexander on 15.07.2015.
 */
public class ErrorCode {
    public final String id;
    public final String text;

    public ErrorCode(String id, String text) {
        this.id = id;
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorCode errorCode = (ErrorCode) o;
        return Objects.equals(id, errorCode.id) &&
                Objects.equals(text, errorCode.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text);
    }
}
