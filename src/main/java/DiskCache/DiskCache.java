package DiskCache;

import CacheInterface.Cache;
import Exceptions.NullElementException;
import org.eclipse.jetty.util.IO;

import java.io.*;
import java.util.*;

public class DiskCache<K, V extends Serializable> extends LinkedHashMap<K, String> implements Cache<K, V>
    {
        final int cacheSize;

        public DiskCache(int cacheSize)
            {
                super(cacheSize, 0.75f, true);
                this.cacheSize = cacheSize;

                File tempFolder = new File("temp");
                if (!tempFolder.exists()) tempFolder.mkdir();
            }


        @Override
        public void putKeyAndValue(K key, V value) throws IOException
            {
                if (super.containsValue(super.get(key))) {
                    updateValue(key, value);
                } else {
                    putObject(key, value);

                    if (isFull()) evictEldestEntry();
                }

            }


        private void updateValue(K key, V value)
            {
                String pathToObject = super.get(key);
                try (FileOutputStream fileStream = new FileOutputStream(pathToObject);
                     ObjectOutputStream objectStream = new ObjectOutputStream(fileStream)) {
                    objectStream.writeObject(value);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        private void putObject(K key, V value)
            {
                String pathToObject = "temp\\" + UUID.randomUUID().toString() + ".temp";

                try (FileOutputStream fileStream = new FileOutputStream(pathToObject);
                     ObjectOutputStream objectStream = new ObjectOutputStream(fileStream)) {
                    super.put(key, pathToObject);


                    objectStream.writeObject(value);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        private void evictEldestEntry()
            {
                Map.Entry<K, String> entry = (Map.Entry<K, String>) super.entrySet().toArray()[0];
                File deletingFile = new File(super.remove(entry.getKey()));
                deletingFile.delete();
            }

        private boolean isFull()
            {
                return super.size() > this.cacheSize;
            }

        @Override
        public Optional<V> getValueByKey(K key)
            {
                if (super.containsKey(key)) {

                }
                return Optional.empty();
            }

        @Override
        public void clearCache()
            {
                for (Map.Entry<K, String> entry : super.entrySet()) {
                    File deletingFile = new File(entry.getValue());
                    deletingFile.delete();
                }
                super.clear();
            }

        @Override
        public String toString()
            {
                return "DiskCache{" +
                        "objectMap=" + super.keySet() +
                        '}';
            }

        public static void main(String[] args) throws NullElementException, IOException
            {
                Cache<Integer, Integer> diskCache = new DiskCache<Integer, Integer>(3);

                diskCache.putKeyAndValue(1, 1);
                diskCache.putKeyAndValue(2, 2);
                diskCache.putKeyAndValue(3, 3);
                System.out.println(diskCache);
                diskCache.putKeyAndValue(1, 5);
                System.out.println(diskCache);
                diskCache.putKeyAndValue(5, 12);
                System.out.println(diskCache);
            }
    }
