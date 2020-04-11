package io.github.campanula.utils.cor;

import org.junit.Test;

import java.math.BigDecimal;

public class ChainTest {

    @Test
    public void testChain() {
        AChain a = new AChain("1");
        a.execute();
        System.out.println();

        a = new AChain("1");
        Chain<Integer, BigDecimal> b = a.setNext(new BChain());
        Chain<BigDecimal, String> c = b.setNext(new CChain());
        c.setNext(new DChain());
        a.execute();
        System.out.println();

        a = new AChain("2");
        a.setNext(new BChain()).setNext(new CChain()).setNext(new DChain());
        a.execute();
        System.out.println();


        a = new AChain("3");
        a.setNext(new BChain(4, true)).setNext(new CChain()).setNext(new DChain());
        a.execute();
        System.out.println();
    }

    public class DChain extends AbstractChain<String, BigDecimal> {

        @Override
        protected BigDecimal handler(String inParam) {
            BigDecimal bigDecimal = new BigDecimal(inParam);
            System.out.println("DChain return : " + bigDecimal);
            return bigDecimal;
        }
    }

    public class CChain extends AbstractChain<BigDecimal, String> {
        @Override
        protected String handler(BigDecimal inParam) {
            String s = inParam.toPlainString();
            System.out.println("CChain return : " + s);
            return s;
        }
    }

    public class BChain extends AbstractChain<Integer, BigDecimal> {

        public BChain() {
        }

        public BChain(Integer inParam, boolean useExternalInParam) {
            super(inParam, useExternalInParam);
        }

        @Override
        protected BigDecimal handler(Integer inParam) {
            BigDecimal bigDecimal = BigDecimal.valueOf(inParam);
            System.out.println("BChain return : " + bigDecimal.toPlainString());
            return bigDecimal;
        }
    }

    public class AChain extends AbstractChain<String, Integer> {

        public AChain(String inParam) {
            super(inParam);
        }

        @Override
        protected Integer handler(String inParam) {
            Integer integer = Integer.valueOf(inParam);
            System.out.println("AChain return : " + integer.toString());
            return integer;
        }
    }
}
