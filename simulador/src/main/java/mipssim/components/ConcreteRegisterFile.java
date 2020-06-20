package mipssim.components;

import mipssim.interfaces.RegisterFile;

public class ConcreteRegisterFile implements RegisterFile {
    Integer[] registers = new Integer[31];

    public Integer[] process(Integer read1, Integer read2, Integer writeReg, Integer writeData, boolean regWrite){
        Integer[] returnValue = new Integer[2];
        returnValue[0] = registers[read1];
        returnValue[1] = registers[read2];
        if(regWrite) registers[writeReg] = writeData;
        return returnValue;
    }
}