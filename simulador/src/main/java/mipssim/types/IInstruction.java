package mipssim.types;

import java.util.BitSet;

public class IInstruction extends AbstractInstruction{
    private String name;
    private Integer rs, rt, i;
    private BitSet bin;

    public IInstruction(String name, Integer rs, Integer rt, Integer i){
        this.name = name;
        this.rs = rs;
        this.rt = rt;
        this.i = i;
        bin = new BitSet(32);
        funct(name);
        translateToBin(rs, 10, 5);
        translateToBin(rt, 15, 5);
        translateToBin(i, 31, 16);
    }

    private void funct(String name){
        switch(name){
            case "beq":
                bin.set(3); //000100
                break;
            case "bne":
                bin.set(3); bin.set(5); //000101
                break;
            case "addi":
                bin.set(2); //001000
                break; 
            case "addiu":
                bin.set(2); bin.set(5); //001001
                break;
            case "andi":
                bin.set(2); bin.set(3); //001100
                break;
            case "ori":
                bin.set(2); bin.set(3); bin.set(5); //001101
                break;
            case "slti":
                bin.set(2); bin.set(4); //001010
                break;
            case "sltiu":
                bin.set(2); bin.set(4); bin.set(5); //001011
                break;
            case "lui":
                bin.set(2); bin.set(3); bin.set(4); bin.set(5); //001111
                break;
            case "lw":
                bin.set(0); bin.set(4); bin.set(5); //100011
                break;
            case "sw":
                bin.set(0); bin.set(2); bin.set(4); bin.set(5); //101011
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
        return INSTRUCTION_FORMAT.I;
    }
}
