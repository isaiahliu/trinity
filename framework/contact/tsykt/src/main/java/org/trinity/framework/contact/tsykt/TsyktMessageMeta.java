package org.trinity.framework.contact.tsykt;

import org.trinity.framework.contact.AbstractContactMessageMeta;

public class TsyktMessageMeta extends AbstractContactMessageMeta implements ITsyktMessageMeta {
    private final boolean[] bitMap;

    public TsyktMessageMeta(final int id) {
        this(id, new int[0]);
    }

    public TsyktMessageMeta(final int id, final int[] mandatoryBitMapFlags) {
        super(id);

        bitMap = new boolean[64];

        markAvailable(mandatoryBitMapFlags);
    }

    @Override
    public boolean[] getBitMap() {
        return bitMap;
    }

    @Override
    public void markAvailable(final int... positions) {
        for (final int position : positions) {
            bitMap[position - 1] = true;
        }
    }
}
