package org.jpc.emulator.execution.opcodes.vm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import org.jpc.emulator.processor.fpu64.*;
import static org.jpc.emulator.processor.Processor.*;

public class bswap_o16_rDXr10 extends Executable
{

    public bswap_o16_rDXr10(int blockStart, Instruction parent)
    {
        super(blockStart, parent);
    }


    public bswap_o16_rDXr10(int blockStart, int eip, int prefices, PeekableInputStream input)
    {
        super(blockStart, eip);
    }

    public Branch execute(Processor cpu)
    {
        cpu.r_edx.set16(Short.reverseBytes((short)cpu.r_edx.get16()));
        return Branch.None;
    }

    public boolean isBranch()
    {
        return false;
    }

    public String toString()
    {
        return this.getClass().getName();
    }
}