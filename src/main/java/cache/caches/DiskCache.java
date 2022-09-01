package cache.caches;

import cache.Cache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public final class DiskCache<K, V extends Serializable> extends LinkedHashMap<K, String> implements Cache<K, V> {
    private static final float LOAD_FACTOR = 0.75F;
    private final int cacheSize;

    public DiskCache(final int cacheSize) {
        super(cacheSize, LOAD_FACTOR, true);
        this.cacheSize = cacheSize;

        createStorage();
    }

    private void createStorage() {
        File tempFolder = new File("temp");
        if (!tempFolder.exists()) {
            if (tempFolder.mkdir()) {
                System.out.println("Creation complete");
            }
        }
    }

    @Override
    public void putKeyAndValue(final K key, final V value) {

        if (super.containsValue(super.get(key))) {
            updateValue(key, value);
        } else {
            putObject(key, value);

            if (isFull()) {
                evictEldestEntry();
            }
        }
    }


    private void updateValue(final K key, final V value) {
        String pathToObject = super.get(key);
        try (FileOutputStream fileStream = new FileOutputStream(pathToObject);
             ObjectOutputStream objectStream = new ObjectOutputStream(fileStream)) {
            objectStream.writeObject(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void putObject(final K key, final V value) {
        String pathToObject = "temp\\" + UUID.randomUUID() + ".temp";

        try (FileOutputStream fileStream = new FileOutputStream(pathToObject);
             ObjectOutputStream objectStream = new ObjectOutputStream(fileStream)) {
            super.put(key, pathToObject);


            objectStream.writeObject(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void evictEldestEntry() {
        Map.Entry<K, String> entry = (Map.Entry<K, String>) super.entrySet().toArray()[0];
        File deletingFile = new File(super.remove(entry.getKey()));
        if (deletingFile.delete()) {
            System.out.println("Entry successfully " + "evict.");
        }
    }

    private boolean isFull() {
        return super.size() > this.cacheSize;
    }

    @Override
    public Optional<V> getValueByKey(final K key) {
        if (super.containsKey(key)) {
            return Optional.ofNullable(getValue(key));
        }
        return Optional.empty();
    }

    private V getValue(K key) {
        String pathToObject = super.get(key);
        try (FileInputStream fileStream = new FileInputStream(pathToObject);
             ObjectInputStream objectStream = new ObjectInputStream(fileStream)) {
            V deserializeObject = (V) objectStream.readObject();

            super.get(key);

            return deserializeObject;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void clearCache() {
        deleteFiles();

        super.clear();
    }

    private void deleteFiles() {
        for (Map.Entry<K, String> entry : super.entrySet()) {
            File deletingFile = new File(entry.getValue());
            if (deletingFile.delete()) {
                System.out.println("File deleted successfully");
            }
        }
    }

    @Override
    public String toString() {
        return "DiskCache{" + "objectMap=" + super.keySet() + '}';
    }
}
