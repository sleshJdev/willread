package com.slesh.willread.ioc;


import java.util.HashMap;
import java.util.Map;

public class ServiceLocator {

    private final Map<Class, Object> services = new HashMap<>();

    public void put(Class service, Object instance) {
        services.put(service, instance);
    }

    @SuppressWarnings("unchecked")
    public <T> T lookup(Class<T> service) {
        return (T) services.get(service);
    }

}
