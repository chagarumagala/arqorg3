package mipssim.components;

import mipssim.interfaces.ALU;
import mipssim.types.ALU_OPS;

public class ConcreteALU implements ALU {
    ALU_OPS cOps;
    int data1;
    int data2;

    public ConcreteALU(){};

    public int process(ALU_OPS op, int data1, int data2){
        switch(op){
            case AND:
                return data1 & data2;
            case OR:
                return data1 | data2;
            case ADD:
                return data1 + data2;
            case SUB:
                return data1 - data2;
            case SLT:
                return data1 < data2 ? 1 : 0;
            case NOR:
                return ~(data1 | data2);
            default:
                return -404; // Easy to identify number. Just for debugging purposes, it shouldn't come up.
        }
    }
}