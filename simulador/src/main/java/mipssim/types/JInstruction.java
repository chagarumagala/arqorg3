package mipssim.types;

import java.util.BitSet;

public class JInstruction extends AbstractInstruction {
    String name;
    Integer address;
    BitSet bin;

    public JInstruction(String name, Integer address){
        this.name = name;
        this.address = address;
        bin = new BitSet(32);
        funct(name);
        translateAddressToBin(address);
    }

    private void funct(String name){
        switch(name){
            case "j":
                bin.set(4); //000010
                break;
            case "jal":
                bin.set(4); bin.set(5); //000011
                break;
        }
    }

    private void translateAddressToBin(Integer value){
        BitSet helper = new BitSet(32);
        for(int i = 0; i < 32; i++){
            if(value == 0) break;
            if(value%2==0) helper.set(31-i);
            value /= 2;
        }
        for(int i = 0; i < 26; i++){
            if(helper.get(i+4)) bin.set(i);
        }
    }
    @Override
    public INSTRUCTION_FORMAT getInstructionF() {
        return INSTRUCTION_FORMAT.J;
    }
}
