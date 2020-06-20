package mipssim.types;

import java.util.BitSet;

public class RInstruction extends AbstractInstruction{
    private String name;
    private BitSet bin;
    private Integer rs, rt, rd, shamt;

    public RInstruction(String name, Integer rs, Integer rt, Integer rd, Integer shamt){
        this.name = name;
        this.rs = rs;
        this.rt = rt;
        this.rd = rd;
        this.shamt = shamt;
        bin = new BitSet(32);
        // Add functor / registers / shamt to the bin representation
        funct(name);
        translateToBin(rs, 10, 5);
        translateToBin(rt, 15, 5);
        translateToBin(rt, 20, 5);
        translateToBin(shamt, 25, 5);
    }
    private void funct(String name){
        switch(name){
            case "add":
                bin.set(26); //100000
                break;
            case "addu": bin.set(26); bin.set(31); //100001
                break;
            case "sub":
                bin.set(26); bin.set(30); //100010
                break;
            case "subu":
                bin.set(26); bin.set(30); bin.set(31); //100011
                break;
            case "and":
                bin.set(26); bin.set(29); //100100
                break;
            case "or":
                bin.set(26); bin.set(29); bin.set(31); //100101
                break;
            case "xor":
                bin.set(26); bin.set(29); bin.set(30); //100110
                break;
            case "nor":
                bin.set(26); bin.set(29); bin.set(30); bin.set(31); //100111
                break;
            case "slt":
                bin.set(26); bin.set(28); bin.set(30); //101010
                break;
            case "sltu":
                bin.set(26); bin.set(28); bin.set(30); bin.set(31); //101011
                break;
            case "sll":
                //000000
                break;
            case "srl":
                bin.set(30); //000010
                break;
            case "jr":
                bin.set(28); //001000
                break;
        }
    }

    private void translateToBin(Integer value, Integer finalPosition, Integer nBits){
        for(int i = 0; i < nBits; i++){
            if(value == 0) break;
            if(value%2==0) bin.set(finalPosition-i);
            value /= 2;
        }
    }

    @Override
    public INSTRUCTION_FORMAT getInstructionF() {
        // TODO Auto-generated method stub
        return INSTRUCTION_FORMAT.R;
    }
}
