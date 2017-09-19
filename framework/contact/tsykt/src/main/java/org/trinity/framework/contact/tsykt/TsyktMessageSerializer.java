package org.trinity.framework.contact.tsykt;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.trinity.framework.contact.AbstractContactMessageSerializer;
import org.trinity.framework.contact.ContactMessage.StoreMethod;
import org.trinity.framework.contact.ContactMessageField;
import org.trinity.framework.contact.ContactMessageUtil;

public final class TsyktMessageSerializer extends AbstractContactMessageSerializer<ITsyktMessageMeta, ITsyktMessage>
        implements ITsyktMessageSerializer {

    private static class ByteMatrix {
        private final List<byte[]> matrix = new ArrayList<>();

        private int pos = 0;

        private byte[] current;

        public ByteMatrix() {
            newRow();
        }

        public byte[] calculate() {
            final byte[] result = new byte[] { 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0 };

            for (final byte[] row : matrix) {
                for (int i = 0; i < 8; i++) {
                    result[i] ^= row[i];
                }
            }
            return result;
        }

        public void read(final byte b) {
            if (pos == 8) {
                newRow();
            }

            current[pos++] = b;
        }

        private void newRow() {
            pos = 0;
            current = new byte[] { 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0 };
            matrix.add(current);
        }
    }

    public static String mak;

    @Override
    public byte[] serializeBody(final ITsyktMessage message) {
        final ByteArrayOutputStream body = new ByteArrayOutputStream();

        serializeObject(message.getMeta(), message, body);
        return body.toByteArray();
    }

    @Override
    public byte[] serializeHeader(final ITsyktMessageMeta header) {
        final ByteArrayOutputStream messageCode = new ByteArrayOutputStream();

        ContactMessageUtil.write(messageCode, 0x60, 1, StoreMethod.BIG_END);
        ContactMessageUtil.writeBcd(messageCode, "00", 2, "0");
        ContactMessageUtil.writeBcd(messageCode, "00", 2, "0");

        ContactMessageUtil.writeBcd(messageCode, "61", 1, "0");
        ContactMessageUtil.writeBcd(messageCode, "22", 1, "0");
        ContactMessageUtil.writeBcd(messageCode, "00", 1, "0");
        ContactMessageUtil.writeBcd(messageCode, "00", 3, "0");
        ContactMessageUtil.write(messageCode, header.getId(), 2, StoreMethod.BIG_END);

        int b = 0;
        int length = 0;
        for (final boolean bit : header.getBitMap()) {
            b |= bit ? 1 : 0;
            length++;
            if (length % 8 == 0) {
                messageCode.write(b);
                b = 0;
            } else {
                b <<= 1;
            }
        }

        return messageCode.toByteArray();
    }

    private byte[] calculateMac(final byte[] input) {
        final String step1Hex = toHexString(input);

        final byte[] step2Result = encrypt(step1Hex.substring(0, 8).getBytes());

        final byte[] last8 = step1Hex.substring(8).getBytes();

        for (int i = 0; i < 8; i++) {
            step2Result[i] ^= last8[i];
        }

        final byte[] step3Result = encrypt(step2Result);

        return toHexString(step3Result).substring(0, 8).getBytes();
    }

    private byte[] encrypt(final byte[] bytes) {
        try {
            final DESKeySpec dks = new DESKeySpec(mak.getBytes());
            final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            final SecretKey securekey = keyFactory.generateSecret(dks);
            final Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, securekey, new SecureRandom());
            final byte[] result = cipher.doFinal(bytes);

            return result;
        } catch (final Exception ex) {
            return new byte[0];
        }
    }

    private String toHexString(final byte[] bytes) {
        final StringBuilder result = new StringBuilder();

        for (final byte b : bytes) {
            final String hex = Integer.toHexString(Byte.toUnsignedInt(b));
            if (hex.length() == 1) {
                result.append("0");
            }
            result.append(hex.toUpperCase());
        }

        return result.toString();
    }

    @Override
    protected Field[] getFields(final ITsyktMessageMeta header, final Object object) {
        final boolean[] bitmap = header.getBitMap();

        return Arrays.stream(object.getClass().getDeclaredFields()).filter(item -> {
            final ContactMessageField annotation = item.getAnnotation(ContactMessageField.class);
            if (annotation == null) {
                return false;
            }

            return bitmap[annotation.bitmapPos() - 1];
        }).sorted((a, b) -> {
            return a.getAnnotation(ContactMessageField.class).order()
                    - b.getAnnotation(ContactMessageField.class).order();
        }).toArray(Field[]::new);
    }

    @Override
    protected byte[] getMac(final ITsyktMessageMeta meta, final ByteArrayOutputStream output) {
        final ByteMatrix matrix = new ByteMatrix();

        final int id = meta.getId();
        final byte idLow = (byte) (id & 0xFF);
        final byte idHigh = (byte) (id >> 8);

        matrix.read(idHigh);
        matrix.read(idLow);
        final boolean[] bitmap = meta.getBitMap();
        byte bitmapRow = 0;
        for (int i = 0; i < 64; i++) {
            bitmapRow |= bitmap[i] ? 1 : 0;
            if (i % 8 == 7) {
                matrix.read(bitmapRow);

                bitmapRow = 0;
            } else {
                bitmapRow <<= 1;
            }
        }
        final byte[] mab = output.toByteArray();

        for (final byte b : mab) {
            matrix.read(b);
        }

        final byte[] step1Result = matrix.calculate();

        return calculateMac(step1Result);
    }
}
