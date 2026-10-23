package com.canach.commonutils.utils;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Objects;
import java.util.UUID;

public class UUIDUtils {

    private static final SecureRandom RANDOM = new SecureRandom();

    private UUIDUtils() {
        // clase utilitaria
    }

    /**
     * Genera un UUID v7 a partir de un string.
     * El string se mezcla (SHA-256) con un salt aleatorio para formar los bits
     * aleatorios, por lo que cada llamada devuelve un valor único, ordenable
     * por tiempo, incluso con el mismo input.
     */
    public static UUID generateV7(String input) {
        Objects.requireNonNull(input, "input no puede ser null");
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);

        MessageDigest digest = sha256();
        digest.update(input.getBytes(StandardCharsets.UTF_8));
        digest.update(salt);

        return build(System.currentTimeMillis(), digest.digest());
    }

    /**
     * Variante determinística: mismo input + mismo timestamp => mismo UUID.
     * Útil para IDs reproducibles (tests, idempotencia).
     */
    public static UUID generateV7Deterministic(String input, long timestampMillis) {
        Objects.requireNonNull(input, "input no puede ser null");
        MessageDigest digest = sha256();
        byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
        return build(timestampMillis, hash);
    }

    /** UUID v7 estándar, sin input. */
    public static UUID generateV7() {
        byte[] rand = new byte[10];
        RANDOM.nextBytes(rand);
        return build(System.currentTimeMillis(), rand);
    }

    // rand debe tener al menos 10 bytes
    private static UUID build(long timestampMillis, byte[] rand) {
        long msb = (timestampMillis & 0xFFFFFFFFFFFFL) << 16; // 48 bits de timestamp
        msb |= 0x7000L;                                       // versión 7
        msb |= ((rand[0] & 0x0FL) << 8) | (rand[1] & 0xFFL);  // rand_a (12 bits)

        long lsb = ByteBuffer.wrap(rand, 2, 8).getLong();
        lsb = (lsb & 0x3FFFFFFFFFFFFFFFL) | 0x8000000000000000L; // variante 10 + rand_b

        return new UUID(msb, lsb);
    }

    private static MessageDigest sha256() {
        try {
            return MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 no disponible", e);
        }
    }
}