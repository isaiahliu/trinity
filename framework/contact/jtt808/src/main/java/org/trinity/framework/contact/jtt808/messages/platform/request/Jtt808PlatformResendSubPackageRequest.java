package org.trinity.framework.contact.jtt808.messages.platform.request;

import java.util.ArrayList;
import java.util.List;

import org.trinity.framework.contact.ContactMessage;
import org.trinity.framework.contact.ContactMessage.StoreMethod;

@ContactMessage(storeMethod = StoreMethod.BIG_END)
public class Jtt808PlatformResendSubPackageRequest extends AbstractJtt808PlatformRequest {
    private int firstSerialNumber;
    private int resendCount;
    private List<Integer> resendIds;

    public int getFirstSerialNumber() {
        return firstSerialNumber;
    }

    public int getResendCount() {
        return resendCount;
    }

    public List<Integer> getResendIds() {
        if (resendIds == null) {
            resendIds = new ArrayList<>();
        }
        return resendIds;
    }

    public void setFirstSerialNumber(final int firstSerialNumber) {
        this.firstSerialNumber = firstSerialNumber;
    }

    public void setResendCount(final int resendCount) {
        this.resendCount = resendCount;
    }

    public void setResendIds(final List<Integer> resendIds) {
        this.resendIds = resendIds;
    }

    @Override
    protected int getDefaultMessageId() {
        return 0x8003;
    }
}
