package com.investment.common.util;

import java.math.BigInteger;
import java.nio.ByteBuffer;

public class Utility {

    public static byte[] legacyToBytes(int a, short b) {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.put((byte)0);
        buffer.put((byte)0);
        buffer.put(int3(a));
        buffer.put(int2(a));
        buffer.put(int1(a));
        buffer.put(int0(a));
        buffer.put(short1(b));
        buffer.put(short0(b));
        return buffer.array();
    }

    private static byte[] legacyToBytes(int a, int b) {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.put(int3(a));
        buffer.put(int2(a));
        buffer.put(int1(a));
        buffer.put(int0(a));
        buffer.put(int3(b));
        buffer.put(int2(b));
        buffer.put(int1(b));
        buffer.put(int0(b));
        return buffer.array();
    }

    public static byte[] intToByteArray(int a) {
//       return  new byte[] {
//                int3(a),
//                int2(a),
//                int1(a),
//                int0(a)
//        };
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.put(int3(a));
        buffer.put(int2(a));
        buffer.put(int1(a));
        buffer.put(int0(a));

        return buffer.array();
    }


    public static String toBinaryString(byte[] bytes, int shift){
        int radix = 1 << shift;
        return new BigInteger(1, bytes).toString(radix);
    }

    public static long bytesToLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.put(bytes, 0, bytes.length);
        buffer.flip();
        return buffer.getLong();
    }

    private static byte int3(int x) {
        return (byte)(x >> 24);
    }

    private static byte int2(int x) {
        return (byte)(x >> 16);
    }

    private static byte int1(int x) {
        return (byte)(x >> 8);
    }

    private static byte int0(int x) {
        return (byte)x;
    }

    private static byte short1(short x) {
        return (byte)(x >> 8);
    }

    private static byte short0(short x) {
        return (byte)x;
    }
}
