package org.trinity.framework.contact.tsykt;

import org.trinity.framework.contact.AbstractContactMessageMeta;

public class TsyktMessageMeta extends AbstractContactMessageMeta implements ITsyktMessageMeta {
    private final byte[] bitMap;

    public TsyktMessageMeta(final int id) {
        super(id);

        bitMap = new byte[64];
    }

    @Override
    public byte[] getBitMap() {
        return bitMap;
    }

    @Override
    public void markAvailable(final int... positions) {
        for (final int position : positions) {
            bitMap[position] = 1;
        }
    }

    @Override
    public void setBitMap(final byte[] bitMap) {
        if (bitMap != null) {
            for (int i = 0; i < 64; i++) {
                this.bitMap[i] = bitMap[i];
            }
        }
    }
}
