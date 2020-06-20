package mipssim.interfaces;

import mipssim.types.ALU_OPS;

public interface ALU {
    int process(ALU_OPS op, int data1, int data2);
}