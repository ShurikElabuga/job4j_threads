package ru.job4j.cache;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.id(), model) == null;
    }

    public boolean update(Base model) throws OptimisticException {
        Base value = memory.get(model.id());
        if (value == null) {
            return false;
        }
        if (value.version() != model.version()) {
            throw new OptimisticException("Версии не совпадают.");
        }
        Base updated = new Base(model.id(), model.name(), model.version() + 1);
        return memory.replace(model.id(), value, updated);
    }

    public void delete(int id) {
        memory.remove(id);
    }

    public Optional<Base> findById(int id) {
        return Stream.of(memory.get(id))
                .filter(Objects::nonNull)
                .findFirst();
    }
}
