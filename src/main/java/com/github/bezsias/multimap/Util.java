package com.github.bezsias.multimap;

class Util {

    static byte ZERO_BYTE   = (byte) 0;

    static void writeShort(int value, byte[] array, int pos) {
        array[pos] = (byte)((value >> 8) & 0xFF);
        array[pos+1] = (byte)(value & 0xFF);
    }

    static int readShort(byte[] array, int pos) {
        return ((array[pos] & 0xFF) << 8 | (array[pos+1] & 0xFF));
    }
}
