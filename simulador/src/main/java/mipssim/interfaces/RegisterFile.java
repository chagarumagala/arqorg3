package mipssim.interfaces;

public interface RegisterFile {
    Integer[] process(Integer read1, Integer read2, Integer writeReg, Integer writeData, boolean regWrite);
}