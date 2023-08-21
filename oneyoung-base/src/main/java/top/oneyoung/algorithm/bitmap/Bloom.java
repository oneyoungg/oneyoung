package top.oneyoung.algorithm.bitmap;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Bloom
 *
 * @author oneyoung
 * @since 2023-08-20 18:36
 */

public class Bloom {
    public static void main(String[] args) {
        BloomFilter<CharSequence> bloomFilter = BloomFilter.create(Funnels.stringFunnel(StandardCharsets.UTF_8),10_000_000, 0.001);
        try (
            BufferedReader bufferedReader = new BufferedReader(new FileReader("dat.txt"));
        ){
            bufferedReader.lines().forEach(bloomFilter::put);
            System.out.println("bloomFilter.mightContain(\"https://www.PjNIg.com\\\\0\") = " + bloomFilter.mightContain("https://www.PjNIg.com\\0"));
            for (int i = 0; i < 10; i++) {
                System.out.println(bloomFilter.mightContain(i+""));
            }

        } catch (Exception e){

        }

//        bloomFilter.put()
    }
}
