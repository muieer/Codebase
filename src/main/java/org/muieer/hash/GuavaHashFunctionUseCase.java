package org.muieer.hash;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class GuavaHashFunctionUseCase {

    public static final HashFunction DEFAULT_HASH_FUNCTION = Hashing.murmur3_128();

    public static void main(String[] args) {
        System.out.println(DEFAULT_HASH_FUNCTION.hashLong(1).asLong());
    }
}
