package org.trinity.framework.contact.jtt808;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.trinity.framework.contact.AbstractContactMessageQueue;
import org.trinity.framework.contact.jtt808.messages.Jtt808SubPackageMessage;
import org.trinity.framework.contact.jtt808.messages.terminal.request.Jtt808TerminalBadRequest;

public class Jtt808MessageQueue
        extends AbstractContactMessageQueue<IJtt808MessageMeta, IJtt808Message, IJtt808MessageSerializer, IJtt808MessageDeserializer>
        implements IJtt808MessageQueue {

    private byte[] remainingBytes = new byte[0];

    public Jtt808MessageQueue(final IJtt808MessageSerializer serializer, final IJtt808MessageDeserializer deserializer,
            final int maxSerialNumber, final int maxBodyLength, final int timeOutSenconds, final int maxRetryTimes) {
        super(serializer, deserializer, maxSerialNumber, maxBodyLength, timeOutSenconds, maxRetryTimes);
    }

    @Override
    protected boolean allowSubPackages() {
        return true;
    }

    @Override
    protected void copyMeta(final IJtt808MessageMeta from, final IJtt808MessageMeta to) {
        to.setEncryptionMode(from.getEncryptionMode());
        to.setPhoneNo(from.getPhoneNo());

    }

    @Override
    protected IJtt808Message createBadRequestMessage() {
        return new Jtt808TerminalBadRequest();
    }

    @Override
    protected IJtt808Message createSubPackageMessage(final boolean requireResponse) {
        return new Jtt808SubPackageMessage(requireResponse);
    }

    @Override
    protected byte[] escape(final byte[] messageCode) {
        try (final ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            out.write(0x7e);
            for (final byte b : messageCode) {
                switch (b) {
                case 0x7e:
                    out.write(0x7d);
                    out.write(0x02);
                    break;
                case 0x7d:
                    out.write(0x7d);
                    out.write(0x01);
                    break;
                default:
                    out.write(b);
                    break;
                }
            }
            out.write(0x7e);

            return out.toByteArray();
        } catch (final IOException e) {
            return new byte[0];
        }
    }

    @Override
    protected byte getVerifyCode(final byte[] byteArray) {
        int result = 0;

        for (final byte b : byteArray) {
            result ^= Byte.toUnsignedInt(b);
        }

        return (byte) result;
    }

    @Override
    protected boolean hasVerifyCode() {
        return true;
    }

    @Override
    protected List<byte[]> unescape(final byte[] messageCodes) {
        try {
            final byte[] codes;
            final ByteArrayOutputStream array = new ByteArrayOutputStream();
            array.write(remainingBytes);

            array.write(messageCodes);
            array.flush();
            codes = array.toByteArray();

            final List<byte[]> result = new ArrayList<>();

            boolean withinMessage = false;
            boolean switching = false;

            final ByteArrayOutputStream messageCode = new ByteArrayOutputStream();
            int prepareLength = 0;
            int index = 0;
            for (final byte b : codes) {
                index++;

                if (b == 0x7e) {
                    withinMessage = !withinMessage;

                    if (!withinMessage) {
                        prepareLength += index;
                        index = 0;
                        if (messageCode.size() != 0) {
                            result.add(messageCode.toByteArray());
                        }

                        messageCode.reset();
                    }
                    continue;
                }

                if (switching) {
                    switch (b) {
                    case 0x01:
                        messageCode.write(0x7d);
                        break;
                    case 0x02:
                        messageCode.write(0x7e);
                        break;
                    }
                    switching = false;
                } else if (b == 0x7d) {
                    switching = true;
                } else {
                    messageCode.write(b);
                }
            }

            remainingBytes = new byte[codes.length - prepareLength];

            for (int i = prepareLength; i < codes.length; i++) {
                remainingBytes[i - prepareLength] = codes[i];
            }
            return result;
        } catch (final IOException e) {
            return Collections.emptyList();
        }
    }
}
