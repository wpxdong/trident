package io.github.trident.base.organization;

import java.util.Locale;

public interface IDbService<T> {
    String persist(T entity, String requestId, Locale locale);
}
