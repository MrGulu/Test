package thread.threadLocal;

import org.junit.Test;
import reflect.Appl;

import java.lang.ref.SoftReference;
import java.math.BigDecimal;
import java.util.Objects;

public class Reference {

    @Test
    public void soft() {
        Appl appl = new Appl();
        appl.setApplSeq(new BigDecimal("12313"));
        appl.setApplCde("33");

        SoftReference<Appl> softReference = new SoftReference<>(appl);
        System.out.println(softReference.get());

        System.gc();
        System.out.println(softReference.get());

        if (Objects.isNull(softReference.get())) {
            softReference = new SoftReference<>(appl);
        }
        Appl appl1 = softReference.get();
    }
}
